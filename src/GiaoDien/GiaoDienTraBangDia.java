package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DieuKhien.QuanLyHoaDon;
import DieuKhien.QuanLyNhanVien;
import DoiTuong.HoaDon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GiaoDienTraBangDia extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtTimKiem;
	private DefaultTableModel tablemodel;
	private QuanLyHoaDon HD;
	private JButton btnTraBangDia, btnTroVe, btnTimKiem;
	private String maTK;
	private QuanLyNhanVien NV;

	public GiaoDienTraBangDia(String maTK) {
		this.maTK = maTK;
		setTitle("Trả Băng Đĩa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 594);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] colNameHD = "Mã Phiếu Thuê;Tên Khách Hàng;Tên Nhân Viên;Ngày Lập Phiếu".split(";");
		tablemodel = new DefaultTableModel(colNameHD, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tablemodel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(28);
		scrollPane.setBounds(20, 57, 905, 391);
		contentPane.add(scrollPane);

		capNhatBangDuLieuHoaDon();

		JLabel lblMPhiuThu = new JLabel("Tên Khách Hàng:");
		lblMPhiuThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMPhiuThu.setBounds(10, 504, 140, 22);
		contentPane.add(lblMPhiuThu);

		txtTimKiem = new JTextField();
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtTimKiem.getText().equals("")) {
					capNhatBangDuLieuHoaDon();
				}
			}
			
		});
//		txtTimKiem.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				if(txtTimKiem.getText().equals("")) {
//					capNhatBangDuLieuHoaDon();
//				}
//				capNhatBangDuLieuTimKiemHoaDon();
//			}
//		});
		txtTimKiem.setBounds(136, 507, 167, 20);
		contentPane.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBackground(Color.LIGHT_GRAY);
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(313, 504, 116, 23);
		btnTimKiem.setIcon(new ImageIcon("icon/search_16px.png"));
		contentPane.add(btnTimKiem);

		btnTraBangDia = new JButton("Trả Băng Đĩa");

	
		btnTraBangDia.setBackground(Color.LIGHT_GRAY);
		btnTraBangDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTraBangDia.setBounds(502, 494, 178, 43);
		btnTraBangDia.setIcon(new ImageIcon("icon/refund_16px.png"));
		contentPane.add(btnTraBangDia);

		JLabel lblTrBnga = new JLabel("Trả Băng Đĩa");
		lblTrBnga.setForeground(Color.BLUE);
		lblTrBnga.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTrBnga.setBounds(388, 11, 178, 35);
		contentPane.add(lblTrBnga);

		btnTroVe = new JButton("Trở Về");
		btnTroVe.setBackground(Color.LIGHT_GRAY);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setBounds(773, 504, 116, 23);
		btnTroVe.setIcon(new ImageIcon("icon/back_to_16px.png"));
		contentPane.add(btnTroVe);

		btnTraBangDia.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnTroVe.addActionListener(this);
	}

	private void capNhatBangDuLieuHoaDon() {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tablemodel.removeRow(i - 1);
		}
		HD = new QuanLyHoaDon();
		List<HoaDon> list = HD.docTuBang1();
		for (HoaDon hoaDon : list) {
			String[] rowData = { hoaDon.getMaHoaDon(), hoaDon.getKhachHang().getHoTen(), hoaDon.getNhanVien().getTenNV(),
					hoaDon.getNgayLapHoaDon().toString() };
			tablemodel.addRow(rowData);
		}
		table.setModel(tablemodel);
	}
	private void capNhatBangDuLieuTimKiemHoaDon() {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tablemodel.removeRow(i - 1);
		}
		HD = new QuanLyHoaDon();
		List<HoaDon> list = HD.timKiemHoaDonTheoTen(txtTimKiem.getText().trim());
		for (HoaDon hoaDon : list) {
			String[] rowData = { hoaDon.getMaHoaDon(), hoaDon.getKhachHang().getHoTen(), hoaDon.getNhanVien().getTenNV(),
					hoaDon.getNgayLapHoaDon().toString() };
			tablemodel.addRow(rowData);
		}
		table.setModel(tablemodel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTraBangDia)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				HD = new QuanLyHoaDon();
				String maHD = (String) table.getValueAt(row, 0);
				GiaoDienChiTietPhieuThue hd = new GiaoDienChiTietPhieuThue(maHD, 0,maTK);
				hd.setVisible(true);
				setVisible(false);
			}
		} else if (o.equals(btnTroVe)) {
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		}else if(o.equals(btnTimKiem)) {
			capNhatBangDuLieuTimKiemHoaDon();
		}

	}
}
