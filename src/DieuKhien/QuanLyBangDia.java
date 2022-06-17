package DieuKhien;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.Database;
import DoiTuong.BangDia;
import DoiTuong.KhachHang;
import DoiTuong.NhaCungCap;
import DoiTuong.TheLoai;

public class QuanLyBangDia {
	private static ArrayList<BangDia> dsBangDia = new ArrayList<BangDia>();

	public QuanLyBangDia() {
		// TODO Auto-generated constructor stub
		dsBangDia = new ArrayList<BangDia>();
	}

	public ArrayList<BangDia> docTuBangBangDia() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select BangDia.IDBangDia, BangDia.TenBD,BangDia.MoTa, TheLoai.TenTheLoai, \r\n"
					+ "NhaCungCap.TenNhaCungCap,BangDia.SoLuong, BangDia.GiaThue\r\n" + "from BangDia join TheLoai\r\n"
					+ "on BangDia.IDTheLoai=TheLoai.IDTheLoai\r\n" + "			join NhaCungCap\r\n"
					+ "on NhaCungCap.IDNhaCungCap=BangDia.IDNhaCungCap";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maBD = rs.getString(1);
				String tenBD = rs.getString(2);
				String moTa = rs.getString(3);
				String tenTheLoai = rs.getString(4);
				String tenNCC = rs.getString(5);
				int soLuong = rs.getInt(6);
				double giaThue = rs.getDouble(7);
				BangDia bd = new BangDia(maBD, tenBD, moTa, giaThue, soLuong,new TheLoai(tenTheLoai),new NhaCungCap(tenNCC));
				dsBangDia.add(bd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsBangDia;
	}
	public static int getTotalofTableBD() {
		int total = 0;
	
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select count(IDBangDia) as Total from [dbo].[BangDia]";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			
			total = rs.getInt(1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return total;
		
	}
	public static ArrayList<BangDia> phanTrang(int from , int to) {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select * from (select ROW_NUMBER() over (ORDER BY [IDBangDia]) as STT,BangDia.IDBangDia, BangDia.TenBD,BangDia.MoTa, TheLoai.TenTheLoai,BangDia.SoLuong, BangDia.GiaThue,NhaCungCap.TenNhaCungCap from BangDia join TheLoai on BangDia.IDTheLoai=TheLoai.IDTheLoai join NhaCungCap on NhaCungCap.IDNhaCungCap=BangDia.IDNhaCungCap) as phanTrang where phanTrang.STT Between ? and ? ";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, from);
			stm.setInt(2, to);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				
				String maBD = rs.getString("IDBangDia");
				String tenBD = rs.getString("TenBD");
				String moTa = rs.getString("MoTa");
				String tenTL = rs.getString("TenTheLoai");
				int soLuong = rs.getInt("SoLuong");
				double giaThue = rs.getDouble("GiaThue");
				String tenNcc = rs.getString("TenNhaCungCap");
				BangDia bd = new BangDia(maBD, tenBD, moTa, giaThue, soLuong,new TheLoai(tenTL),new NhaCungCap(tenNcc));
					
				dsBangDia.add(bd);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsBangDia;

	}
	public boolean themBangDia(String maBD, String tenBD, String moTa, double donGia, int soLuong, String tenTheLoai,
			String tenNhaCC) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("\r\n" + "declare @IDTheLoai char(10),@IDNhaCungCap char(10)\r\n"
					+ "select @IDTheLoai = IDTheLoai\r\n" + "from TheLoai\r\n" + "where TenTheLoai = ?\r\n"
					+ "select @IDNhaCungCap = IDNhaCungCap\r\n" + "from NhaCungCap\r\n" + "where TenNhaCungCap = ?\r\n"
					+ "insert BangDia values(?,@IDNhaCungCap,@IDTheLoai,?,?,?,?)");
			stmt.setString(1, tenTheLoai);
			stmt.setString(2, tenNhaCC);
			stmt.setString(3, maBD);
			stmt.setString(4, tenBD);
			stmt.setString(5, moTa);
			stmt.setDouble(6, donGia);
			stmt.setInt(7, soLuong);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean xoaBangDia(String maBD) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from BangDia where IDBangDia = ?");
			stmt.setString(1, maBD);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capnhat(String maBD, String tenBD, String moTa, Double GiaNhap, int soLuong, String tenTheLoai,
			String tenNCC) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("declare @IDTheLoai char(10),@IDNhaCungCap char(10)\r\n"
					+ "select @IDTheLoai = IDTheLoai\r\n" + "from TheLoai\r\n" + "where TenTheLoai = ?\r\n"
					+ "select @IDNhaCungCap = IDNhaCungCap\r\n" + "from NhaCungCap\r\n" + "where TenNhaCungCap = ?\r\n"
					+ "update BangDia set IDNhaCungCap = @IDNhaCungCap,TenBD = ?,MoTa = ?,IDTheLoai=@IDTheLoai,GiaThue=?,SoLuong=? where IDBangDia = ?");
			stmt.setString(1, tenTheLoai);
			stmt.setString(2, tenNCC);
			stmt.setString(3, tenBD);
			stmt.setString(4, moTa);
			stmt.setDouble(5, GiaNhap);
			stmt.setInt(6, soLuong);
			stmt.setString(7, maBD);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<BangDia> timKiem(String tenBD, String tenNCC, String tenTL, double min, double max) {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "\r\n" + "select BangDia.IDBangDia, BangDia.TenBD,BangDia.MoTa, TheLoai.TenTheLoai, \r\n"
					+ "NhaCungCap.TenNhaCungCap,BangDia.SoLuong, BangDia.GiaThue\r\n" + "from BangDia join TheLoai\r\n"
					+ "on BangDia.IDTheLoai=TheLoai.IDTheLoai\r\n" + "join NhaCungCap\r\n"
					+ "on NhaCungCap.IDNhaCungCap=BangDia.IDNhaCungCap\r\n" + "where BangDia.TenBD like N'%" + tenBD
					+ "%' and NhaCungCap.TenNhaCungCap like N'%" + tenNCC + "%'\r\n"
					+ "								and TheLoai.TenTheLoai like N'%" + tenTL + "%'\r\n"
					+ "				and GiaThue >=" + min + " and GiaThue <= " + max + " ";
	
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maBD = rs.getString(1);
				String tenBangDia = rs.getString(2);
				String moTa = rs.getString(3);
				String tenTheLoai = rs.getString(4);
				String tenNhaCungCap = rs.getString(5);
				int soLuong = rs.getInt(6);
				double giaThue = rs.getDouble(7);
				BangDia bd = new BangDia(maBD, tenBangDia, moTa, giaThue, soLuong,new TheLoai(tenTheLoai),new NhaCungCap(tenNCC));
				dsBangDia.add(bd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsBangDia;
	}

	public boolean thueBangDia(String maBD, int soLuong) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("declare @soLuong int \r\n"
					+ "set  @soLuong = (select SoLuong from BangDia where IDBangDia like ?)\r\n"
					+ "update BangDia set SoLuong = @soLuong - " + soLuong + " where IDBangDia like ?");
			stmt.setString(1, maBD);
			stmt.setString(2, maBD);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean traBangDia(String maBD, int soLuong) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("declare @soLuong int \r\n"
					+ "set  @soLuong = (select SoLuong from BangDia where IDBangDia like ?)\r\n"
					+ "update BangDia set SoLuong = @soLuong + " + soLuong + " where IDBangDia like ?");
			stmt.setString(1, maBD);
			stmt.setString(2, maBD);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public String tuDongLayMa() {
		String maBD = "";
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "declare @ma char(5),@max int\r\n"
					+ "set @ma = RIGHT((select MAX(IDBangDia) from BangDia),3)\r\n"
					+ "set @max = CAST(@ma as int) + 1\r\n" + "set @ma = 'BD' + cast(@max as char(3))\r\n"
					+ "select @ma";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maBangDia = rs.getString(1);
				maBD = maBangDia;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maBD;
	}

	public double layGiaThue(String maBD) {
		double giaThue = 0;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select [dbo].[inIDBangDia_GiaThue]('" + maBD + "')";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				double gt = rs.getDouble(1);
				giaThue = gt;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return giaThue;
	}
}
