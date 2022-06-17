package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Database.Database;
import DieuKhien.QuanLyHoaDon;
import DieuKhien.QuanLyKhachHang;
import DieuKhien.QuanLyNhanVien;
import DoiTuong.KhachHang;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class GiaoDienDanhSachKhachHang extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private QuanLyKhachHang qlkh;
	private QuanLyHoaDon HD;
	private List<KhachHang> dskh;
	private JTextField txtTKSDT;
	private JButton btntimKiem, btnthem, btnthueBangDia;
	private JLabel lblsdt;
	private JButton btnTroVe;
	private String maNV;
	private QuanLyNhanVien NV;
	private String maTK;
	private JTextField textField;
	private JTextField txtSoTrang;
	private JButton btnFirstPage;
	private JButton btnPre;
	private JButton btnNext;
	private JButton btnLastPage;
	private ArrayList<KhachHang> list = qlkh.phanTrang(1, 15);;
	
	private int page = 1;
	private int maxPage = 0;
	public GiaoDienDanhSachKhachHang(String maNV,String maTK) {
		this.maNV = maNV;
		this.maTK = maTK;
		setTitle("Giao Diện Khách Hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1068, 730);
		setResizable(false);
		setLocationRelativeTo(null);
		taoGiaoDien();
	}

	public void taoGiaoDien() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] header = "Mã Khách Hàng; Tên Khách Hàng; Số Điện Thoại ; CMND".split(";");
		tableModel = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(29);
		contentPane.add(scrollPane);
		scrollPane.setBounds(12, 74, 1030, 469);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thanh T\u00ECm Ki\u1EBFm ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.setBounds(12, 597, 462, 83);
		contentPane.add(panel);
		panel.setLayout(null);

		lblsdt = new JLabel("Số Điện Thoại:");
		lblsdt.setBounds(10, 33, 95, 25);
		panel.add(lblsdt);

		txtTKSDT = new JTextField();


		txtTKSDT.setBounds(94, 33, 196, 24);
		panel.add(txtTKSDT);
		txtTKSDT.setColumns(10);
		txtTKSDT.setBackground(Color.WHITE);

		btntimKiem = new JButton("Tìm Kiếm");
		btntimKiem.setBackground(Color.LIGHT_GRAY);
		btntimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btntimKiem.setBounds(310, 33, 129, 25);
		btntimKiem.setIcon(new ImageIcon("icon/search_16px.png"));
		panel.add(btntimKiem);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "C\u00E1c Thao T\u00E1c",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(484, 597, 558, 83);
		contentPane.add(panel_1);

		btnthem = new JButton("Thêm Khách Hàng");
		btnthem.setBackground(Color.LIGHT_GRAY);
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setBounds(21, 33, 171, 26);
		btnthem.setIcon(new ImageIcon("icon/add_16px.png"));
		panel_1.add(btnthem);


		btnthueBangDia = new JButton("Thuê CD");
		btnthueBangDia.setBackground(Color.LIGHT_GRAY);
		btnthueBangDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthueBangDia.setBounds(221, 33, 153, 26);
		btnthueBangDia.setIcon(new ImageIcon("icon/rent_16px.png"));
		panel_1.add(btnthueBangDia);
		//btnthueBangDia.setBackground(Color.GREEN);

		btnTroVe = new JButton("Trở Về");
		btnTroVe.setBackground(Color.LIGHT_GRAY);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setIcon(new ImageIcon("icon/back_to_16px.png"));
		btnTroVe.setBounds(407, 33, 129, 26);
		panel_1.add(btnTroVe);

		JLabel lblDanhSchKhch = new JLabel("Danh Sách Khách Hàng");
		lblDanhSchKhch.setForeground(Color.BLUE);
		lblDanhSchKhch.setFont(new Font("Arial", Font.BOLD, 30));
		lblDanhSchKhch.setBounds(345, 11, 392, 52);
		contentPane.add(lblDanhSchKhch);
		
		btnFirstPage = new JButton("");
		btnFirstPage.setBackground(UIManager.getColor("Button.background"));
	
		btnFirstPage.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\rewind_16px.png"));
		btnFirstPage.setBounds(121, 551, 89, 23);
		contentPane.add(btnFirstPage);
		
		btnPre = new JButton("");
		btnPre.setBackground(UIManager.getColor("Button.background"));
		btnPre.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\back_to_16px.png"));
		btnPre.setBounds(309, 551, 89, 23);
		contentPane.add(btnPre);
		
		btnNext = new JButton("");
		btnNext.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\next_page_16px.png"));
		btnNext.setBounds(605, 551, 89, 23);
		contentPane.add(btnNext);
		
		btnLastPage = new JButton("");
		btnLastPage.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\fast_forward_16px.png"));
		btnLastPage.setBounds(775, 551, 89, 23);
		contentPane.add(btnLastPage);
		
		textField = new JTextField();
		textField.setBounds(445, 566, 46, -5);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtSoTrang = new JTextField();
		txtSoTrang.setForeground(Color.RED);
		txtSoTrang.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoTrang.setText("1");
		txtSoTrang.setBounds(467, 552, 60, 20);
		contentPane.add(txtSoTrang);
		txtSoTrang.setColumns(10);
		btntimKiem.addActionListener(this);
		btnthueBangDia.addActionListener(this);
		btnthem.addActionListener(this);
		btnTroVe.addActionListener(this);
		btnFirstPage.addActionListener(this);
		btnLastPage.addActionListener(this);
		btnNext.addActionListener(this);
		btnPre.addActionListener(this);
		if(qlkh.getTotalofTableCustomer() % 15 == 0)
			maxPage = qlkh.getTotalofTableCustomer() / 15;
		else maxPage = qlkh.getTotalofTableCustomer() /15 + 1;
		capNhatBangDuLieu();
	}

	private void capNhatBangDuLieu() {
		reMove();
		qlkh = new QuanLyKhachHang();
		for (KhachHang khachHang : list) {
			String[] rowData = { khachHang.getMaKH(), khachHang.getHoTen(), khachHang.getSdt(), khachHang.getcMND() };
			tableModel.addRow(rowData);
			// cbotimKiem.addItem(khachHang.getHoTen());
		}
		table.setModel(tableModel);
	}
	public void backUpFirstPage() {
		this.page = 1;
		txtSoTrang.setText(page+"");
		list = qlkh.phanTrang(1,15);
		capNhatBangDuLieu();
	}
	public void comeToLastPage() {
		this.page = maxPage;
		txtSoTrang.setText(maxPage+"");
		list = qlkh.phanTrang((maxPage-1)*15+1,maxPage*15);
		capNhatBangDuLieu();
	}
	public void nextPage() {
		if(this.page < maxPage) {
			this.page++;
			txtSoTrang.setText(page+"");
			list = qlkh.phanTrang((page-1)*15+1, page*15);		
			capNhatBangDuLieu();
		}
	}
	public void prePage() {
		if(this.page > 1) {
			this.page--;
			txtSoTrang.setText(page+"");
			list = qlkh.phanTrang((page-1)*15+1, (page)*15);
			capNhatBangDuLieu();
		}
	}
	public void reMove() {
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();
	}
	private void capNhatBangDuLieuTimKiem(String soDienThoai) {
		reMove();
		qlkh = new QuanLyKhachHang();
		list = qlkh.timKiemTheoSDTKhachHang(soDienThoai);
		for (KhachHang khachHang : list) {
			String[] rowData = { khachHang.getMaKH(), khachHang.getHoTen(), khachHang.getSdt(), khachHang.getcMND() };
			tableModel.addRow(rowData);
			// cbotimKiem.addItem(khachHang.getHoTen());
		}
		table.setModel(tableModel);
	}

	private boolean kiemTraKhachHang(String maKH) {
		qlkh = new QuanLyKhachHang();
		list = qlkh.kiemTraKhachHangDangThue();
		for (KhachHang khachHang : list) {
			if (maKH.trim().equals(khachHang.getMaKH().trim()))
				return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob.equals(btntimKiem)) {
			String soDienThoai = txtTKSDT.getText();
			capNhatBangDuLieuTimKiem(soDienThoai.trim());

		} else if (ob.equals(btnthem)) {
			GiaoDienThemKhachHang kh = new GiaoDienThemKhachHang(maNV,maTK);
			kh.setVisible(true);
			setVisible(false);

		} else if (ob.equals(btnthueBangDia)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				String maKH = (String) table.getValueAt(row, 0);
				if (kiemTraKhachHang(maKH)) {
					GiaoDienThueBangDia thue = new GiaoDienThueBangDia(maKH, maNV,maTK);
					thue.setVisible(true);
					setVisible(false);
				} else
					JOptionPane.showMessageDialog(this, "Khách Hàng Đang Có Phiếu Thuê Chưa Trả !");
			} else 
				JOptionPane.showMessageDialog(this, "Hãy chọn một khách hàng !");
		} else if (ob.equals(btnTroVe)) {
		
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		}else if (ob.equals(btnNext)) {
			nextPage();
		}
		else if (ob.equals(btnPre)) {
			prePage();
		}else if(ob.equals(btnFirstPage)) {
			backUpFirstPage();
		}else if(ob.equals(btnLastPage)) {
			comeToLastPage();
		}

	}
}
