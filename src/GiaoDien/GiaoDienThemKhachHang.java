package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.itextpdf.text.ImgCCITT;

import DatePicker.DateLabelFormatter;
import DieuKhien.QuanLyKhachHang;
import DieuKhien.QuanLyNhanVien;
import DoiTuong.KhachHang;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GiaoDienThemKhachHang extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txttenKhachHang;
	private JTextField txtcmnd;
	private JTextField txtdiaChi;
	private JTextField txtemail;
	private JTextField txtsdt;
	private Properties p;
	private UtilDateModel model1;
	private JDatePanelImpl datePanel1;
	private JDatePickerImpl datePicker1;
	private QuanLyKhachHang qlkh;
	private List<KhachHang> dskh;
	private JRadioButton radnam, radnu;
	private JButton btnhuy;
	private ButtonGroup group;
	private JButton btnthem;
	private String maNV;
	private QuanLyNhanVien NV;
	private String maTK;

	public GiaoDienThemKhachHang(String maNV,String maTK) {
		setResizable(false);
		this.maTK = maTK;
		this.maNV = maNV;
		setTitle("Thêm Khách Hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 448);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblThmKhchHng = new JLabel("Thêm Khách Hàng");
		lblThmKhchHng.setForeground(Color.BLUE);
		lblThmKhchHng.setFont(new Font("Arial", Font.BOLD, 30));
		lblThmKhchHng.setBounds(56, 11, 290, 52);
		contentPane.add(lblThmKhchHng);

		JLabel lbltenKhachHang = new JLabel("Tên Khách Hàng:");
		lbltenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltenKhachHang.setBounds(10, 74, 111, 25);
		
		contentPane.add(lbltenKhachHang);

		JLabel lblgioiTinh = new JLabel("Giới Tính:");
		lblgioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblgioiTinh.setBounds(56, 146, 65, 25);
		contentPane.add(lblgioiTinh);

		group = new ButtonGroup();
		radnam = new JRadioButton("Nam");
		radnam.setBackground(Color.WHITE);
		radnam.setBounds(165, 148, 65, 25);
		radnam.setIcon(new ImageIcon("icon/male_16px.png"));
		group.add(radnam);
		contentPane.add(radnam);

		radnu = new JRadioButton("Nữ");
		radnu.setBackground(Color.WHITE);
		radnu.setBounds(283, 148, 47, 25);
		radnu.setIcon(new ImageIcon("icon/female_16px.png"));
		group.add(radnu);
		contentPane.add(radnu);

		JLabel lblcmnd = new JLabel("CMND:");
		lblcmnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblcmnd.setBounds(69, 182, 52, 25);
		contentPane.add(lblcmnd);

		JLabel lblngaySinh = new JLabel("Ngày Sinh:");
		lblngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblngaySinh.setBounds(50, 110, 71, 25);
		contentPane.add(lblngaySinh);

		JLabel lbldiaChi = new JLabel("Địa Chỉ:");
		lbldiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbldiaChi.setBounds(69, 254, 52, 25);
		contentPane.add(lbldiaChi);

		JLabel lblemail = new JLabel("Email:");
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblemail.setBounds(74, 290, 47, 25);
		contentPane.add(lblemail);

		JLabel lblsdt = new JLabel("Số Điện Thoại :");
		lblsdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblsdt.setBounds(18, 218, 103, 25);
		contentPane.add(lblsdt);

		txttenKhachHang = new JTextField();
		txttenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttenKhachHang.setColumns(10);
		txttenKhachHang.setBackground(Color.WHITE);
		txttenKhachHang.setBounds(131, 75, 230, 24);
		contentPane.add(txttenKhachHang);

		txtcmnd = new JTextField();
		txtcmnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcmnd.setColumns(10);
		txtcmnd.setBackground(Color.WHITE);
		txtcmnd.setBounds(131, 180, 230, 24);
		contentPane.add(txtcmnd);

		txtdiaChi = new JTextField();
		txtdiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtdiaChi.setColumns(10);
		txtdiaChi.setBackground(Color.WHITE);
		txtdiaChi.setBounds(131, 255, 230, 24);
		contentPane.add(txtdiaChi);

		txtemail = new JTextField();
		txtemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtemail.setColumns(10);
		txtemail.setBackground(Color.WHITE);
		txtemail.setBounds(131, 291, 230, 24);
		contentPane.add(txtemail);

		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model1 = new UtilDateModel();
		datePanel1 = new JDatePanelImpl(model1, p);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		datePicker1.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 14));
		datePicker1.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker1.setBackground(Color.WHITE);
		contentPane.add(datePicker1);
		datePicker1.setBounds(131, 110, 230, 24);

		txtsdt = new JTextField();
		txtsdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtsdt.setColumns(10);
		txtsdt.setBackground(Color.WHITE);
		txtsdt.setBounds(131, 219, 230, 24);
		contentPane.add(txtsdt);

		btnhuy = new JButton("Hủy");
		btnhuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnhuy.setBackground(Color.WHITE);
		btnhuy.setBounds(213, 343, 146, 39);
		btnhuy.setIcon(new ImageIcon("icon/cancel_16px.png"));
		contentPane.add(btnhuy);

		btnthem = new JButton("Thêm");
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setBackground(Color.WHITE);
		btnthem.setBounds(35, 343, 146, 39);
		btnthem.setIcon(new ImageIcon("icon/add_16px.png"));
		contentPane.add(btnthem);
		btnhuy.addActionListener(this);
		btnthem.addActionListener(this);
		
		radnam.setSelected(true);

	}

	private boolean kiemTraDinhDang() {
		String ten = txttenKhachHang.getText();
		int namSinh = model1.getYear();
		String cCCD = txtcmnd.getText();
		String sdt = txtsdt.getText();
		String diachi = txtdiaChi.getText();
		String email = txtemail.getText();
		// String ghi = txtghiChu.getText();
		if (ten.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Tên không được để trống!");
			txttenKhachHang.requestFocus();
			txttenKhachHang.selectAll();
			return false;
		} else if (!ten.matches("^[\\p{L} ]+$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng điền tên chính xác!");
			txttenKhachHang.requestFocus();
			txttenKhachHang.selectAll();
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
		} else if (namSinh >= 2002) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tuổi trên 18!");
			return false;
		}
		qlkh = new QuanLyKhachHang();
		List<KhachHang> list = qlkh.docTuBang();
		for (KhachHang khachHang : list) {
			if (sdt.trim().equals(khachHang.getSdt().trim())) {
				JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại trong hệ thống !");
				txtsdt.requestFocus();
				txtsdt.selectAll();
				return false;
			}
			if (cCCD.trim().equals(khachHang.getcMND().trim())) {
				JOptionPane.showMessageDialog(this, "CMND/CCCD đã tồn tại trong hệ thống !");
				txtcmnd.requestFocus();
				txtcmnd.selectAll();
				return false;
			}
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
				String ten = txttenKhachHang.getText();
				String gioiTinh;
				if (radnam.isSelected())
					gioiTinh = "Nam";
				else
					gioiTinh = "Nữ";
				Date ngaySinh = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() + 1, model1.getDay()));
				Date ngayDK = Date.valueOf(LocalDate.now());
				String cmnd = txtcmnd.getText();
				String diaChi = txtdiaChi.getText();
				String email = txtemail.getText();
				String sdt = txtsdt.getText();

				if (qlkh.themKhachHang(ma, ten, gioiTinh, ngaySinh, ngayDK, cmnd, diaChi, email, sdt)) {
					NV = new QuanLyNhanVien();
					String maTK = NV.layMaTK(maNV);
					JOptionPane.showMessageDialog(this, "Thêm Thành Công!");
					GiaoDienThueBangDia t = new GiaoDienThueBangDia(ma, maNV,maTK);
					t.setVisible(true);
					setVisible(false);
				} else
					JOptionPane.showMessageDialog(this, "Thêm Không Thành Công!");
			}
		}
		 else if (ob.equals(btnhuy)) {
			 	
			 NV = new QuanLyNhanVien();
				String maNV = NV.layMaNV(maTK);
				GiaoDienDanhSachKhachHang kh = new GiaoDienDanhSachKhachHang(maNV, maTK);
				kh.setVisible(true);
				setVisible(false);
			}

	}
}
