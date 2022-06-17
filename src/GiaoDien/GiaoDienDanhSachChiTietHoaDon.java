package GiaoDien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import DatePicker.DateLabelFormatter;
import DieuKhien.QuanLyBangDia;
import DieuKhien.QuanLyChiTietHoaDon;
import DieuKhien.QuanLyHoaDon;
import DieuKhien.QuanLyNhanVien;
import DoiTuong.ChiTietHoaDon;
import DoiTuong.HoaDon;

public class GiaoDienDanhSachChiTietHoaDon extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable tableCTHoaDon;
	private DefaultTableModel tablemodelCT;
	private JTextField txtMaPhieuThue;
	private JTextField txtKhachHang;
	private JTextField txtNhanVien;
	private JTextField txtTong;
	private JTextField textNgayThue;
	private Properties p;
	private UtilDateModel modelNgayThue, modelNgayTra;
	private JDatePickerImpl datePickerNgayThue, datePickerNgayTra;
	private JDatePanelImpl datePanelNgayThue, datePanelNgayTra;
	private QuanLyChiTietHoaDon CT;
	private QuanLyHoaDon HD;
	private double tongThue = 0;
	private JButton btnTraBangDia, btnTroVe;
	private String maHD;
	private QuanLyBangDia BD;
	private QuanLyNhanVien NV;
	private String maTK;

	public GiaoDienDanhSachChiTietHoaDon(String maHD) {
		this.maHD = maHD;
		setTitle("Quản Lý Hóa Đơn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 730);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] colNameCT = "Mã Băng Đĩa;Tên Băng Đĩa;Số Lượng;Giá Thuê".split(";");
		tablemodelCT = new DefaultTableModel(colNameCT, 0);

		JLabel lblChiTitPhiu = new JLabel("Chi Tiết Phiếu Thuê");
		lblChiTitPhiu.setForeground(Color.BLUE);
		lblChiTitPhiu.setBounds(320, 11, 232, 35);
		contentPane.add(lblChiTitPhiu);
		lblChiTitPhiu.setFont(new Font("Sitka Small", Font.BOLD, 22));
		JScrollPane scrollPane_1 = new JScrollPane(tableCTHoaDon = new JTable(tablemodelCT),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tableCTHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableCTHoaDon.setRowHeight(28);
		scrollPane_1.setBounds(10, 285, 852, 345);
		contentPane.add(scrollPane_1);
		capNhapBangDuLieuChiTiet(maHD);

		JLabel lblMPhiuThu = new JLabel("Mã Phiếu Thuê:");
		lblMPhiuThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMPhiuThu.setBounds(35, 71, 100, 26);
		contentPane.add(lblMPhiuThu);

		JLabel lblTnKhchHng = new JLabel("Tên Khách Hàng:");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnKhchHng.setBounds(30, 138, 105, 26);
		contentPane.add(lblTnKhchHng);

		JLabel lblTnNhnVin = new JLabel("Tên Nhân Viên:");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnNhnVin.setBounds(35, 205, 100, 26);
		contentPane.add(lblTnNhnVin);

		JLabel lblNgyLpPhiu = new JLabel("Ngày Thuê :");
		lblNgyLpPhiu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgyLpPhiu.setBounds(453, 71, 84, 26);
		contentPane.add(lblNgyLpPhiu);

		JLabel lblNgyTr = new JLabel("Ngày Trả:");
		lblNgyTr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgyTr.setBounds(468, 137, 69, 28);
		contentPane.add(lblNgyTr);

		JLabel lblTngTin = new JLabel("Tổng Tiền:");
		lblTngTin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTngTin.setBounds(461, 205, 76, 26);
		contentPane.add(lblTngTin);

		JLabel lblVnd = new JLabel("VND");
		lblVnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVnd.setBounds(798, 211, 48, 14);
		contentPane.add(lblVnd);

		txtMaPhieuThue = new JTextField();
		txtMaPhieuThue.setEnabled(false);
		txtMaPhieuThue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaPhieuThue.setBounds(145, 74, 251, 20);
		contentPane.add(txtMaPhieuThue);
		txtMaPhieuThue.setColumns(10);

		txtKhachHang = new JTextField();
		txtKhachHang.setEnabled(false);
		txtKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtKhachHang.setBounds(145, 141, 251, 20);
		contentPane.add(txtKhachHang);
		txtKhachHang.setColumns(10);

		txtNhanVien = new JTextField();
		txtNhanVien.setEnabled(false);
		txtNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNhanVien.setBounds(145, 208, 251, 20);
		contentPane.add(txtNhanVien);
		txtNhanVien.setColumns(10);

		txtTong = new JTextField();
		txtTong.setEnabled(false);
		txtTong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTong.setBounds(547, 208, 241, 20);
		contentPane.add(txtTong);
		txtTong.setColumns(10);

		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		modelNgayThue = new UtilDateModel();
		datePanelNgayThue = new JDatePanelImpl(modelNgayThue, p);
		datePickerNgayThue = new JDatePickerImpl(datePanelNgayThue, new DateLabelFormatter());
		datePickerNgayThue.getJFormattedTextField().setBackground(Color.WHITE);
		datePickerNgayThue.setBackground(Color.WHITE);
		contentPane.add(datePickerNgayThue);
		datePickerNgayThue.setBounds(547, 77, 241, 20);

		modelNgayTra = new UtilDateModel();
		datePanelNgayTra = new JDatePanelImpl(modelNgayTra, p);
		datePickerNgayTra = new JDatePickerImpl(datePanelNgayTra, new DateLabelFormatter());
		datePickerNgayTra.getJFormattedTextField().setBackground(Color.WHITE);
		datePickerNgayTra.setBackground(Color.WHITE);
		contentPane.add(datePickerNgayTra);
		datePickerNgayTra.setBounds(547, 144, 241, 20);

		JButton btnInPhiuThu = new JButton("In Phiếu Thuê");
		btnInPhiuThu.setBackground(Color.LIGHT_GRAY);
		btnInPhiuThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInPhiuThu.setBounds(145, 653, 121, 26);
		contentPane.add(btnInPhiuThu);

		btnTroVe = new JButton("Trở Về");
		btnTroVe.setBackground(Color.LIGHT_GRAY);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setBounds(634, 653, 100, 26);
		contentPane.add(btnTroVe);

		btnTraBangDia = new JButton("Trả Băng Đĩa");
		btnTraBangDia.setBackground(Color.LIGHT_GRAY);
		btnTraBangDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTraBangDia.setBounds(377, 653, 121, 26);
		contentPane.add(btnTraBangDia);

		dienThongTin(maHD);

		btnTraBangDia.addActionListener(this);
		btnTroVe.addActionListener(this);

	}

	private void capNhapBangDuLieuChiTiet(String maHD) {

		int rowCount = tableCTHoaDon.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tablemodelCT.removeRow(i - 1);
		}
		CT = new QuanLyChiTietHoaDon();
		List<ChiTietHoaDon> list = CT.docTuBangChiTietHD(maHD);
		for (ChiTietHoaDon chiTietHoaDon : list) {
			String[] rowData = { chiTietHoaDon.getBangDia().getMaBangDia(), chiTietHoaDon.getBangDia().getTenBangDia(),
					chiTietHoaDon.getSoLuong() + "" };
			tablemodelCT.addRow(rowData);
		}
		tableCTHoaDon.setModel(tablemodelCT);
	}

	private void dienThongTin(String maHD) {
		HD = new QuanLyHoaDon();
		List<HoaDon> list = HD.docTuBang1();
		for (HoaDon hoaDon : list) {
			if (maHD.trim().equals(hoaDon.getMaHoaDon().trim())) {
				txtMaPhieuThue.setText(hoaDon.getMaHoaDon());
				txtNhanVien.setText(hoaDon.getNhanVien().getTenNV());
				txtKhachHang.setText(hoaDon.getKhachHang().getHoTen());
				modelNgayThue.setValue(hoaDon.getNgayLapHoaDon());
				// txtTong.setText(tongThue * hoaDon.getSoNgayThue() + "");
				//datePickerNgayThue.
			}

		}

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
			setVisible(false);
			GiaoDienChiTietPhieuThue pt = new GiaoDienChiTietPhieuThue(maHD, 1,maTK);
			pt.setVisible(true);
		} else if (o.equals(btnTroVe)) {
			setVisible(false);
		}
	}
}
