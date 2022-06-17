package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Database.Database;
import DatePicker.DateLabelFormatter;
import DieuKhien.QuanLyKhachHang;
import DieuKhien.QuanLyNhanVien;
import DoiTuong.KhachHang;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GiaoDienQuanLyKhachHang extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btntimKiem, btnthem;
	private JButton btnluu, btnxoaRong;
	private JButton btnthoat;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblmaKH;
	private JLabel lbltenKH;
	private JLabel lblgioiTinh;
	private JLabel lblngaySinh;
	private JLabel lblngayDangKy;
	private JLabel lblcmnd;
	private JLabel lbldiaChi;
	private JLabel lblemail;
	private JTextField txtmaKH;
	private JTextField txttenKH;
	private JTextField txtcmnd;
	private JTextField txtdiaChi;
	private JTextField txtemail;
	private JRadioButton radnam, radnu;
	private Properties p;
	private UtilDateModel model1, model2;
	private JDatePanelImpl datePanel1, datePanel2;
	private JDatePickerImpl datePicker1, datePicker2;
	private JTextField txtsdt;
	private QuanLyKhachHang qlkh;
	private ButtonGroup group;
	private JTextField txtTKSDT;
	private String maTK;
	private QuanLyNhanVien NV;
	private JTextField txtTrang;
	private JButton btnNext;
	private JButton btnPre;
	private ArrayList<KhachHang> list = qlkh.phanTrang(1, 20);;
	
	private int page = 1;
	private int maxPage = 0;
	private JButton btnFirstPage;
	private JButton btnLastPage;
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public GiaoDienQuanLyKhachHang(String maTK) {
		this.maTK = maTK;
		qlkh = new QuanLyKhachHang();
		taoGiaoDien();
		
		capNhatBangDuLieu();
	}

	public GiaoDienQuanLyKhachHang(String tenKh, String Sdt, String Cmnd, Date tuNgay, Date denNgay) {
		qlkh = new QuanLyKhachHang();
		taoGiaoDien();
		timKiemKH(tenKh, Sdt, Cmnd, tuNgay, denNgay);

	}

	public void taoGiaoDien() {
		setTitle("Quản Lý Khách Hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1266, 705);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] header = "Mã Khách Hàng; Tên Khách Hàng; Giới Tính; Ngày Sinh; Email; Số Điện Thoại".split(";");
		tableModel = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(29);
		table.addMouseListener(this);
		Database.getInstance().getConnection();

		scrollPane.setBounds(10, 11, 661, 603);
		contentPane.add(scrollPane);

		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(681, 11, 557, 643);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(138, 11, 346, 52);
		panel.add(lblNewLabel);

		lblmaKH = new JLabel("Mã Khách Hàng:");
		lblmaKH.setBounds(12, 90, 103, 25);
		panel.add(lblmaKH);

		lbltenKH = new JLabel("Tên Khách Hàng:");
		lbltenKH.setBounds(12, 141, 103, 25);
		panel.add(lbltenKH);

		lblgioiTinh = new JLabel("Giới Tính:");
		lblgioiTinh.setBounds(12, 193, 83, 25);
		panel.add(lblgioiTinh);

		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model1 = new UtilDateModel();
		datePanel1 = new JDatePanelImpl(model1, p);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		datePicker1.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker1.setBackground(Color.WHITE);
		panel.add(datePicker1);

		lblngaySinh = new JLabel("Ngày Sinh:");
		lblngaySinh.setBounds(12, 298, 62, 25);
		panel.add(lblngaySinh);
		datePicker1.setBounds(105, 298, 167, 24);

		model2 = new UtilDateModel();
		datePanel2 = new JDatePanelImpl(model2, p);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePicker2.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker2.setBackground(Color.WHITE);
		panel.add(datePicker2);

		lblngayDangKy = new JLabel("Ngày Đăng Ký:");
		lblngayDangKy.setBounds(291, 297, 103, 25);
		panel.add(lblngayDangKy);
		datePicker2.setBounds(380, 298, 167, 24);

		lblcmnd = new JLabel("CMND:");
		lblcmnd.setBounds(309, 243, 46, 25);
		panel.add(lblcmnd);

		lbldiaChi = new JLabel("Địa Chỉ:");
		lbldiaChi.setBounds(12, 356, 62, 25);
		panel.add(lbldiaChi);

		lblemail = new JLabel("Email:");
		lblemail.setBounds(12, 408, 46, 25);
		panel.add(lblemail);

		txtmaKH = new JTextField();
		txtmaKH.setEditable(false);
		txtmaKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtmaKH.setBackground(Color.WHITE);
		txtmaKH.setBounds(125, 90, 96, 24);
		panel.add(txtmaKH);
		txtmaKH.setColumns(10);

		txttenKH = new JTextField();
		txttenKH.setBackground(Color.WHITE);
		txttenKH.setColumns(10);
		txttenKH.setBounds(125, 141, 217, 24);
		panel.add(txttenKH);

		txtcmnd = new JTextField();
		txtcmnd.setBackground(Color.WHITE);
		txtcmnd.setColumns(10);
		txtcmnd.setBounds(365, 243, 182, 24);
		panel.add(txtcmnd);

		txtdiaChi = new JTextField();
		txtdiaChi.setBackground(Color.WHITE);
		txtdiaChi.setColumns(10);
		txtdiaChi.setBounds(105, 356, 442, 24);
		panel.add(txtdiaChi);

		txtemail = new JTextField();
		txtemail.setBackground(Color.WHITE);
		txtemail.setColumns(10);
		txtemail.setBounds(105, 408, 346, 24);
		panel.add(txtemail);

		group = new ButtonGroup();
		radnam = new JRadioButton("Nam");
		radnam.setBackground(Color.WHITE);
		radnam.setBounds(125, 193, 71, 25);
		group.add(radnam);
		panel.add(radnam);

		radnu = new JRadioButton("Nữ");
		radnu.setBackground(Color.WHITE);
		radnu.setBounds(198, 193, 62, 25);
		panel.add(radnu);
		group.add(radnu);

		JLabel label = new JLabel("Số Điện Thoại:");
		label.setBounds(12, 243, 83, 25);
		panel.add(label);

		txtsdt = new JTextField();
		txtsdt.setBackground(Color.WHITE);
		txtsdt.setColumns(10);
		txtsdt.setBounds(105, 243, 167, 24);
		panel.add(txtsdt);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Thanh C\u00F4ng C\u1EE5",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(8, 463, 539, 82);
		panel.add(panel_1);
		panel_1.setLayout(null);

		btnthem = new JButton("Thêm");
		btnthem.setForeground(Color.BLACK);
		btnthem.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\add_16px.png"));
		btnthem.setBounds(22, 32, 105, 25);
		panel_1.add(btnthem);
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setBackground(Color.LIGHT_GRAY);

		btnluu = new JButton("Cập Nhật");
		btnluu.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\update_16px.png"));
		btnluu.setBounds(145, 32, 115, 25);
		panel_1.add(btnluu);
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnluu.setBackground(Color.LIGHT_GRAY);

		btnxoaRong = new JButton("Xóa Rỗng");
		btnxoaRong.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\emptydelete_16px.png"));
		btnxoaRong.setBounds(281, 32, 115, 25);
		panel_1.add(btnxoaRong);
		btnxoaRong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnxoaRong.setBackground(Color.LIGHT_GRAY);

		btnthoat = new JButton("Trở Về");
		btnthoat.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\back_to_16px.png"));
		btnthoat.setBounds(420, 32, 105, 25);
		panel_1.add(btnthoat);
		btnthoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthoat.setBackground(Color.LIGHT_GRAY);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Thanh t\u00ECm ki\u1EBFm",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(12, 561, 535, 69);
		panel.add(panel_2);
		panel_2.setLayout(null);

		btntimKiem = new JButton("Tìm kiếm");
		btntimKiem.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\search_16px.png"));
		btntimKiem.setBounds(410, 25, 110, 25);
		panel_2.add(btntimKiem);
		btntimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btntimKiem.setBackground(Color.LIGHT_GRAY);

		JLabel label_1 = new JLabel("Số Điện Thoại:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(10, 25, 103, 25);
		panel_2.add(label_1);

		txtTKSDT = new JTextField();
		txtTKSDT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtTKSDT.getText().equals("")) {
					reMove();
					
				}
				capNhatBangDuLieuTimKiem(txtTKSDT.getText());
				
			}
		});

		txtTKSDT.setBounds(123, 29, 277, 20);
		panel_2.add(txtTKSDT);
		txtTKSDT.setColumns(10);
		btntimKiem.addActionListener(this);
		btnthoat.addActionListener(this);
		btnxoaRong.addActionListener(this);
		btnluu.addActionListener(this);
		btnthem.addActionListener(this);
	
		radnam.setSelected(true);
		
		btnFirstPage = new JButton("");
		btnFirstPage.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\rewind_16px.png"));
		btnFirstPage.setBounds(72, 625, 59, 23);
		contentPane.add(btnFirstPage);
		
		btnPre = new JButton("");
		btnPre.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\back_to_16px.png"));
		btnPre.setBounds(177, 625, 59, 23);
		contentPane.add(btnPre);
		
		btnNext = new JButton("");
		btnNext.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\next_page_16px.png"));
		btnNext.setBounds(427, 625, 59, 23);
		contentPane.add(btnNext);
		
		btnLastPage = new JButton("");
		btnLastPage.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\fast_forward_16px.png"));
		btnLastPage.setBounds(541, 625, 59, 23);
		contentPane.add(btnLastPage);
		
		txtTrang = new JTextField();
		txtTrang.setForeground(Color.RED);
		txtTrang.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrang.setText("1");
		txtTrang.setBounds(303, 625, 66, 20);
		contentPane.add(txtTrang);
		txtTrang.setColumns(10);
		model1.setValue(Date.valueOf(LocalDate.now()));
		model2.setValue(Date.valueOf(LocalDate.now()));
		
		if(qlkh.getTotalofTableCustomer() % 20 == 0)
			maxPage = qlkh.getTotalofTableCustomer() / 20;
		else maxPage = qlkh.getTotalofTableCustomer() /20 + 1;
	//	System.out.println(maxPage);
		btnNext.addActionListener(this);
		btnPre.addActionListener(this);
		btnFirstPage.addActionListener(this);
			btnLastPage.addActionListener(this);
	}
	
	private void capNhatBangDuLieu() {
		reMove();
		qlkh = new QuanLyKhachHang();
		for (KhachHang khachHang : list) {
			String[] rowData = { khachHang.getMaKH(), khachHang.getHoTen(), khachHang.getGioiTinh(),
					khachHang.getNgaySinh().toString(), khachHang.getEmail(), khachHang.getSdt() };
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
	}
	private void capNhatBangDuLieuCapNhat() {
		reMove();
		qlkh = new QuanLyKhachHang();
		list = qlkh.phanTrang((page-1)*20+1, page*20);
			for (KhachHang khachHang : list) {
				String[] rowData = { khachHang.getMaKH(), khachHang.getHoTen(), khachHang.getGioiTinh(),
						khachHang.getNgaySinh().toString(), khachHang.getEmail(), khachHang.getSdt() };
				tableModel.addRow(rowData);
			}
			table.setModel(tableModel);
			page = this.page;
	}
	private void timKiemKH(String tenKh, String Sdt, String Cmnd, Date tuNgay, Date denNgay) {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tableModel.removeRow(i - 1);
		}
		qlkh = new QuanLyKhachHang();
		 list = qlkh.timKiemKH(tenKh, Sdt, Cmnd, tuNgay, denNgay);
		for (KhachHang khachHang : list) {
			String[] rowData = { khachHang.getMaKH(), khachHang.getHoTen(), khachHang.getGioiTinh(),
					khachHang.getNgaySinh().toString(), khachHang.getEmail(), khachHang.getSdt() };
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
	}

	private void capNhatBangDuLieuTimKiem(String soDienThoai) {
			if(!soDienThoai.equals("")) {
				reMove();
				qlkh = new QuanLyKhachHang();
				list = qlkh.timKiemTheoSDTKhachHang(soDienThoai);
				for (KhachHang khachHang : list) {
					String[] rowData = { khachHang.getMaKH(), khachHang.getHoTen(), khachHang.getGioiTinh(),
							khachHang.getNgaySinh().toString(), khachHang.getEmail(), khachHang.getSdt() };
					tableModel.addRow(rowData);
				}
				table.setModel(tableModel);
			}
		
	}

	private void dienThongTin(int row) {
		if (row != -1) {
			btnthem.setVisible(false);
			qlkh = new QuanLyKhachHang();
			String ma = (String) table.getValueAt(row, 0);
			// JOptionPane.showMessageDialog(this, ma);
			// String ma="123";
			 list = qlkh.docTuBang();
			for (KhachHang kh : list) {
				if (ma.trim().equals(kh.getMaKH().trim())) {
					// JOptionPane.showMessageDialog(this, "Chay dc");
					txtmaKH.setText(ma.trim());
					txttenKH.setText(kh.getHoTen());
					String gioiTinh;
					if (kh.getGioiTinh().trim().equals("Nam"))
						radnam.setSelected(true);
					else
						radnu.setSelected(true);
					txtcmnd.setText(kh.getcMND());
					model1.setValue(kh.getNgaySinh());
					model2.setValue(kh.getNgayDK());
					txtdiaChi.setText(kh.getDiaChi());
					txtemail.setText(kh.getEmail());
					txtsdt.setText(kh.getSdt());
				}

			}
		}
	}

	private boolean kiemTraDinhDang() {
		String ten = txttenKH.getText();
		int namSinh = model1.getYear();
		Date ngayDangKy = Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() + 1, model2.getDay()));
		String cCCD = txtcmnd.getText();
		txtcmnd.setEditable(false);
		String sdt = txtsdt.getText();
		String diachi = txtdiaChi.getText();
		String email = txtemail.getText();
		// String ghi = txtghiChu.getText();
		if (ten.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Tên không được để trống!");
			txttenKH.requestFocus();
			txttenKH.selectAll();
			return false;
		} else if (!ten.matches("^[\\p{L} ]+$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng điền tên chính xác!");
			txttenKH.requestFocus();
			txttenKH.selectAll();
			return false;
		} else if (cCCD.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "CCCD/CMND không được để trống!");
			txtcmnd.requestFocus();
			txtcmnd.selectAll();
			return false;
		} else if (!cCCD.matches("[0-9]{9}") && !cCCD.matches("[0-9]{12}")) {
			JOptionPane.showMessageDialog(this, "CCCD phải là số và phải đủ 12 số(CMND phải là số và phải đủ 9 số) !");
			txtcmnd.requestFocus();
			txtcmnd.selectAll();
			return false;
		} else if (sdt.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
			txtsdt.requestFocus();
			txtsdt.selectAll();
			return false;
		} else if (!sdt.matches("[0-9]{10}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải là số và phải đủ 10 số!");
			txtsdt.requestFocus();
			txtsdt.selectAll();
			return false;
		} else if (email.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Email không được để trống");
			txtemail.requestFocus();
			txtemail.selectAll();
			return false;
		} else if (!email.matches("^([a-z0-9_])+@([\\da-z])+\\.([a-z\\.]{2,6})$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng Email!");
			txtemail.requestFocus();
			txtemail.selectAll();
			return false;
		} else if (diachi.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
			txtdiaChi.requestFocus();
			txtdiaChi.selectAll();
			return false;
		} else if (!diachi.matches("^[\\p{L}0-9 -/]+$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng địa chỉ!");
			txtdiaChi.requestFocus();
			txtdiaChi.selectAll();
			return false;
		} else if (namSinh >= 2002) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tuổi trên 18!");
			return false;
		} else if (ngayDangKy.after(Date.valueOf(LocalDate.now()))) {
			JOptionPane.showMessageDialog(this, "Nhập ngày đăng ký phải trước ngày hiện tại!");
			return false;
		}
		qlkh = new QuanLyKhachHang();
		List<KhachHang> list = qlkh.docTuBang();
		for (KhachHang khachHang : list) {
			if (sdt.trim().equals(khachHang.getSdt().trim())) {
			//	JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại trong hệ thống !");
				int value =  (Integer)(JOptionPane.showConfirmDialog(this, "Số điện thoại đã tồn tại! Vui lòng xác nhận đó có phải là số điện thoại của bạn", "Xác Nhận", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE));
				if(value ==JOptionPane.YES_OPTION) {
					return true;
				}else {
					JOptionPane.showMessageDialog(this, "Nhập lại số điện thoại mới");
					txtsdt.requestFocus();
					txtsdt.selectAll();
					return false;
				}
				
			}
//			if (cCCD.equals(khachHang.getcMND().trim())) {
//				System.out.println(cCCD);
//				System.out.println(khachHang.getcMND().trim());
//				JOptionPane.showMessageDialog(this, "CMND không được thay đổi!");
//				txtcmnd.requestFocus();
//				txtcmnd.selectAll();
//				return false;
//			}
		}
		reMove();

		return true;
	}

	private void xoaRong() {
		btnthem.setVisible(true);
		txtmaKH.setText("");
		txttenKH.setText("");
		radnam.setSelected(false);
		radnu.setSelected(false);
		txtcmnd.setText("");
		txtdiaChi.setText("");
		txtemail.setText("");
		txtsdt.setText("");

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(table)) {
			dienThongTin(table.getSelectedRow());
			txtmaKH.setEditable(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob.equals(btnthem)) {
			if (kiemTraDinhDang()) {
				qlkh = new QuanLyKhachHang();
				String ma = qlkh.tuDongLayMa();
				String ten = txttenKH.getText();
				String gioiTinh;
				if (radnam.isSelected())
					gioiTinh = "Nam";
				else
					gioiTinh = "Nữ";
				String cmnd = txtcmnd.getText();
				Date ngaySinh = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() + 1, model1.getDay()));
				Date ngayDK = Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() + 1, model2.getDay()));
				String diaChi = txtdiaChi.getText();
				String email = txtemail.getText();
				String sdt = txtsdt.getText();
				if (qlkh.themKhachHang(ma, ten, gioiTinh, ngaySinh, ngayDK, cmnd, diaChi, email, sdt)) {		
					comeToLastPage();
					JOptionPane.showMessageDialog(this, "Thêm thành công!");
					
				} else
					JOptionPane.showMessageDialog(this, "Thêm không thành công!");
			}

		} else if (ob.equals(btnluu)) {
			if (kiemTraDinhDang()) {
				String ma = txtmaKH.getText();
				String ten = txttenKH.getText();
				String gioiTinh;
				if (radnam.isSelected())
					gioiTinh = "Nam";
				else
					gioiTinh = "Nữ";
				String cmnd = txtcmnd.getText();
				Date ngaySinh = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() + 1, model1.getDay()));
				Date ngayDK = Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() + 1, model2.getDay()));
				String diaChi = txtdiaChi.getText();
				String email = txtemail.getText();
				String sdt = txtsdt.getText();
				if (qlkh.capNhat(ma, ten, gioiTinh, ngaySinh, ngayDK, cmnd, diaChi, email, sdt)) {				
					xoaRong();
					JOptionPane.showMessageDialog(this, "Lưu thành công!");
					capNhatBangDuLieuCapNhat();
					
				} else
					JOptionPane.showMessageDialog(this, "Lưu không thành công!");

			}
		} else if (ob.equals(btnthoat)) {
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		} else if (ob.equals(btnxoaRong)) {
			xoaRong();
			reMove();
		} else if (ob.equals(btntimKiem)) {
			String soDienThoai = txtTKSDT.getText();
			capNhatBangDuLieuTimKiem(soDienThoai);
		}
		else if (ob.equals(btnNext)) {
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
	public void backUpFirstPage() {
		this.page = 1;
		txtTrang.setText(page+"");
		list = qlkh.phanTrang(1,20);
		capNhatBangDuLieu();
	}
	public void comeToLastPage() {
		this.page = maxPage;
		txtTrang.setText(maxPage+"");
		list = qlkh.phanTrang((maxPage-1)*20+1,maxPage*20);
		capNhatBangDuLieu();
	}
	public void nextPage() {
		if(this.page < maxPage) {
			//reMove();
			this.page++;
			txtTrang.setText(page+"");
			list = qlkh.phanTrang((page-1)*20+1, page*20);
			
			capNhatBangDuLieu();
		}
	}
	public void prePage() {
		if(this.page > 1) {
			this.page--;
			txtTrang.setText(page+"");
			list = qlkh.phanTrang((page-1)*20+1, (page)*20);
			//reMove();
			capNhatBangDuLieu();
		}
	}
	public void reMove() {
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();
	}
}
