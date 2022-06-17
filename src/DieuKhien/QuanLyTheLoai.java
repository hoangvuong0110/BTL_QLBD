package DieuKhien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.Database;
import DoiTuong.TheLoai;

public class QuanLyTheLoai {
	private ArrayList<TheLoai> dsTL = new ArrayList<TheLoai>();

	public QuanLyTheLoai() {
		// TODO Auto-generated constructor stub
		dsTL = new ArrayList<TheLoai>();
	}

	public ArrayList<TheLoai> docTuBang() {
		try {
			Connection ketnoi = Database.getConnection();
			String sqlTheLoai = "select * from TheLoai";
			Statement Starement = ketnoi.createStatement();
			ResultSet rs = Starement.executeQuery(sqlTheLoai);
			while (rs.next()) {
				String maTheLoai = rs.getString(1);
				String tenTheLoai = rs.getString(2);
				TheLoai tl = new TheLoai(maTheLoai, tenTheLoai);
				dsTL.add(tl);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTL;
	}

	public boolean themTheLoai(String maTheLoai, String tenTheLoai) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Insert into TheLoai Values(?,?)");
			stmt.setString(1, maTheLoai);
			stmt.setString(2, tenTheLoai);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capNhatTL(String maTheLoai, String tenTheLoai) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update TheLoai set TenTheLoai = ? where IDTheLoai like ?");
			stmt.setString(1, tenTheLoai);
			stmt.setString(2, maTheLoai);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public String tuDongLayMa() {
		String maTL = "";
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "declare @ma char(5),@max int\r\n"
					+ "set @ma = RIGHT((select MAX(IDTheLoai) from TheLoai),3)\r\n"
					+ "set @max = CAST(@ma as int) + 1\r\n" + "set @ma = 'TL' + cast(@max as char(3))\r\n"
					+ "select @ma";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maTheLoai = rs.getString(1);
				maTL = maTheLoai;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maTL;
	}
}
