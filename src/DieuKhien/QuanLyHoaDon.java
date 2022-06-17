package DieuKhien;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Database.Database;
import DoiTuong.ChiTietHoaDon;
import DoiTuong.HoaDon;
import DoiTuong.KhachHang;
import DoiTuong.NhanVien;

public class QuanLyHoaDon {
	private ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();

	public QuanLyHoaDon() {
		// TODO Auto-generated constructor stub
		dsHoaDon = new ArrayList<HoaDon>();
	}

	public ArrayList<HoaDon> docTuBang() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select h.IDHoaDon,v.HoTenNV,k.HotenKH,h.NgayLapHD,NgayTraDia\r\n"
					+ "from HoaDon h join NhanVien v\r\n" + "on h.IDNhanVien = v.IDNhanVien\r\n"
					+ "			  join KhachHang k\r\n" + "on h.IDKhachHang = k.IDKhachHang\r\n"
					+ "where h.IDHoaDon in (select IDHoaDon from HoaDon where NgayTraDia not like 'NULL')";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				String tenKhachHang = rs.getString(3);
				String tenNhanVien = rs.getString(2);
				Date ngayLapHoaDon = rs.getDate(4);
				Date ngayTra = rs.getDate(5);
				HoaDon hd = new HoaDon(maHoaDon, ngayLapHoaDon, ngayTra, new KhachHang(tenKhachHang), new NhanVien(tenNhanVien));
				dsHoaDon.add(hd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	public ArrayList<HoaDon> docTuBang1() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select h.IDHoaDon,v.HoTenNV,k.HotenKH,h.NgayLapHD\r\n" + "from HoaDon h join NhanVien v\r\n"
					+ "on h.IDNhanVien = v.IDNhanVien\r\n" + "			  join KhachHang k\r\n"
					+ "on h.IDKhachHang = k.IDKhachHang\r\n"
					+ "where h.IDHoaDon not in (select IDHoaDon from HoaDon where NgayTraDia not like 'NULL')";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				String tenKhachHang = rs.getString(3);
				String tenNhanVien = rs.getString(2);
				Date ngayLapHoaDon = rs.getDate(4);
				HoaDon hd = new HoaDon(maHoaDon, ngayLapHoaDon, new KhachHang(tenKhachHang), new NhanVien(tenNhanVien));
				dsHoaDon.add(hd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHoaDon;
	}
	public ArrayList<HoaDon> timKiemHoaDonTheoTen(String tenKH) {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select h.IDHoaDon,k.HotenKH,v.HoTenNV,h.NgayLapHD\r\n" + 
					"from HoaDon h join NhanVien v on h.IDNhanVien = v.IDNhanVien\r\n" + 
					"join KhachHang k on h.IDKhachHang = k.IDKhachHang\r\n" + 
					"where k.HotenKH like N'%"+ tenKH +"%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				String tenKhachHang = rs.getString(2);
				String tenNhanVien = rs.getString(3);
				Date ngayLapHoaDon = rs.getDate(4);
				HoaDon hd = new HoaDon(maHoaDon, ngayLapHoaDon, new KhachHang(tenKhachHang), new NhanVien(tenNhanVien));
				dsHoaDon.add(hd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHoaDon;
	}
	public ArrayList<HoaDon> timKiemHoaDon(String tenKH, String tenNV, Date tuNgay, Date denNgay, int trThai) {
		if (trThai == 1) {
			try {
				Connection con = Database.getInstance().getConnection();
				String sql = "select h.IDHoaDon,v.HoTenNV,k.HotenKH,h.NgayLapHD,NgayTraDia\r\n"
						+ "from HoaDon h join NhanVien v\r\n" + "on h.IDNhanVien = v.IDNhanVien\r\n"
						+ "			  join KhachHang k\r\n" + "on h.IDKhachHang = k.IDKhachHang\r\n"
						+ "where k.HotenKH like N'%" + tenKH + "%' and v.HoTenNV like N'%" + tenNV + "%'\r\n"
						+ "and h.NgayLapHD >= '" + tuNgay.toString() + "' and h.NgayLapHD <= '" + denNgay.toString()
						+ "'\r\n" + "and h.IDHoaDon in (select IDHoaDon from HoaDon where NgayTraDia not like 'NULL')";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					String maHoaDon = rs.getString(1);
					String tenKhachHang = rs.getString(3);
					String tenNhanVien = rs.getString(2);
					Date ngayLapHoaDon = rs.getDate(4);
					Date ngayTra = rs.getDate(5);
					HoaDon hd = new HoaDon(maHoaDon, ngayLapHoaDon, ngayTra, new KhachHang(tenKhachHang), new NhanVien(tenNhanVien));
					dsHoaDon.add(hd);
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			try {
				Connection con = Database.getInstance().getConnection();
				String sql = "select h.IDHoaDon,v.HoTenNV,k.HotenKH,h.NgayLapHD\r\n"
						+ "from HoaDon h join NhanVien v\r\n" + "on h.IDNhanVien = v.IDNhanVien\r\n"
						+ "			  join KhachHang k\r\n" + "on h.IDKhachHang = k.IDKhachHang\r\n"
						+ "where k.HotenKH like N'%" + tenKH + "%' and v.HoTenNV like N'%" + tenNV + "%'\r\n"
						+ "and h.NgayLapHD >= '" + tuNgay.toString() + "' and h.NgayLapHD <= '" + denNgay.toString()
						+ "'\r\n"
						+ "and h.IDHoaDon not in (select IDHoaDon from HoaDon where NgayTraDia not like 'NULL')";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					String maHoaDon = rs.getString(1);
					String tenKhachHang = rs.getString(3);
					String tenNhanVien = rs.getString(2);
					Date ngayLapHoaDon = rs.getDate(4);
					HoaDon hd = new HoaDon(maHoaDon, ngayLapHoaDon, new KhachHang(tenKhachHang), new NhanVien(tenNhanVien));
					dsHoaDon.add(hd);
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dsHoaDon;
	}

	public boolean themHoaDon(String maHD, String maNV, String maKH) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Insert into HoaDon values(?,?,?,?,null)");
			stmt.setString(1, maHD);
			stmt.setString(2, maNV);
			stmt.setString(3, maKH);
			stmt.setDate(4, Date.valueOf(LocalDate.now()));
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean traHoaDon(String maHD) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update HoaDon set NgayTraDia = ? where IDHoaDon = ?");
			stmt.setDate(1, Date.valueOf(LocalDate.now()));
			stmt.setString(2, maHD);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public String tuDongLayMa() {
		String maHD = "";
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "declare @maHD char(5),@max int\r\n"
					+ "set @maHD = RIGHT((select MAX(IDHoaDon) from HoaDon),3)\r\n"
					+ "set @max = CAST(@maHD as int) + 1\r\n" + "set @maHD = 'HD' + CAST(@max as char(3))\r\n"
					+ "select @maHD";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHoaDon = rs.getString(1);
				maHD = maHoaDon;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maHD;
	}

	public String laySDTKhachHang(String maHD) {
		String sdt = "";
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select [dbo].[IDHoaDon_SDT]('" + maHD + "')";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String soDienThoai = rs.getString(1);
				sdt = soDienThoai;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sdt;
	}

}
