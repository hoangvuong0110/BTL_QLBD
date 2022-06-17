package DoiTuong;

import java.util.Date;

public class KhachHang {
	private String maKH, hoTen, gioiTinh;
	private Date ngaySinh, ngayDK;
	private String cMND, diaChi, email, sdt;

	public KhachHang() {
		super();
	}

	public KhachHang(String maKH, String hoTen, String gioiTinh, Date ngaySinh, Date ngayDK, String cMND, String diaChi,
			String email, String sdt) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.ngayDK = ngayDK;
		this.cMND = cMND;
		this.diaChi = diaChi;
		this.email = email;
		this.sdt = sdt;
	}


	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public KhachHang(String hoTen) {
		super();
		this.hoTen = hoTen;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public Date getNgayDK() {
		return ngayDK;
	}

	public void setNgayDK(Date ngayDK) {
		this.ngayDK = ngayDK;
	}

	public String getcMND() {
		return cMND;
	}

	public void setcMND(String cMND) {
		this.cMND = cMND;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh
				+ ", ngayDK=" + ngayDK + ", cMND=" + cMND + ", diaChi=" + diaChi + ", email=" + email + ", sdt=" + sdt
				+ "]";
	}

}
