package DieuKhien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.Database;
import DoiTuong.NhaCungCap;
import DoiTuong.QuocGia;

public class QuanLyNhaCungCap {
	private ArrayList<NhaCungCap> dsNCC = new ArrayList<NhaCungCap>();

	public QuanLyNhaCungCap() {
		dsNCC = new ArrayList<NhaCungCap>();
	}

	public ArrayList<NhaCungCap> docTuBang() {
		try {
			Connection ketnoi = Database.getConnection();
			String danhSachNhaCungCap = "select n.IDNhaCungCap,TenNhaCungCap,q.IDQuocGia,q.TenQuocGia,n.SDT,n.Email\r\n"
					+ "from NhaCungCap n join QuocGia q\r\n" + "on n.IDQuocGia = q.IDQuocGia";
			Statement Starement = ketnoi.createStatement();
			ResultSet rs = Starement.executeQuery(danhSachNhaCungCap);
			while (rs.next()) {
				String idNCC = rs.getString(1);
				String tenNCC = rs.getString(2);
				String tenQuocGia = rs.getString(4);
				String idQuocGia = rs.getString(3);
				String soDienThoai = rs.getString(5);
				String eMail = rs.getString(6);
				NhaCungCap nCC = new NhaCungCap(idNCC, tenNCC,eMail, soDienThoai, new QuocGia(idQuocGia, tenQuocGia));
				dsNCC.add(nCC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNCC;
	}

	public boolean themNCC(String idNCC, String tenNCC, String quocGia, String soDienThoai, String eMail) {
		Connection ketnoi = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = ketnoi.prepareStatement("declare @IDQuocGia char(5)\r\n"
					+ "set @IDQuocGia = (select IDQuocGia from QuocGia where TenQuocGia like ?)\r\n"
					+ "insert NhaCungCap values(?,@IDQuocGia,?,?,?)");
			stmt.setString(1, quocGia);
			stmt.setString(2, idNCC);
			stmt.setString(3, tenNCC);
			stmt.setString(4, soDienThoai);
			stmt.setString(5, eMail);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capNhapNCC(String idNCC, String tenNCC, String quocGia, String soDienThoai, String eMail) {
		Connection connection = Database.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			preparedStatement = connection.prepareStatement("declare @IDQuocGia char(5)\r\n"
					+ "set @IDQuocGia = (select IDQuocGia from QuocGia where TenQuocGia like ?)\r\n"
					+ "update NhaCungCap set TenNhaCungCap=?, IDQuocGia=@IDQuocGia,SDT=?,Email=? where NhaCungCap.IDNhaCungCap=?");
			preparedStatement.setString(1, quocGia);
			preparedStatement.setString(2, tenNCC);
			preparedStatement.setString(3, soDienThoai);
			preparedStatement.setString(4, eMail);
			preparedStatement.setString(5, idNCC);
			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public String tuDongLayMa() {
		String maNCC = "";
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "declare @ma char(5),@max int\r\n"
					+ "set @ma = RIGHT((select MAX(IDNhaCungCap) from NhaCungCap),3)\r\n"
					+ "set @max = CAST(@ma as int) + 1\r\n" + "set @ma = 'CC' + cast(@max as char(3))\r\n"
					+ "select @ma";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				maNCC = ma;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maNCC;
	}

}
