package DieuKhien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.Database;
import DoiTuong.TaiKhoan;

public class QuanLyTaiKhoan {
	private ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();

	public QuanLyTaiKhoan() {
		dstk = new ArrayList<TaiKhoan>();
	}

	public ArrayList<TaiKhoan> docTuBang() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select t.IDTaiKhoan , TenTK , MatKhau\r\n" + "from TaiKhoan t join NhanVien n\r\n"
					+ "on t.IDTaiKhoan = n.IDTaiKhoan\r\n" + "where n.TinhTrang = 1";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String matk = rs.getString(1);
				String tentk = rs.getString(2);
				String matkhau = rs.getString(3);
				TaiKhoan tk = new TaiKhoan(matk, tentk, matkhau);
				dstk.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dstk;
	}

//	public String dangNhap(String tenTK, String matKhau) {
//		String maTK = "SAI";
//		try {
//			Connection con = Database.getInstance().getConnection();
//			String sql = "select IDTaiKhoan from TaiKhoan where TenTK like '" + tenTK + "' and MatKhau like '" + matKhau
//					+ "'";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				String ma = rs.getString(1);
//				maTK = ma;
//			}
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return maTK;
//	}

	public boolean themTaiKhoan(String maTK, String tenTK, String matKhau) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Insert into TaiKhoan values(?,?,?)");
			stmt.setString(1, maTK);
			stmt.setString(2, tenTK);
			stmt.setString(3, matKhau);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public String tuDongLayMa() {
		String maTK = "";
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "declare @ma char(5),@max int\r\n"
					+ "set @ma = RIGHT((select MAX(IDTaiKhoan) from TaiKhoan),3)\r\n"
					+ "set @max = CAST(@ma as int) + 1\r\n" + "set @ma = 'TK' + cast(@max as char(3))\r\n"
					+ "select @ma";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				maTK = ma;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maTK;
	}

	public boolean doiMatKhau(String maTK, String matKhau) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update TaiKhoan set MatKhau = ? where IDTaiKhoan like ?");
			stmt.setString(2, maTK);
			stmt.setString(1, matKhau);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

}
