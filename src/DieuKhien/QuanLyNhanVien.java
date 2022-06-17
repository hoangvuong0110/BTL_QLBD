package DieuKhien;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.Database;
import DoiTuong.NhanVien;

public class QuanLyNhanVien {
	private ArrayList<NhanVien> qlnv = new ArrayList<NhanVien>();

	public QuanLyNhanVien() {
		qlnv = new ArrayList<NhanVien>();
	}

	public ArrayList<NhanVien> docTuBang() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select * from NhanVien";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNv = rs.getString(1);
				String tenNV = rs.getString(2);
				String chucVu = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				Date ngayLamViec = rs.getDate(6);
				String cmnd = rs.getString(7);
				String diaChi = rs.getString(8);
				String email = rs.getString(9);
				String sdt = rs.getString(10);
				int trangThai = rs.getInt(11);
				NhanVien nv = new NhanVien(maNv, tenNV, chucVu, gioiTinh, ngaySinh, ngayLamViec, cmnd, sdt, diaChi,
						email, trangThai);
				qlnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qlnv;

	}

	public boolean themNhanVien(String maNV, String ten, String chucVu, String phai, Date ngaySinh, Date ngayLam,
			String cmND, String sdt, String diachi, String emaiL, int tinhTrang, String matk) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert NhanVien values(?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, maNV);
			stmt.setString(2, ten);
			stmt.setString(3, chucVu);
			stmt.setString(4, phai);
			stmt.setDate(5, ngaySinh);
			stmt.setDate(6, ngayLam);
			stmt.setString(7, cmND);
			stmt.setString(8, diachi);
			stmt.setString(9, emaiL);
			stmt.setString(10, sdt);
			stmt.setInt(11, tinhTrang);
			stmt.setString(12, matk);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capNhat(String manV, String ten, String chucvu, String phai, Date ngaySinh, Date ngayLam,
			String cmND, String sdt, String diachi, String emaiL, int tinhTrang) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Update NhanVien " + "set hoTenNV=?," + "chucVu=?," + "gioiTinh=?,"
					+ "ngaySinh=?," + "ngayLamViec=?," + "cMND=?," + "sDT=?," + "diaChi=?," + "email=?," + "tinhTrang=?"
					+ " where iDNhanVien like ?");
			stmt.setString(1, ten);
			stmt.setString(2, chucvu);
			stmt.setString(3, phai);
			stmt.setDate(4, ngaySinh);
			stmt.setDate(5, ngayLam);
			stmt.setString(6, cmND);
			stmt.setString(7, sdt);
			stmt.setString(8, diachi);
			stmt.setString(9, emaiL);
			stmt.setInt(10, tinhTrang);
			stmt.setString(11, manV);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<NhanVien> tim(String ten) {
		ArrayList<NhanVien> dstimNV = new ArrayList<NhanVien>();
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select * from NhanVien where hoTenNV =N'" + ten + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNv = rs.getString(1);
				String tenNV = rs.getString(2);
				String chucVu = rs.getString(3);
				String gioiTinh = rs.getString(4);
				Date ngaySinh = rs.getDate(5);
				Date ngayLamViec = rs.getDate(6);
				String cmnd = rs.getString(7);
				String sdt = rs.getString(8);
				String diaChi = rs.getString(9);
				String email = rs.getString(10);
				int trangThai = rs.getInt(11);
				NhanVien nv = new NhanVien(maNv, tenNV, chucVu, gioiTinh, ngaySinh, ngayLamViec, cmnd, sdt, diaChi,
						email, trangThai);
				qlnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}

	public String tuDongLayMa() {
		String maNV = "";
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "declare @ma char(5),@max int\r\n"
					+ "set @ma = RIGHT((select MAX(IDNhanVien) from NhanVien),3)\r\n"
					+ "set @max = CAST(@ma as int) + 1\r\n" + "set @ma = 'NV' + cast(@max as char(3))\r\n"
					+ "select @ma";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				maNV = ma;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maNV;
	}

	public String layMaNV(String maTK) {
		String maNV = "";
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select IDNhanVien from NhanVien where IDTaiKhoan like '" + maTK + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				maNV = ma;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maNV;
	}

	public String layMaTK(String maNV) {
		String maTK = "";
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select IDTaiKhoan from NhanVien where IDNhanVien like '" + maNV + "'";
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

}
