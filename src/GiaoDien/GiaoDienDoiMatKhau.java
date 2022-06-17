package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DieuKhien.QuanLyNhanVien;
import DieuKhien.QuanLyTaiKhoan;
import DoiTuong.TaiKhoan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;

public class GiaoDienDoiMatKhau extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPasswordField pwfMKCu;
	private JPasswordField pwfMKMoi;
	private JPasswordField pwfMKNhapLai;
	private QuanLyTaiKhoan TK;
	private String maTK;
	private JButton btnXacNhan, btnTroVe;
	private QuanLyNhanVien NV;

	public GiaoDienDoiMatKhau(String maTK) {
		this.maTK = maTK;
		setTitle("Thay đổi mật khẩu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 220);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Mật khẩu cũ:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(73, 11, 90, 20);
		contentPane.add(label);

		JLabel label_1 = new JLabel("Mật khẩu mới:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(63, 50, 100, 20);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Nhập lại mật khẩu mới:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(10, 90, 170, 20);
		contentPane.add(label_2);

		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXacNhan.setBounds(73, 141, 100, 25);
		contentPane.add(btnXacNhan);
		btnXacNhan.addActionListener(this);

		btnTroVe = new JButton("Trở Về");
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setBounds(234, 141, 100, 25);
		contentPane.add(btnTroVe);
		btnTroVe.addActionListener(this);

		pwfMKCu = new JPasswordField();
		pwfMKCu.setBounds(173, 13, 182, 20);
		contentPane.add(pwfMKCu);

		pwfMKMoi = new JPasswordField();
		pwfMKMoi.setBounds(173, 52, 182, 20);
		contentPane.add(pwfMKMoi);

		pwfMKNhapLai = new JPasswordField();
		pwfMKNhapLai.setBounds(173, 92, 182, 20);
		contentPane.add(pwfMKNhapLai);
	}

	private boolean kiemTraMK() {
		char[] c = pwfMKCu.getPassword();
		char[] m = pwfMKMoi.getPassword();
		char[] nlmk = pwfMKNhapLai.getPassword();
		String mkcu = "";
		String mkmoi = "";
		String nlmkmoi = "";
		// String tenTK;
		for (int i = 0; i < c.length; i++) {
			mkcu += c[i];
		}
		for (int i = 0; i < m.length; i++) {
			mkmoi += m[i];
		}
		for (int i = 0; i < nlmk.length; i++) {
			nlmkmoi += nlmk[i];
		}
		if (mkcu.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu cũ không được để trống");
			pwfMKCu.requestFocus();
			return false;
		}
		TK = new QuanLyTaiKhoan();
		int k = 0;
		List<TaiKhoan> list = TK.docTuBang();
		for (TaiKhoan taiKhoan : list) {
			if (mkcu.equals(taiKhoan.getMatKhau()))
				k++;
		}
		if (k == 0) {
			JOptionPane.showMessageDialog(this, "Sai mật khẩu");
			pwfMKCu.setText("");
			pwfMKCu.requestFocus();
			pwfMKMoi.setText("");
			pwfMKNhapLai.setText("");
			return false;
		}
		if (!mkmoi.matches("[a-zA-Z0-9]+")) {
			JOptionPane.showMessageDialog(this, "Nhập Mật khẩu mới. Mật khẩu không được có các kí tự đặc biệt!");
			pwfMKCu.setText("");
			pwfMKCu.requestFocus();
			pwfMKMoi.setText("");
			pwfMKNhapLai.setText("");
			return false;
		}
		if (mkmoi.length() < 5) {
			JOptionPane.showMessageDialog(this, "Nhập mật khẩu mới. Mật khẩu mới phải từ 5 kí tự trở lên!");
			pwfMKCu.setText("");
			pwfMKCu.requestFocus();
			pwfMKMoi.setText("");
			pwfMKNhapLai.setText("");
			return false;
		}
		if (mkcu.equals(mkmoi)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu mới không được giống mật khẩu cũ!");
			pwfMKCu.setText("");
			pwfMKCu.requestFocus();
			pwfMKMoi.setText("");
			pwfMKNhapLai.setText("");
			return false;
		}
		if (!mkmoi.equals(nlmkmoi)) {
			JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu mới không khớp!");
			pwfMKCu.setText("");
			pwfMKCu.requestFocus();
			pwfMKMoi.setText("");
			pwfMKNhapLai.setText("");
			return false;
		}
		return true;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXacNhan)) {
			char[] m2 = pwfMKMoi.getPassword();
			String mk2 = "";
			for (int i = 0; i < m2.length; i++) {
				mk2 += m2[i];
			}
			if (kiemTraMK()) {
				TK.doiMatKhau(maTK, mk2);
				JOptionPane.showMessageDialog(this, "Đã thay đổi mật khẩu , mời bại đăng nhập lại");
				setVisible(false);
				GiaoDienDangNhap dn = new GiaoDienDangNhap();
				dn.setVisible(true);
			}
		} else if (o.equals(btnTroVe)) {
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		}
	}
}
