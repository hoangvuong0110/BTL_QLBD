package DoiTuong;

public class BangDia {
	private String maBangDia, tenBangDia, moTA;
	private double giaThue;
	private int soLuong;
	private TheLoai theLoai;
	private NhaCungCap nCC;
	public String getMaBangDia() {
		return maBangDia;
	}
	public void setMaBangDia(String maBangDia) {
		this.maBangDia = maBangDia;
	}
	public String getTenBangDia() {
		return tenBangDia;
	}
	public void setTenBangDia(String tenBangDia) {
		this.tenBangDia = tenBangDia;
	}
	public String getMoTA() {
		return moTA;
	}
	public void setMoTA(String moTA) {
		this.moTA = moTA;
	}
	public double getGiaThue() {
		return giaThue;
	}
	public void setGiaThue(double giaThue) {
		this.giaThue = giaThue;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public TheLoai getTheLoai() {
		return theLoai;
	}
	public void setTheLoai(TheLoai theLoai) {
		this.theLoai = theLoai;
	}
	public BangDia(String maBangDia, String tenBangDia) {
		super();
		this.maBangDia = maBangDia;
		this.tenBangDia = tenBangDia;
	}
	public NhaCungCap getnCC() {
		return nCC;
	}
	public void setnCC(NhaCungCap nCC) {
		this.nCC = nCC;
	}
	public BangDia(String maBangDia, String tenBangDia, String moTA, double giaThue, int soLuong, TheLoai theLoai,
			NhaCungCap nCC) {
		super();
		this.maBangDia = maBangDia;
		this.tenBangDia = tenBangDia;
		this.moTA = moTA;
		this.giaThue = giaThue;
		this.soLuong = soLuong;
		this.theLoai = theLoai;
		this.nCC = nCC;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maBangDia == null) ? 0 : maBangDia.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BangDia other = (BangDia) obj;
		if (maBangDia == null) {
			if (other.maBangDia != null)
				return false;
		} else if (!maBangDia.equals(other.maBangDia))
			return false;
		return true;
	}
	public BangDia() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BangDia [maBangDia=" + maBangDia + ", tenBangDia=" + tenBangDia + ", moTA=" + moTA + ", giaThue="
				+ giaThue + ", soLuong=" + soLuong + ", theLoai=" + theLoai + ", nCC=" + nCC + "]";
	}
	
	

}
