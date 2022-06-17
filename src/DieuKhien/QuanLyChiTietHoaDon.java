package DieuKhien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.Database;
import DoiTuong.BangDia;
import DoiTuong.ChiTietHoaDon;

public class QuanLyChiTietHoaDon {
	private ArrayList<ChiTietHoaDon> chiTietHD = new ArrayList<ChiTietHoaDon>();

	public QuanLyChiTietHoaDon() {
		// TODO Auto-generated constructor stub
		chiTietHD = new ArrayList<ChiTietHoaDon>();
	}

	public ArrayList<ChiTietHoaDon> docTuBangChiTietHD(String maHD) {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select c.IDBangDia , b.TenBD , c.SoLuong \r\n" + "from ChiTietHoaDon c join BangDia b\r\n"
					+ "on c.IDBangDia = b.IDBangDia\r\n" + "where IDHoaDon = '" + maHD + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maBangDia = rs.getString(1);
				String tenBangDia = rs.getString(2);
				int soLuong = rs.getInt(3);
				ChiTietHoaDon ct = new ChiTietHoaDon(new BangDia(maBangDia, tenBangDia), soLuong);
				chiTietHD.add(ct);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return chiTietHD;
	}

	public boolean themCTHoaDon(String maBD, String maHD, int soLuong) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?)");
			stmt.setString(1, maBD);
			stmt.setString(2, maHD);
			stmt.setInt(3, soLuong);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
}
