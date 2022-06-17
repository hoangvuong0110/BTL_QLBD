package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.Database;
import DieuKhien.QuanLyNhanVien;
import DieuKhien.QuanLyTaiKhoan;
import DoiTuong.TaiKhoan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;

public class GiaoDienThemTaiKhoan extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private QuanLyNhanVien qlNV;
	private JTextField txttenTK;
	private JButton btnthem;
	private JButton btnthoat;
	private QuanLyTaiKhoan qltk;
	private List<TaiKhoan> tk;
	private String maNV, ten, chucVu, phai, cmND, sdt, diachi, emaiL;
	private Date ngaySinh, ngayLam;
	private int tinhTrang;
	private JPasswordField pwfMatKhau;
	private JPasswordField pwfNhapLai;

	public GiaoDienThemTaiKhoan(String maNV, String ten, String chucVu, String phai, Date ngaySinh, Date ngayLam,
			String cmND, String sdt, String diachi, String emaiL, int tinhTrang) {
		this.maNV = maNV;
		this.ten = ten;
		this.chucVu = chucVu;
		this.phai = phai;
		this.cmND = cmND;
		this.sdt = sdt;
		this.diachi = diachi;
		this.emaiL = emaiL;
		this.tinhTrang = tinhTrang;
		this.ngaySinh = ngaySinh;
		this.ngayLam = ngayLam;
		setTitle("Giao Diện Thêm Tài Khoản");
		Database.getInstance().getConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 272);
		setLocationRelativeTo(null);
		taoGiaoDien();
	}

	public void taoGiaoDien() {

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbltenTK = new JLabel("Tên Tài Khoản: ");
		lbltenTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltenTK.setBounds(12, 22, 110, 26);
		contentPane.add(lbltenTK);

		JLabel lblmatKhau = new JLabel("Mật Khẩu: ");
		lblmatKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblmatKhau.setBounds(12, 76, 88, 26);
		contentPane.add(lblmatKhau);

		txttenTK = new JTextField();
		txttenTK.setColumns(10);
		txttenTK.setBounds(112, 24, 200, 26);
		contentPane.add(txttenTK);

		btnthem = new JButton("Thêm");
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setBackground(Color.WHITE);
		btnthem.setBounds(42, 186, 103, 26);
		contentPane.add(btnthem);
		btnthem.addActionListener(this);

		btnthoat = new JButton("Thoát");
		btnthoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthoat.setBackground(Color.WHITE);
		btnthoat.setBounds(185, 186, 110, 26);
		contentPane.add(btnthoat);

		JLabel lblnhapLaiMK = new JLabel("Nhập Lại MK:");
		lblnhapLaiMK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnhapLaiMK.setBounds(12, 134, 110, 26);
		contentPane.add(lblnhapLaiMK);

		pwfMatKhau = new JPasswordField();
		pwfMatKhau.setBounds(112, 78, 200, 26);
		contentPane.add(pwfMatKhau);

		pwfNhapLai = new JPasswordField();
		pwfNhapLai.setBounds(112, 136, 200, 26);
		contentPane.add(pwfNhapLai);
		btnthoat.addActionListener(this);
	}

	private String kiemTraMK(String tenTK, String matKhau) {
		Database.getInstance().getConnection();
		qltk = new QuanLyTaiKhoan();
		List<TaiKhoan> tk = qltk.docTuBang();
		for (TaiKhoan taiKhoan : tk) {
			if (tenTK.equals(taiKhoan.getTenTK()))
				if (matKhau.equals(taiKhoan.getMatKhau()))
					return taiKhoan.getMaTK().trim();
		}
		return null;
	}

	private boolean kiemTraTaiKhoan() {
		String tentk = txttenTK.getText();
		char[] mKhau = pwfMatKhau.getPassword();
		char[] nhapLaiMK = pwfNhapLai.getPassword();
		String MK = "";
		String NLMK = "";
		for (int i = 0; i < mKhau.length; i++) {
			MK += mKhau[i];
		}
		for (int i = 0; i < nhapLaiMK.length; i++) {
			NLMK += nhapLaiMK[i];
		}
		qltk = new QuanLyTaiKhoan();
		List<TaiKhoan> dstk = qltk.docTuBang();
		if (tentk.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Tên tài khoản không được bỏ rỗng!");
			txttenTK.requestFocus();
			txttenTK.selectAll();
			return false;
		} else if (!tentk.matches("[a-zA-Z]+[a-zA-Z0-9]+") || tentk.length() < 5) {
			JOptionPane.showMessageDialog(this,
					"Tên tài khoản phải có kí tự đầu là chữ và có ít nhất là 5 kí tự.\n Tên tài khoản không có khoảng trắng và các kí tự đặc biệt khác!");
			txttenTK.requestFocus();
			txttenTK.selectAll();
			return false;
		}
		for (TaiKhoan taiKhoan : dstk) {
			if (tentk.trim().equals(taiKhoan.getTenTK().trim())) {
				JOptionPane.showMessageDialog(this, "Tên tài khoản đã có người sử dụng!");
				return false;
			}
		}
		if (mKhau.length < 5) {
			JOptionPane.showMessageDialog(this, " Mật khẩu phải từ 5 kí tự trở lên!");
			pwfMatKhau.requestFocus();
			pwfMatKhau.setText("");
			return false;
		} else if (!MK.equals(NLMK)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không khớp!");
			pwfMatKhau.requestFocus();
			pwfMatKhau.setText("");
			pwfNhapLai.setText("");
			return false;
		}

		return true;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob.equals(btnthoat)) {
			setVisible(false);
		} else if (ob.equals(btnthem)) {
			if (kiemTraTaiKhoan()) {
				qlNV = new QuanLyNhanVien();
				qltk = new QuanLyTaiKhoan();
				String maTK = qltk.tuDongLayMa();
				String tenTK = txttenTK.getText();
				char[] m = pwfMatKhau.getPassword();
				String mk = "";
				for (int i = 0; i < m.length; i++) {
					mk += m[i];
				}
				if (qltk.themTaiKhoan(maTK, tenTK, mk)) {
					if (qlNV.themNhanVien(maNV, ten, chucVu, phai, ngaySinh, ngayLam, cmND, sdt, diachi, emaiL,
							tinhTrang, maTK)) {
						JOptionPane.showMessageDialog(this, "Đã thêm nhân viên!");
						setVisible(false);
						GiaoDienQuanLyNhanVien nv = new GiaoDienQuanLyNhanVien(maTK);
						nv.setVisible(true);
					}
				}
			}
		}
	}
}
