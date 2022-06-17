package DoiTuong;

public class QuocGia {
	private String maQuocGia,tenQuocGia;

	public QuocGia(String maQuocGia, String tenQuocGia) {
		super();
		this.maQuocGia = maQuocGia;
		this.tenQuocGia = tenQuocGia;
	}

	public String getMaQuocGia() {
		return maQuocGia;
	}

	public void setMaQuocGia(String maQuocGia) {
		this.maQuocGia = maQuocGia;
	}

	public String getTenQuocGia() {
		return tenQuocGia;
	}

	public void setTenQuocGia(String tenQuocGia) {
		this.tenQuocGia = tenQuocGia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maQuocGia == null) ? 0 : maQuocGia.hashCode());
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
		QuocGia other = (QuocGia) obj;
		if (maQuocGia == null) {
			if (other.maQuocGia != null)
				return false;
		} else if (!maQuocGia.equals(other.maQuocGia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuocGia [maQuocGia=" + maQuocGia + ", tenQuocGia=" + tenQuocGia + "]";
	}
	
}
