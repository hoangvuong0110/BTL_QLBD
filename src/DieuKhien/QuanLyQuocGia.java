package DieuKhien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.Database;
import DoiTuong.QuocGia;
import DoiTuong.TheLoai;

public class QuanLyQuocGia {
	private ArrayList<QuocGia> qlqg = new ArrayList<QuocGia>();

	public QuanLyQuocGia() {
		qlqg = new ArrayList<QuocGia>();
	}

	public ArrayList<QuocGia> docTuBang() {
		try {
			Connection ketnoi = Database.getConnection();
			String sqlTheLoai = "select * from QuocGia";
			Statement Starement = ketnoi.createStatement();
			ResultSet rs = Starement.executeQuery(sqlTheLoai);
			while (rs.next()) {
				String maQuocgia = rs.getString(1);
				String tenQuocGia = rs.getString(2);
				QuocGia qg = new QuocGia(maQuocgia, tenQuocGia);
				qlqg.add(qg);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return qlqg;
	}

	public boolean themTheLoai(String maQuocGia, String tenQuocGia) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Insert into QuocGia Values(?,?)");
			stmt.setString(1, maQuocGia);
			stmt.setString(2, tenQuocGia);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capNhatTL(String maQuocGia, String tenQuocGia) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update QuocGia set TenQuocGia = ? where IDQuocGia like ?");
			stmt.setString(1, tenQuocGia);
			stmt.setString(2, maQuocGia);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public String tuDongLayMa() {
		String maQG = "";
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "declare @ma char(5),@max int\r\n"
					+ "set @ma = RIGHT((select MAX(IDTheLoai) from TheLoai),3)\r\n"	
					+ "set @max = CAST(@ma as int) + 1\r\n" + "set @ma = 'TL' + cast(@max as char(3))\r\n"
					+ "select @ma";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				maQG = ma;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maQG;
	}
}
