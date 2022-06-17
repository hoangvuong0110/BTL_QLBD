package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DieuKhien.QuanLyNhanVien;
import DieuKhien.QuanLyTaiKhoan;
import DoiTuong.TaiKhoan;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class GiaoDienDangNhap extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtTenTK;
	private JPasswordField pwfMatKhau;
	private JLabel lblTK;
	private JLabel lblMK;
	private JButton btnDangNhap, btnThoat;
	private QuanLyTaiKhoan TK;
	private QuanLyNhanVien NV;
	
	public GiaoDienDangNhap() {
		setTitle("Đăng Nhập");
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon/login.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 180);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtTenTK = new JTextField();
		txtTenTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenTK.setBounds(106, 16, 160, 25);
		txtTenTK.setText("lamvinh");
		contentPane.add(txtTenTK);
		txtTenTK.setColumns(10);

		pwfMatKhau = new JPasswordField();
		pwfMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pwfMatKhau.setBounds(106, 61, 160, 25);
		pwfMatKhau.setText("admin");
		contentPane.add(pwfMatKhau);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDangNhap.setBounds(30, 101, 112, 25);
		contentPane.add(btnDangNhap);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnThoat.setBounds(171, 101, 95, 25);
		contentPane.add(btnThoat);

		lblTK = new JLabel("Tài Khoản:");
		lblTK.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTK.setIcon(new ImageIcon("icon\\user_group.png"));
		lblTK.setBounds(5, 11, 150, 34);
		contentPane.add(lblTK);

		lblMK = new JLabel("Mật Khẩu:");
		lblMK.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMK.setIcon(new ImageIcon("icon\\key.png"));
		lblMK.setBounds(5, 56, 150, 34);
		contentPane.add(lblMK);
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		pwfMatKhau.addActionListener(this);
	}

	private String kiemTraTK(String tk, String mk) {
		TK = new QuanLyTaiKhoan();
		List<TaiKhoan> list = TK.docTuBang();
		for (TaiKhoan taiKhoan : list) {
			if (tk.equals(taiKhoan.getTenTK()))
				if (mk.equals(taiKhoan.getMatKhau()))
					return taiKhoan.getMaTK().trim();
		}
		return null;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDangNhap) || o.equals(pwfMatKhau)) {
			String tk = txtTenTK.getText();
			char[] m = pwfMatKhau.getPassword();
			String mk = "";
			for (int i = 0; i < m.length; i++) {
				mk += m[i];
			}
			String ma = kiemTraTK(tk, mk);
			if (ma != null) {
				NV = new QuanLyNhanVien();
				String maNV = NV.layMaNV(ma);
				GiaoDienTong t = new GiaoDienTong(maNV);
				t.setVisible(true);
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Sai tên tài khoản hoặc mật khẩu ");
				pwfMatKhau.setText("");
			}
		} else if (o.equals(btnThoat)) {
			setVisible(false);
		}
	}
}
