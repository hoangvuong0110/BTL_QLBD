package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DieuKhien.QuanLyBangDia;
import DieuKhien.QuanLyNhaCungCap;
import DieuKhien.QuanLyNhanVien;
import DieuKhien.QuanLyTheLoai;
import DoiTuong.BangDia;
import DoiTuong.NhaCungCap;
import DoiTuong.TheLoai;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GiaoDienTimKiemBangDia extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tablemodel;
	private JTextField txtBangDia;
	private JTextField txtMin;
	private JTextField txtMax;
	private JComboBox cboNCC, cboTheLoai;
	private JButton btnTimKiem, btnTroVe;
	private QuanLyBangDia BD;
	private QuanLyNhaCungCap NCC;
	private QuanLyTheLoai TL;
	private QuanLyNhanVien NV;
	private String maTK;


	public GiaoDienTimKiemBangDia(String maTK) {
		
		this.maTK = maTK;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 432);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Tìm Kiếm Băng Đĩa");
		label.setForeground(new Color(0, 0, 255));
		label.setFont(new Font("Tahoma", Font.PLAIN, 23));
		label.setBounds(136, 11, 200, 39);
		contentPane.add(label);

		JLabel label_1 = new JLabel("Tên Băng Đĩa:");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_1.setBounds(39, 78, 103, 23);
		contentPane.add(label_1);

		txtBangDia = new JTextField();
		
		txtBangDia.setColumns(10);
		txtBangDia.setBounds(138, 81, 237, 20);
		contentPane.add(txtBangDia);

		JLabel label_2 = new JLabel("Thể Loại:");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_2.setBounds(69, 130, 73, 23);
		contentPane.add(label_2);

		cboTheLoai = new JComboBox();
		cboTheLoai.setBounds(138, 132, 237, 22);
		contentPane.add(cboTheLoai);

		JLabel label_3 = new JLabel("Nhà Cung Cấp:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(40, 181, 102, 22);
		contentPane.add(label_3);

		cboNCC = new JComboBox();
		cboNCC.setBounds(138, 183, 237, 22);
		contentPane.add(cboNCC);

		JLabel lblGiThuTi = new JLabel("Giá Thuê Tối Thiểu:");
		lblGiThuTi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiThuTi.setBounds(10, 237, 132, 22);
		contentPane.add(lblGiThuTi);

		txtMin = new JTextField();
		txtMin.setText("0");
		txtMin.setColumns(10);
		txtMin.setBounds(138, 240, 237, 20);
		contentPane.add(txtMin);

		txtMax = new JTextField();
		txtMax.setText("900000");
		txtMax.setColumns(10);
		txtMax.setBounds(138, 286, 237, 20);
		contentPane.add(txtMax);

		JLabel label_6 = new JLabel("VND");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_6.setBounds(380, 241, 27, 14);
		contentPane.add(label_6);

		JLabel lblGiThuTi_1 = new JLabel("Giá Thuê Tối Đa:");
		lblGiThuTi_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiThuTi_1.setBounds(27, 283, 115, 22);
		contentPane.add(lblGiThuTi_1);

		JLabel label_4 = new JLabel("VND");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(380, 287, 27, 14);
		contentPane.add(label_4);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBackground(Color.LIGHT_GRAY);
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(95, 350, 110, 25);
		btnTimKiem.setIcon(new ImageIcon("icon/search_16px.png"));
		contentPane.add(btnTimKiem);

		btnTroVe = new JButton("Trở Về");
		btnTroVe.setBackground(Color.LIGHT_GRAY);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setBounds(260, 350, 110, 23);
		btnTroVe.setIcon(new ImageIcon("icon/back_to_16px.png"));
		contentPane.add(btnTroVe);

		btnTimKiem.addActionListener(this);
		btnTroVe.addActionListener(this);

		XuatTTCombobox();

	}

	private void XuatTTCombobox() {
		cboTheLoai.addItem("---------------------------------------------------");
		cboNCC.addItem("---------------------------------------------------");
		TL = new QuanLyTheLoai();
		NCC = new QuanLyNhaCungCap();
		List<TheLoai> listTL = TL.docTuBang();
		for (TheLoai theLoai : listTL) {
			cboTheLoai.addItem(theLoai.getTenTheLoai());
		}
		List<NhaCungCap> listNCC = NCC.docTuBang();
		for (NhaCungCap nhaCungCap : listNCC) {
			cboNCC.addItem(nhaCungCap.getTenNCC());
		}
	}

	/*
	 * private void timKiem(String tenBD, String nCC, String theLoai, double min,
	 * double max) { int rowCount = table.getRowCount(); for (int i = rowCount; i >
	 * 0; i--) { tablemodel.removeRow(i - 1); } BD = new QuanLyBangDia();
	 * List<BangDia> list = BD.timKiem(tenBD, nCC, theLoai, min, max); for (BangDia
	 * bangDia : list) { String[] rowData = { bangDia.getMaBangDia(),
	 * bangDia.getTenBangDia(), bangDia.getTheLoai(), bangDia.getNhaCungCap(),
	 * bangDia.getSoLuong() + "", bangDia.getGiaThue() + "" };
	 * tablemodel.addRow(rowData); } table.setModel(tablemodel); }
	 * 
	 * private void capNhatBangDuLieu() { int rowCount = table.getRowCount(); for
	 * (int i = rowCount; i > 0; i--) { tablemodel.removeRow(i - 1); } BD = new
	 * QuanLyBangDia(); List<BangDia> list = BD.docTuBangBangDia(); for (BangDia
	 * bangDia : list) { String[] rowData = { bangDia.getMaBangDia(),
	 * bangDia.getTenBangDia(), bangDia.getTheLoai(), bangDia.getNhaCungCap(),
	 * bangDia.getSoLuong() + "", bangDia.getGiaThue() + "" };
	 * tablemodel.addRow(rowData); } table.setModel(tablemodel); }
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			String tenBd = txtBangDia.getText();
			String nCC = (String) cboNCC.getSelectedItem();
			if (nCC.equals("---------------------------------------------------"))
				nCC = "";
			String theLoai = (String) cboTheLoai.getSelectedItem();
			if (theLoai.equals("---------------------------------------------------"))
				theLoai = "";
			String strMin = txtMin.getText();
			String strMax = txtMax.getText();
			double min = Double.parseDouble(strMin);
			double max = Double.parseDouble(strMax);
			GiaoDienQuanLyBangDia bd = new GiaoDienQuanLyBangDia(tenBd, nCC, theLoai, min, max,maTK);
			bd.setVisible(true);
			setVisible(false);
		} else if (o.equals(btnTroVe)) {
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		}

	}
}
