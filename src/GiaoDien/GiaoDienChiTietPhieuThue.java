package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DieuKhien.QuanLyBangDia;
import DieuKhien.QuanLyChiTietHoaDon;
import DieuKhien.QuanLyHoaDon;
import DieuKhien.QuanLyNhanVien;
import DoiTuong.ChiTietHoaDon;
import DoiTuong.HoaDon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GiaoDienChiTietPhieuThue extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable tableCTHoaDon;
	private DefaultTableModel tablemodelCT;
	private JLabel lblTenPhieu, lblMaPhieu, lblTenKH, lblSoDienThoai, lblNgayThue, lblNhanVien, lblNgayTra, lblTong,
			lblTongTien, lblTra, lblPhat, lblTienPhat, lblThue, lblTienThue;
	private JButton btnTraBangDia, btnInPhieu, btnTroVe;
	private QuanLyChiTietHoaDon CT;
	private QuanLyHoaDon HD;
	private QuanLyBangDia BD;
	private String maHD;
	private QuanLyNhanVien NV;
	private String maTK;

	public GiaoDienChiTietPhieuThue(String maHD, int i,String maTK) {
		this.maTK = maTK;
		this.maHD = maHD;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hóa Đơn");
		setBounds(100, 100, 668, 558);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTenPhieu = new JLabel("Phiếu Thuê");
		lblTenPhieu.setForeground(Color.BLUE);
		lblTenPhieu.setFont(new Font("Arial", Font.BOLD, 30));
		lblTenPhieu.setBounds(244, 22, 186, 49);
		contentPane.add(lblTenPhieu);

		JLabel lblMPhiuThu = new JLabel("Mã Phiếu Thuê :");
		lblMPhiuThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMPhiuThu.setBounds(461, 7, 100, 26);
		contentPane.add(lblMPhiuThu);

		lblMaPhieu = new JLabel("");
		lblMaPhieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaPhieu.setBounds(571, 7, 72, 26);
		contentPane.add(lblMaPhieu);

		JLabel label = new JLabel("Tên Khách Hàng:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 90, 110, 26);
		contentPane.add(label);

		lblTenKH = new JLabel("");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKH.setBounds(125, 90, 150, 26);
		contentPane.add(lblTenKH);

		JLabel lblSDT = new JLabel("Số Điện Thoại :");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT.setBounds(20, 127, 100, 26);
		contentPane.add(lblSDT);

		lblSoDienThoai = new JLabel("");
		lblSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoDienThoai.setBounds(125, 127, 150, 26);
		contentPane.add(lblSoDienThoai);

		JLabel lbl1 = new JLabel("Ngày Thuê :");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl1.setBounds(451, 90, 80, 26);
		contentPane.add(lbl1);

		lblNgayThue = new JLabel("");
		lblNgayThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayThue.setBounds(536, 90, 88, 26);
		contentPane.add(lblNgayThue);

		JLabel lblNhnVin = new JLabel("Nhân Viên");
		lblNhnVin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNhnVin.setBounds(476, 374, 75, 26);
		contentPane.add(lblNhnVin);

		lblNhanVien = new JLabel("");
		lblNhanVien.setForeground(Color.RED);
		lblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhanVien.setBounds(471, 411, 182, 26);
		contentPane.add(lblNhanVien);

		lblTong = new JLabel("Tổng Tiền :");
		lblTong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTong.setBounds(10, 450, 80, 26);
		contentPane.add(lblTong);

		lblTongTien = new JLabel("");
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongTien.setBounds(82, 450, 132, 26);
		contentPane.add(lblTongTien);

		lblTra = new JLabel("Ngày Trả  :");
		lblTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTra.setBounds(461, 127, 75, 26);
		contentPane.add(lblTra);

		lblNgayTra = new JLabel("");
		lblNgayTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayTra.setBounds(536, 127, 88, 26);
		contentPane.add(lblNgayTra);

		btnInPhieu = new JButton("In Phiếu");
		btnInPhieu.setBackground(Color.LIGHT_GRAY);
		btnInPhieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInPhieu.setBounds(122, 480, 110, 23);
		btnInPhieu.setIcon(new ImageIcon("icon/print_16px.png"));
		contentPane.add(btnInPhieu);

		btnTroVe = new JButton("Trở về");
		btnTroVe.setBackground(Color.LIGHT_GRAY);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setBounds(421, 480, 110, 23);
		btnTroVe.setIcon(new ImageIcon("icon/back_to_16px.png"));
		contentPane.add(btnTroVe);

		lblPhat = new JLabel("Tiền Phạt :");
		lblPhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhat.setBounds(10, 411, 80, 26);
		contentPane.add(lblPhat);

		lblTienPhat = new JLabel("");
		lblTienPhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTienPhat.setBounds(82, 411, 238, 26);
		contentPane.add(lblTienPhat);

		btnTraBangDia = new JButton("Trả Băng Đĩa");
		btnTraBangDia.setBackground(Color.LIGHT_GRAY);
		btnTraBangDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTraBangDia.setBounds(262, 480, 132, 23);
		btnTraBangDia.setIcon(new ImageIcon("icon/refund_16px.png"));
		contentPane.add(btnTraBangDia);

		lblThue = new JLabel("Tiền Thuê :");
		lblThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThue.setBounds(10, 374, 80, 26);
		contentPane.add(lblThue);

		lblTienThue = new JLabel("");
		lblTienThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTienThue.setBounds(82, 374, 132, 26);
		contentPane.add(lblTienThue);

		if (i == 0)
			taoPhieuThue(maHD);
		else
			taoHoaDon(maHD);

		btnTraBangDia.addActionListener(this);
		btnTroVe.addActionListener(this);
	}

	private void taoPhieuThue(String maHD) {
		lblTenPhieu.setText("Phiếu Thuê");
		lblTra.setVisible(false);
		lblNgayTra.setVisible(false);
		lblPhat.setVisible(false);
		lblTienPhat.setVisible(false);
		lblTong.setVisible(false);
		lblTongTien.setVisible(false);
		lblThue.setVisible(false);
		lblTienThue.setVisible(false);
		String[] colNameCT = "Mã Băng Đĩa;Tên Băng Đĩa;Số Lượng".split(";");
		tablemodelCT = new DefaultTableModel(colNameCT, 0);
		JScrollPane scrollPane = new JScrollPane(tableCTHoaDon = new JTable(tablemodelCT),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 164, 633, 199);
		contentPane.add(scrollPane);

		HD = new QuanLyHoaDon();
		CT = new QuanLyChiTietHoaDon();
		List<ChiTietHoaDon> list = CT.docTuBangChiTietHD(maHD);
		for (ChiTietHoaDon chiTietHoaDon : list) {
			String[] rowData = { chiTietHoaDon.getBangDia().getMaBangDia(), chiTietHoaDon.getBangDia().getTenBangDia(),
					chiTietHoaDon.getSoLuong() + "" };
			tablemodelCT.addRow(rowData);
		}
		tableCTHoaDon.setModel(tablemodelCT);

		List<HoaDon> listHD = HD.docTuBang1();
		for (HoaDon hoaDon : listHD) {
			if (maHD.trim().equals(hoaDon.getMaHoaDon().trim())) {
				lblMaPhieu.setText(maHD);
				lblTenKH.setText(hoaDon.getKhachHang().getHoTen());
				String sdt = HD.laySDTKhachHang(maHD);
				lblSoDienThoai.setText(sdt);
				lblNgayThue.setText(hoaDon.getNgayLapHoaDon().toString());
				lblNhanVien.setText(hoaDon.getNhanVien().getTenNV());
			}
		}

	}

	private void taoHoaDon(String maHD) {
		HD = new QuanLyHoaDon();
		BD = new QuanLyBangDia();
		btnTraBangDia.setVisible(false);
		lblTenPhieu.setText("Hóa Đơn");
		String[] colNameCT = "Mã Băng Đĩa;Tên Băng Đĩa;Số Lượng;Giá Thuê".split(";");
		tablemodelCT = new DefaultTableModel(colNameCT, 0);
		JScrollPane scrollPane = new JScrollPane(tableCTHoaDon = new JTable(tablemodelCT),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 164, 633, 199);
		contentPane.add(scrollPane);
		CT = new QuanLyChiTietHoaDon();
		List<ChiTietHoaDon> list = CT.docTuBangChiTietHD(maHD);
		for (ChiTietHoaDon chiTietHoaDon : list) {
			String[] rowData = { chiTietHoaDon.getBangDia().getMaBangDia(), chiTietHoaDon.getBangDia().getTenBangDia(),
					chiTietHoaDon.getSoLuong() + "", BD.layGiaThue(chiTietHoaDon.getBangDia().getMaBangDia()) + "" };
			tablemodelCT.addRow(rowData);
		}
		tableCTHoaDon.setModel(tablemodelCT);

		List<HoaDon> listHD = HD.docTuBang();
		for (HoaDon hoaDon : listHD) {
			if (maHD.trim().equals(hoaDon.getMaHoaDon().trim())) {
				lblMaPhieu.setText(maHD);
				lblTenKH.setText(hoaDon.getKhachHang().getHoTen());
				String sdt = HD.laySDTKhachHang(maHD);
				lblSoDienThoai.setText(sdt);
				lblNgayThue.setText(hoaDon.getNgayLapHoaDon().toString());
				lblNgayTra.setText(hoaDon.getNgayTra().toString());
				lblNhanVien.setText(hoaDon.getNhanVien().getTenNV());
				long thoiGianThue = hoaDon.getNgayTra().getTime() - hoaDon.getNgayLapHoaDon().getTime();
				int soNgayThue = (int) (thoiGianThue / (24 * 60 * 60 * 1000));
				if (soNgayThue == 0)
					soNgayThue = 1;
				if (soNgayThue <= 7) {
					lblTienThue.setText(soNgayThue * tinhTienThue() + " VND");
					lblTongTien.setText(soNgayThue * tinhTienThue() + " VND");
					lblTienPhat.setText("0 VND");
				} else {
					double tongTien = soNgayThue * tinhTienThue();
					lblTienThue.setText(soNgayThue * tinhTienThue() + " VND");
					lblTongTien.setText(tongTien * 1.5 + " VND");
					lblTienPhat.setText((tongTien / 2) + " VND (" + (soNgayThue - 7) + " ngày trễ hạn)");
				}

			}
		}

	}

	private double tinhTienThue() {
		double tong = 0;
		int row = tableCTHoaDon.getRowCount();
		for (int i = 0; i < row; i++) {
			String stringGiaThue = (String) tableCTHoaDon.getValueAt(i, 3);
			String stringSoLuong = (String) tableCTHoaDon.getValueAt(i, 2);
			double giaThue = Double.valueOf(stringGiaThue);
			int soLuong = Integer.valueOf(stringSoLuong);
			tong = tong + giaThue * soLuong;
		}
		return tong;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTraBangDia)) {
			HD = new QuanLyHoaDon();
			HD.traHoaDon(maHD);
			BD = new QuanLyBangDia();
			int rowCount = tableCTHoaDon.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				String maBD = (String) tableCTHoaDon.getValueAt(i, 0);
				int soLuong = Integer.valueOf((String) tableCTHoaDon.getValueAt(i, 2));
				BD.traBangDia(maBD, soLuong);
			}
			JOptionPane.showMessageDialog(this, "Trả băng đĩa thành công!");
			setVisible(false);
			GiaoDienChiTietPhieuThue t = new GiaoDienChiTietPhieuThue(maHD, 1,maTK);
			t.setVisible(true);
		} else if (o.equals(btnTroVe)) {
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		}
	}
}
