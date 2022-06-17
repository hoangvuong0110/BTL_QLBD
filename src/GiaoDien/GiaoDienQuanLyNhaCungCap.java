package GiaoDien;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import DieuKhien.QuanLyNhaCungCap;
import DieuKhien.QuanLyNhanVien;
import DieuKhien.QuanLyQuocGia;
import DieuKhien.QuanLyTheLoai;
import DoiTuong.NhaCungCap;
import DoiTuong.QuocGia;
import DoiTuong.TheLoai;

import java.awt.Font;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class GiaoDienQuanLyNhaCungCap extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtSoDienThoai;
	private JTextField txtEmail;
	private JButton btnThem, btnCapNhat, btnTroVe, btnTimKiem;
	private QuanLyNhaCungCap NCC;
	private QuanLyQuocGia QG;
	private List<NhaCungCap> dsNCC;
	private JComboBox cboNCC, cboQG;
	private JButton btnXoaRong;
	private QuanLyNhanVien NV;
	private String maTK;

	public GiaoDienQuanLyNhaCungCap(String maTK) {
		this.maTK = maTK;
		setTitle("Quản lý nhà cung cấp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1278, 643);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(contentPane);
		contentPane.setLayout(null);

		JScrollPane scroll;
		String[] headers = "Mã Nhà Cung Cấp;Tên Nhà Cung Cấp;Quốc Gia;Số Điện Thoại;Email".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		contentPane.add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(28);
		table.addMouseListener(this);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(721, 15, 530, 586);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblThngTinChi = new JLabel("THÔNG TIN NHÀ CUNG CẤP");
		lblThngTinChi.setForeground(Color.BLUE);
		lblThngTinChi.setBounds(120, 22, 330, 31);
		lblThngTinChi.setFont(new Font("Times New Roman", Font.BOLD, 24));
		panel.add(lblThngTinChi);

		JLabel label_1 = new JLabel("Mã Nhà Cung Cấp:");
		label_1.setBounds(18, 71, 121, 31);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(label_1);

		JLabel lblTnNhCung_1 = new JLabel("Tên Nhà Cung Cấp:");
		lblTnNhCung_1.setBounds(12, 129, 127, 31);
		lblTnNhCung_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblTnNhCung_1);

		JLabel lblQucGia = new JLabel("Quốc Gia:");
		lblQucGia.setBounds(73, 187, 66, 31);
		lblQucGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblQucGia);

		txtMa = new JTextField();
		txtMa.setEditable(false);
		txtMa.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMa.setBounds(149, 75, 181, 27);
		panel.add(txtMa);
		txtMa.setColumns(10);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTen.setBounds(149, 133, 362, 27);
		txtTen.setColumns(10);
		panel.add(txtTen);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại:");
		lblSinThoi.setBounds(41, 245, 98, 31);
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSinThoi);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSoDienThoai.setBounds(149, 249, 362, 27);
		txtSoDienThoai.setColumns(10);
		panel.add(txtSoDienThoai);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(95, 303, 44, 31);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setBounds(149, 307, 362, 27);
		txtEmail.setColumns(10);
		panel.add(txtEmail);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(18, 385, 493, 95);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Thanh C\u00F4ng C\u1EE5",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel_1.setBackground(UIManager.getColor("Button.background"));
		panel.add(panel_1);
		panel_1.setLayout(null);

		btnThem = new JButton("Thêm");
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBounds(20, 39, 100, 23);
		btnThem.setIcon(new ImageIcon(new ImageIcon("icon/add_16px.png").getImage()));
		panel_1.add(btnThem);

		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setBackground(Color.LIGHT_GRAY);
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCapNhat.setBounds(130, 39, 115, 23);
		btnCapNhat.setIcon(new ImageIcon("icon/update_16px.png"));
		panel_1.add(btnCapNhat);
		

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setBackground(Color.LIGHT_GRAY);
		btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaRong.setBounds(255, 39, 115, 23);
		btnXoaRong.setIcon(new ImageIcon("icon/emptydelete_16px.png"));
		panel_1.add(btnXoaRong);
		
		btnTroVe = new JButton("Trở Về");
		btnTroVe.setBackground(Color.LIGHT_GRAY);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setBounds(375, 39, 108, 23);
		
		btnTroVe.setIcon(new ImageIcon("icon/back_to_16px.png"));
		panel_1.add(btnTroVe);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Thanh T\u00ECm Ki\u1EBFm",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel_2.setBackground(UIManager.getColor("Button.background"));
		panel_2.setBounds(18, 491, 493, 71);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblTnNhCung = new JLabel("Tên Nhà Cung Cấp:");
		lblTnNhCung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnNhCung.setBounds(10, 24, 124, 24);
		panel_2.add(lblTnNhCung);

		cboNCC = new JComboBox();
		cboNCC.setBounds(144, 26, 201, 24);
		panel_2.add(cboNCC);
		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBackground(Color.LIGHT_GRAY);
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(365, 26, 115, 24);
		btnTimKiem.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\search_16px.png"));
		panel_2.add(btnTimKiem);

		cboQG = new JComboBox();
		cboQG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboQG.setBounds(150, 189, 361, 31);
		panel.add(cboQG);
		scroll.setBounds(10, 15, 701, 586);
		capNhapBangDuLieu();
		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnTroVe.addActionListener(this);
		btnTimKiem.addActionListener(this);

		XuatTTCombobox();
	}

	private void XuatTTCombobox() {
		NCC = new QuanLyNhaCungCap();
		List<NhaCungCap> listNCC = NCC.docTuBang();
		for (NhaCungCap nhaCungCap : listNCC) {
			cboNCC.addItem(nhaCungCap.getTenNCC());
		}
		QG = new QuanLyQuocGia();
		List<QuocGia> listQG = QG.docTuBang();
		for (QuocGia quocGia : listQG) {
			cboQG.addItem(quocGia.getTenQuocGia());
		}
	}

	private void capNhapBangDuLieu() {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tableModel.removeRow(i - 1);
		}
		NCC = new QuanLyNhaCungCap();
		List<NhaCungCap> List = NCC.docTuBang();
		for (NhaCungCap nhaCungCap : List) {
			String[] strings = { nhaCungCap.getMaNCC(), nhaCungCap.getTenNCC(), nhaCungCap.getQuocGia().getTenQuocGia(),
					nhaCungCap.getSoDienThoai(), nhaCungCap.getEmail() };
			tableModel.addRow(strings);
		}
		table.setModel(tableModel);
	}

	private void dienThongTin(int row) {
		if (row != -1) {
			btnThem.setVisible(false);
			String ma = (String) table.getValueAt(row, 0);// lay du lieu dong thu nhat trong bang
			NCC = new QuanLyNhaCungCap();
			List<NhaCungCap> List = NCC.docTuBang();
			for (NhaCungCap nhaCungCap : List) {
				if (ma.trim().equals(nhaCungCap.getMaNCC().trim())) {// trim bo khoan trang giua 2 dong
					txtMa.setEditable(false);
					txtMa.setText(nhaCungCap.getMaNCC());// lay du lieu tu base len textfilde
					txtTen.setText(nhaCungCap.getTenNCC());// get: doc du lieu tu trong datab
					cboQG.setSelectedItem(nhaCungCap.getQuocGia());
					txtSoDienThoai.setText(nhaCungCap.getSoDienThoai());
					txtEmail.setText(nhaCungCap.getEmail());

				}
			}
		}

	}

	private boolean kiemTraDinhDang() {
		String ten = txtTen.getText();
		String sdt = txtSoDienThoai.getText();
		String email = txtEmail.getText();

		if (ten.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Tên không được để trống!");
			txtTen.requestFocus();
			txtTen.selectAll();
			return false;
		} else if (!ten.matches("^[\\p{L}0-9 ]+$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng điền tên chính xác!");
			txtTen.requestFocus();
			txtTen.selectAll();
			return false;
		} else if (sdt.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
			txtSoDienThoai.requestFocus();
			txtSoDienThoai.selectAll();
			return false;
		} else if (!sdt.matches("[0-9]{10}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải là số và phải đủ 10 số!");
			txtSoDienThoai.requestFocus();
			txtSoDienThoai.selectAll();
			return false;
		} else if (email.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Email không được để trống");
			txtEmail.requestFocus();
			txtEmail.selectAll();
			return false;
		} else if (!email.matches("^([a-z0-9_])+@([\\da-z])+\\.([a-z\\.]{2,6})$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng Email!");
			txtEmail.requestFocus();
			txtEmail.selectAll();
			return false;
		}

		return true;
	}

	private void xoaRong() {
		btnThem.setVisible(true);
		txtMa.setText("");
		txtTen.setText("");
		cboQG.setSelectedIndex(1);
		txtSoDienThoai.setText("");
		txtEmail.setText("");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object.equals(table)) {
			dienThongTin(table.getSelectedRow());
		}
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object.equals(btnThem)) {
			if (kiemTraDinhDang()) {
				NCC = new QuanLyNhaCungCap();
				String idNCC = NCC.tuDongLayMa();
				String tenNCC = txtTen.getText();
				String quocGia = (String) cboQG.getSelectedItem();
				String soDienThoai = txtSoDienThoai.getText();
				String eMail = txtEmail.getText();
				if (NCC.themNCC(idNCC, tenNCC, quocGia, soDienThoai, eMail)) {
					capNhapBangDuLieu();
					JOptionPane.showMessageDialog(this, "Thêm thành công!");
					xoaRong();
					cboNCC = new JComboBox();
					XuatTTCombobox();
				} else
					JOptionPane.showMessageDialog(this, "Không thành công!");
			}
		} else if (object.equals(btnCapNhat)) {
			if (kiemTraDinhDang()) {
				String idNCC = txtMa.getText();
				String tenNCC = txtTen.getText();
				String quocGia = (String) cboQG.getSelectedItem();
				String soDienThoai = txtSoDienThoai.getText();
				String eMail = txtEmail.getText();
				if (NCC.capNhapNCC(idNCC, tenNCC, quocGia, soDienThoai, eMail)) {
					capNhapBangDuLieu();
					JOptionPane.showMessageDialog(this, "Cập nhập thành công");
					xoaRong();
					cboNCC = new JComboBox();
					XuatTTCombobox();
				} else
					JOptionPane.showMessageDialog(this, "Không thành công!");
			}
		}  else if (object.equals(btnTroVe)) {
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		} else if (object.equals(btnTimKiem)) {
			dienThongTin(cboNCC.getSelectedIndex());
		}

	}
}
