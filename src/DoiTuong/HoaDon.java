package DoiTuong;

import java.sql.Date;

public class HoaDon {
	private String maHoaDon;
	private Date ngayLapHoaDon, ngayTra;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public Date getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}
	public void setNgayLapHoaDon(Date ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}
	public Date getNgayTra() {
		return ngayTra;
	}
	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}
	public HoaDon(String maHoaDon, Date ngayLapHoaDon, KhachHang khachHang, NhanVien nhanVien) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public HoaDon(String maHoaDon, Date ngayLapHoaDon, Date ngayTra, KhachHang khachHang, NhanVien nhanVien) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.ngayTra = ngayTra;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}
	public HoaDon(KhachHang khachHang, NhanVien nhanVien) {
		super();
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLapHoaDon=" + ngayLapHoaDon + ", ngayTra=" + ngayTra
				+ ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + "]";
	}

	
}
