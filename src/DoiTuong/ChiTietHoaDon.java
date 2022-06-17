package DoiTuong;

public class ChiTietHoaDon {
	private BangDia bangDia;
	private int soLuong;
	public BangDia getBangDia() {
		return bangDia;
	}
	public void setBangDia(BangDia bangDia) {
		this.bangDia = bangDia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [bangDia=" + bangDia + ", soLuong=" + soLuong + "]";
	}
	public ChiTietHoaDon(BangDia bangDia, int soLuong) {
		super();
		this.bangDia = bangDia;
		this.soLuong = soLuong;
	}
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
