package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import DieuKhien.QuanLyBangDia;
import DieuKhien.QuanLyChiTietHoaDon;
import DieuKhien.QuanLyHoaDon;
import DieuKhien.QuanLyKhachHang;
import DieuKhien.QuanLyNhanVien;
import DieuKhien.QuanLyTheLoai;
import DoiTuong.BangDia;
import DoiTuong.ChiTietHoaDon;
import DoiTuong.KhachHang;
import DoiTuong.TheLoai;

import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class GiaoDienThueBangDia extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table, table1;
	private DefaultTableModel tablemodel, tablemodel1;
	private JTextField txtTenBangDia;
	private JTextField txtMin;
	private JTextField txtMax;
	private QuanLyBangDia BD;
	private QuanLyChiTietHoaDon CT;
	private QuanLyHoaDon HD;
	private QuanLyTheLoai TL;
	private QuanLyKhachHang KH;
	private JButton btnThemGioHang, btnXoaGioHang, btnTimKiem, btnXemAll, btnLapPhieu, btnHuy;
	private String maKH, maNV;
	private JComboBox cboTheLoai;
	private JTextField txtKhachHang;
	private JTextField txtSoDienThoai;
	private QuanLyNhanVien NV;
	private String maTK;
	private JButton btnFirstPage;
	private JButton btnPrePage;
	private JButton btnNextPage;
	private JButton btnLastPage;
	private JTextField txtSoTrang;
	private ArrayList<BangDia> list = BD.phanTrang(1, 15);
	
	private int page = 1;
	private int maxPage = 0;

	public GiaoDienThueBangDia(String maKH, String maNV,String maTK) {
		this.maTK = maTK;
		this.maKH = maKH;
		this.maNV = maNV;
		setTitle("Thuê Băng Đĩa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1228, 643);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] colName = "Mã Băng Đĩa;Tên Băng Đĩa;Thể Loại;Số Lượng;Giá Thuê".split(";");
		tablemodel = new DefaultTableModel(colName, 0);
		JScrollPane Pane = new JScrollPane(table = new JTable(tablemodel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setRowHeight(23);
		Pane.setBounds(10, 200, 578, 376);
		contentPane.add(Pane);

		JPanel panel = new JPanel();
		panel.setBounds(688, 205, 522, 397);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Gi\u1ECF H\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);

		String[] Name = "Mã Băng Đĩa;Tên Băng Đĩa;Số Lượng".split(";");
		tablemodel1 = new DefaultTableModel(Name, 0);
		JScrollPane scrollPane = new JScrollPane(table1 = new JTable(tablemodel1),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setBounds(10, 21, 502, 358);
		panel.add(scrollPane);

		btnThemGioHang = new JButton("");
		btnThemGioHang
				.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\chevron_right_30px.png"));
		btnThemGioHang.setBounds(598, 353, 80, 55);
		btnThemGioHang.setBackground(Color.WHITE);
		contentPane.add(btnThemGioHang);

		btnXoaGioHang = new JButton("");
		btnXoaGioHang
				.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\chevron_left_30px.png"));
		btnXoaGioHang.setBackground(Color.WHITE);
		btnXoaGioHang.setBounds(598, 419, 80, 55);
		contentPane.add(btnXoaGioHang);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"T\u00ECm Ki\u1EBFm B\u0103ng \u0110\u0129a", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 11, 426, 183);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblThLoi = new JLabel("Thể Loại :");
		lblThLoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThLoi.setBounds(36, 72, 68, 22);
		panel_1.add(lblThLoi);

		cboTheLoai = new JComboBox();
		cboTheLoai.setBounds(114, 74, 209, 22);
		panel_1.add(cboTheLoai);

		JLabel label_1 = new JLabel("Tên Băng Đĩa:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(10, 29, 94, 22);
		panel_1.add(label_1);

		txtTenBangDia = new JTextField();
		txtTenBangDia.setColumns(10);
		txtTenBangDia.setBounds(114, 31, 275, 22);
		panel_1.add(txtTenBangDia);

		JLabel lblGiThu = new JLabel("Giá Thuê :");
		lblGiThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiThu.setBounds(36, 105, 68, 22);
		panel_1.add(lblGiThu);

		txtMin = new JTextField();
		txtMin.setBounds(114, 107, 96, 20);
		panel_1.add(txtMin);
		txtMin.setColumns(10);

		JLabel label_2 = new JLabel("-");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(211, 109, 16, 14);
		panel_1.add(label_2);

		txtMax = new JTextField();
		txtMax.setColumns(10);
		txtMax.setBounds(220, 107, 96, 20);
		panel_1.add(txtMax);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBackground(Color.LIGHT_GRAY);
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(46, 144, 115, 23);
		btnTimKiem.setIcon(new ImageIcon("icon/search_16px.png"));
		panel_1.add(btnTimKiem);

		btnXemAll = new JButton("Danh Sách Băng Đĩa");
		btnXemAll.setBackground(Color.LIGHT_GRAY);
		btnXemAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXemAll.setBounds(189, 144, 200, 23);
		btnXemAll.setIcon(new ImageIcon("icon/list.png"));
		panel_1.add(btnXemAll);

		JLabel label_3 = new JLabel("VND");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(326, 109, 27, 14);
		panel_1.add(label_3);
		txtMin.setText("0");
		txtMax.setText("500000");

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Th\u00F4ng tin kh\u00E1ch h\u00E0ng ",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(765, 11, 445, 183);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		btnLapPhieu = new JButton("Lập Phiếu Thuê");
		btnLapPhieu.setBackground(Color.LIGHT_GRAY);
		btnLapPhieu.setBounds(74, 123, 150, 23);
		btnLapPhieu.setIcon(new ImageIcon("icon/create_16px.png"));
		panel_2.add(btnLapPhieu);
		btnLapPhieu.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnLapPhieu.addActionListener(this);

		btnHuy = new JButton("Hủy");
		btnHuy.setBackground(Color.LIGHT_GRAY);
		btnHuy.setBounds(304, 123, 103, 23);
		btnHuy.setIcon(new ImageIcon("icon/cancel_16px.png"));
		panel_2.add(btnHuy);
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnHuy.addActionListener(this);

		JLabel lblTnKhchHng = new JLabel("Tên Khách Hàng :");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnKhchHng.setBounds(54, 26, 115, 26);
		panel_2.add(lblTnKhchHng);

		txtKhachHang = new JTextField();
		txtKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtKhachHang.setEnabled(false);
		txtKhachHang.setColumns(10);
		txtKhachHang.setBounds(169, 30, 251, 20);
		panel_2.add(txtKhachHang);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại :");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSinThoi.setBounds(54, 75, 105, 26);
		panel_2.add(lblSinThoi);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoDienThoai.setEnabled(false);
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(169, 79, 251, 20);
		panel_2.add(txtSoDienThoai);

		JLabel lblThuBnga = new JLabel("Thuê Băng Đĩa");
		lblThuBnga.setForeground(Color.BLUE);
		lblThuBnga.setFont(new Font("Arial", Font.BOLD, 30));
		lblThuBnga.setBounds(498, 46, 233, 52);
		contentPane.add(lblThuBnga);
		
		btnFirstPage = new JButton("");
		btnFirstPage.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\rewind_16px.png"));
		btnFirstPage.setBackground(Color.LIGHT_GRAY);
		btnFirstPage.setBounds(60, 582, 60, 18);
		contentPane.add(btnFirstPage);
		
		btnPrePage = new JButton("");
		btnPrePage.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\back_to_16px.png"));
		btnPrePage.setBackground(Color.LIGHT_GRAY);
		btnPrePage.setBounds(152, 582, 60, 18);
		contentPane.add(btnPrePage);
		
		btnNextPage = new JButton("");
		btnNextPage.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\next_page_16px.png"));
		btnNextPage.setBackground(Color.LIGHT_GRAY);
		btnNextPage.setBounds(336, 582, 60, 18);
		contentPane.add(btnNextPage);
		
		btnLastPage = new JButton("");
		btnLastPage.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\fast_forward_16px.png"));
		btnLastPage.setBackground(Color.LIGHT_GRAY);
		btnLastPage.setBounds(430, 582, 60, 18);
		contentPane.add(btnLastPage);
		
		txtSoTrang = new JTextField();
		txtSoTrang.setForeground(Color.RED);
		txtSoTrang.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoTrang.setText("1");
		txtSoTrang.setBounds(248, 581, 50, 18);
		contentPane.add(txtSoTrang);
		txtSoTrang.setColumns(10);
		if(BD.getTotalofTableBD() % 15 == 0)
			maxPage = BD.getTotalofTableBD() / 15;
		else maxPage = BD.getTotalofTableBD() /15 + 1;
		capNhatBangDuLieu();
		
		btnThemGioHang.addActionListener(this);
		btnXoaGioHang.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXemAll.addActionListener(this);
		btnFirstPage.addActionListener(this);
		btnLastPage.addActionListener(this);
		btnNextPage.addActionListener(this);
		btnPrePage.addActionListener(this);
		
		
		KH = new QuanLyKhachHang();
		List<KhachHang> list = KH.docTuBang();
		for (KhachHang khachHang : list) {
			if (maKH.trim().equals(khachHang.getMaKH().trim())) {
				txtKhachHang.setText(khachHang.getHoTen());
				txtSoDienThoai.setText(khachHang.getSdt());
			}
		}

		XuatTTCombobox();

	}

	private void capNhatBangDuLieu() {
		reMove();
		BD = new QuanLyBangDia();
		//list = BD.docTuBangBangDia();
		for (BangDia bangDia : list) {
			int soLuong = bangDia.getSoLuong();
			int i = ktraGioHang(bangDia.getMaBangDia());
			if (i != -1) {
				String stringSoLuong = (String) table1.getValueAt(i, 2);
				soLuong = soLuong - Integer.valueOf(stringSoLuong);
			}
			String[] rowData = { bangDia.getMaBangDia(), bangDia.getTenBangDia(), bangDia.getTheLoai().getTenTheLoai(), soLuong + "",
					String.valueOf(bangDia.getGiaThue()) };
			tablemodel.addRow(rowData);
		}
		table.setModel(tablemodel);
	}
	public void nextPage() {
		if(this.page < maxPage) {
			this.page++;
			txtSoTrang.setText(page+"");
			list = BD.phanTrang((page-1)*15+1, page*15);
			reMove();
			capNhatBangDuLieu();
		}
	}
	public void prePage() {
		if(this.page > 1) {
			this.page--;
			txtSoTrang.setText(page+"");
			list = BD.phanTrang((page-1)*15+1, (page)*15);
			reMove();
			capNhatBangDuLieu();
		}
	}
	
	public void backUpFirstPage() {
		this.page = 1;
		txtSoTrang.setText(page+"");
		list = BD.phanTrang(1,15);
		capNhatBangDuLieu();
	}
	public void comeToLastPage() {
		this.page = maxPage;
		txtSoTrang.setText(maxPage+"");
		list = BD.phanTrang((maxPage-1)*15+1,maxPage*15);
		capNhatBangDuLieu();
	}
	public void reMove() {
		
		tablemodel.getDataVector().removeAllElements();
		tablemodel.fireTableDataChanged();
	}	
	private boolean kTraSoLuong(int soThue, int soDia) {
		if (soThue <= 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng đĩa lớn hơn 0");
			return false;
		} else if (soThue > soDia) {
			JOptionPane.showMessageDialog(this, "Số lượng đĩa thuê vượt quá cho phép số lượng đĩa đang có trong kho");
			return false;
		}
		return true;
	}

	private void XuatTTCombobox() {
		cboTheLoai.addItem("---------------------------------------------");
		TL = new QuanLyTheLoai();
		List<TheLoai> listTL = TL.docTuBang();
		for (TheLoai theLoai : listTL) {
			cboTheLoai.addItem(theLoai.getTenTheLoai());
		}
	}

	private int ktraGioHang(String maBD) {
		int rowCount = table1.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			String maBangDia = (String) table1.getValueAt(i, 0);
			if (maBD.trim().equals(maBangDia.trim()))
				return i;
		}
		return -1;
	}

	private void timKiem(String tenBD, String nCC, String theLoai, double min, double max) {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tablemodel.removeRow(i - 1);
		}
		BD = new QuanLyBangDia();
		List<BangDia> list = BD.timKiem(tenBD, nCC, theLoai, min, max);
		for (BangDia bangDia : list) {
			int soLuong = bangDia.getSoLuong();
			int i = ktraGioHang(bangDia.getMaBangDia());
			if (i != -1) {
				String stringSoLuong = (String) table1.getValueAt(i, 1);
				soLuong = soLuong - Integer.valueOf(stringSoLuong);
			}
			String[] rowData = { bangDia.getMaBangDia(), bangDia.getTenBangDia(), bangDia.getTheLoai().getTenTheLoai(), soLuong + "",
					String.valueOf(bangDia.getGiaThue()) };
			tablemodel.addRow(rowData);
		}
		table.setModel(tablemodel);
	}

	private boolean kTraDinhDangTimKiem() {
		String strMin = txtMin.getText();
		String strMax = txtMax.getText();
		if (!strMin.matches("[0-9]+$")) {
			JOptionPane.showMessageDialog(this, "Giá tối thiểu phải là số!");
			txtMin.requestFocus();
			txtMin.selectAll();
			return false;
		}
		if (!strMax.matches("[0-9]+$")) {
			JOptionPane.showMessageDialog(this, "Giá tối đa phải là số!");
			txtMax.requestFocus();
			txtMax.selectAll();
			return false;
		}
		double min = Double.parseDouble(strMin);
		double max = Double.parseDouble(strMax);
		if (min < 0) {
			JOptionPane.showMessageDialog(this, "Giá trị tối thiểu phải lớn hơn hoặc bằng 0!");
			txtMin.requestFocus();
			txtMin.selectAll();
			return false;
		}
		if (max < min) {
			JOptionPane.showMessageDialog(this, "Giá trị tối đa phải lớn hơn hoặc bằng giá trị tối thiểu!");
			txtMax.requestFocus();
			txtMax.selectAll();
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThemGioHang)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				String maBD = (String) table.getValueAt(row, 0);
				String tenBD = (String) table.getValueAt(row, 1);
				int soLuong = Integer.valueOf(JOptionPane.showInputDialog(this, "Nhập Số Lượng Đĩa", "1"));
				String stringSo = (String) table.getValueAt(row, 3);
				int so = Integer.parseInt(stringSo);
				if (kTraSoLuong(soLuong, so)) {
					int i = ktraGioHang(maBD);
					if (i != -1) {
						String stringSoLuong = (String) table1.getValueAt(i, 2);
						int newSoLuong = soLuong + Integer.valueOf(stringSoLuong);
						stringSoLuong = newSoLuong + "";
						table1.setValueAt(stringSoLuong, i, 2);
						capNhatBangDuLieu();
					} else {
						String[] rowData = { maBD, tenBD, soLuong + "" };
						tablemodel1.addRow(rowData);
						table1.setModel(tablemodel1);
						capNhatBangDuLieu();
					}
				}

			}
		} else if (o.equals(btnLapPhieu)) {
			HD = new QuanLyHoaDon();
			CT = new QuanLyChiTietHoaDon();
			BD = new QuanLyBangDia();
			String maHD = HD.tuDongLayMa();
			int k = table1.getRowCount();
			if (k != 0) {
				if (HD.themHoaDon(maHD, maNV, maKH)) {
					int rowCount = table1.getRowCount();
					for (int i = 0; i < rowCount; i++) {
						String maBD = (String) table1.getValueAt(i, 0);
						String stringSoLuong = (String) table1.getValueAt(i, 2);
						int soLuong = Integer.parseInt(stringSoLuong);
						CT.themCTHoaDon(maBD, maHD.trim(), soLuong);
						BD.thueBangDia(maBD, soLuong);
					}
					GiaoDienChiTietPhieuThue pt = new GiaoDienChiTietPhieuThue(maHD, 0,maTK);
					pt.setVisible(true);
					setVisible(false);
				}
			}
			else 
				JOptionPane.showMessageDialog(this, "Giỏ hàng không được để trống !");
		} else if (o.equals(btnXoaGioHang)) {
			int row = table1.getSelectedRow();
			if (row != -1) {
				tablemodel1.removeRow(row);
				capNhatBangDuLieu();
			}
			// tinhTongTien();
		} else if (o.equals(btnXemAll)) {
			capNhatBangDuLieu();
		} else if (o.equals(btnTimKiem)) {
			String tenBd = txtTenBangDia.getText();
			String theLoai = (String) cboTheLoai.getSelectedItem();
			if (theLoai.equals("---------------------------------------------"))
				theLoai = " ";
			String strMin = txtMin.getText();
			String strMax = txtMax.getText();
			double min = Double.parseDouble(strMin);
			double max = Double.parseDouble(strMax);
			timKiem(tenBd, " ", theLoai, min, max);
		} else if (o.equals(btnHuy)) {
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienDanhSachKhachHang kh = new GiaoDienDanhSachKhachHang(maNV, maTK);
			kh.setVisible(true);
			setVisible(false);
		}else if (o.equals(btnNextPage)) {
			nextPage();
		}
		else if (o.equals(btnPrePage)) {
			prePage();
		}else if(o.equals(btnFirstPage)) {
			backUpFirstPage();
		}else if(o.equals(btnLastPage)) {
			comeToLastPage();
		}

	}
}
