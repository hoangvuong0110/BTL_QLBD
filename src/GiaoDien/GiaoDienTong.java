package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DieuKhien.QuanLyNhanVien;
import DoiTuong.NhanVien;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import java.awt.Color;

public class GiaoDienTong extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem mntmCNBangDia, mntmTaiKhoan, mntmDangXuat, mntmThoat, mntmCNNCC, mntmCNKhachHang, mntmCNNhanVien,
			mntmTraBangDia, mntmThueBangDia, mntmTKBangDia, mntmTKHoaDon, mntmTKKhachHang, mntmCNTheLoai, mntmCNQuocGia,
			mntmYeuThich, mntmBDQuaHan, mntmDoanhThu, mntmThueNhieu, mntmKHQuaHan;
	private String maNV;
	private JLabel lblNhanVien, lblChucVu;
	private QuanLyNhanVien NV;
	private JMenu mnNewMenu;
	private JMenuItem mntmToBoCo;
	private JMenuItem mntmInBoCo;
	private JMenuItem mntmHuongDan;

	public GiaoDienTong(String maNV) {
		setTitle("Quản Lý Cửa Hàng Thuê Băng Đĩa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1014, 572);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.maNV = maNV;

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(0, 0, 1360, 32);
		contentPane.add(menuBar);

		JMenu mnHeThong = new JMenu("Hệ Thống  ");
		mnHeThong.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mnHeThong.setIcon(new ImageIcon("icon/system_16px.png"));
		menuBar.add(mnHeThong);
		mnHeThong.setForeground(Color.BLACK);
		
		
		mntmHuongDan = new JMenuItem("Hướng Dẫn");
		mntmHuongDan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnHeThong.add(mntmHuongDan);
		
		mntmHuongDan.addActionListener(e -> createAccount());
		
		mntmTaiKhoan = new JMenuItem("Đổi Mật Khẩu");
		mntmTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnHeThong.add(mntmTaiKhoan);
		

		mntmDangXuat = new JMenuItem("Đăng Xuất");
		mntmDangXuat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnHeThong.add(mntmDangXuat);
		mntmDangXuat.addActionListener(this);

		mntmThoat = new JMenuItem("Thoát");
		mntmThoat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnHeThong.add(mntmThoat);
		mntmThoat.addActionListener(this);

		JMenu mnQuanLy = new JMenu("Danh Mục ");
		mnQuanLy.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mnQuanLy.setIcon(new ImageIcon("icon/list.png"));
		menuBar.add(mnQuanLy);
		mnQuanLy.setForeground(Color.BLACK);
		mntmCNBangDia = new JMenuItem("Băng Đĩa");
		mntmCNBangDia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnQuanLy.add(mntmCNBangDia);
		mntmCNBangDia.addActionListener(this);
		
		mntmCNKhachHang = new JMenuItem("Khách Hàng");
		mntmCNKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnQuanLy.add(mntmCNKhachHang);
		mntmCNKhachHang.addActionListener(this);


		mntmCNNhanVien = new JMenuItem("Nhân Viên");
		mntmCNNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnQuanLy.add(mntmCNNhanVien);mntmCNNCC = new JMenuItem("Nhà Cung Cấp");
		mntmCNNCC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnQuanLy.add(mntmCNNCC);
		mntmCNNCC.addActionListener(this);
		
		

		mntmCNTheLoai = new JMenuItem("Thể Loại");
		mntmCNTheLoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnQuanLy.add(mntmCNTheLoai);
		mntmCNTheLoai.addActionListener(this);
		mntmCNQuocGia = new JMenuItem("Quốc Gia");
		mntmCNQuocGia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnQuanLy.add(mntmCNQuocGia);
		mntmCNQuocGia.addActionListener(this);
		mntmCNNhanVien.addActionListener(this);

		JMenu mnXuLy = new JMenu("Xử Lý ");
		mnXuLy.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mnXuLy.setIcon(new ImageIcon("icon/process_16px.png"));
		menuBar.add(mnXuLy);
		mnXuLy.setForeground(Color.BLACK);
		mntmThueBangDia = new JMenuItem("Thuê Băng Đĩa");
		mntmThueBangDia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnXuLy.add(mntmThueBangDia);
		mntmThueBangDia.addActionListener(this);

		mntmTraBangDia = new JMenuItem("Trả Băng Đĩa");
		mntmTraBangDia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnXuLy.add(mntmTraBangDia);
		mntmTraBangDia.addActionListener(this);

		JMenu mnTimKiem = new JMenu("Tìm Kiếm  ");
		mnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mnTimKiem.setIcon(new ImageIcon("icon/search_16px.png"));
		menuBar.add(mnTimKiem);
		mnTimKiem.setForeground(Color.BLACK);
		mntmTKBangDia = new JMenuItem("Băng Đĩa");
		mntmTKBangDia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnTimKiem.add(mntmTKBangDia);

		mntmTKHoaDon = new JMenuItem("Hóa Đơn");
		mntmTKHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnTimKiem.add(mntmTKHoaDon);

		mntmTKKhachHang = new JMenuItem("Khách Hàng");
		mntmTKKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnTimKiem.add(mntmTKKhachHang);

		JMenu mnThongKe = new JMenu("Thống Kê  ");
		mnThongKe.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
		mnThongKe.setIcon(new ImageIcon("icon/statistics_16px.png"));
		menuBar.add(mnThongKe);
		mnThongKe.setForeground(Color.BLACK);
		JMenu mnBnga = new JMenu("Băng Đĩa");
		mnBnga.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnThongKe.add(mnBnga);

		mntmYeuThich = new JMenuItem("Yêu Thích");
		mntmYeuThich.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnBnga.add(mntmYeuThich);
		mntmYeuThich.addActionListener(this);

		mntmBDQuaHan = new JMenuItem("Quá Hạn Trả");
		mntmBDQuaHan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnBnga.add(mntmBDQuaHan);
		mntmBDQuaHan.addActionListener(this);

		mntmDoanhThu = new JMenuItem("Doanh Thu");
		mntmDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnThongKe.add(mntmDoanhThu);
		mntmDoanhThu.addActionListener(this);

		JMenu mnKhchHng = new JMenu("Khách Hàng");
		mnKhchHng.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnThongKe.add(mnKhchHng);

		mntmThueNhieu = new JMenuItem("Thuê Nhiều Nhất");
		mntmThueNhieu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnKhchHng.add(mntmThueNhieu);
		mntmThueNhieu.addActionListener(this);

		mntmKHQuaHan = new JMenuItem("Quá Hạn Trả");
		mntmKHQuaHan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnKhchHng.add(mntmKHQuaHan);
		mntmKHQuaHan.addActionListener(this);


		JLabel lblNewLabel_1 = new JLabel("Nhân Viên : ");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\staff_16px.png"));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 475, 103, 23);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNhanVien = new JLabel(" ");
		lblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhanVien.setBounds(114, 474, 203, 23);
		lblNhanVien.setForeground(Color.RED);
		contentPane.add(lblNhanVien);

		JLabel lblChcV = new JLabel("Chức Vụ  : ");
		lblChcV.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\intel_isef_16px.png"));
		lblChcV.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblChcV.setBounds(10, 509, 94, 23);
		lblChcV.setForeground(Color.BLACK);
		contentPane.add(lblChcV);
		
		lblChucVu = new JLabel("");
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChucVu.setBounds(114, 509, 203, 23);
		lblChucVu.setForeground(Color.RED);
		contentPane.add(lblChucVu);
		

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"icon/anhnen1.jpg"));
		lblNewLabel.setBounds(0, 31, 1008, 435);
		contentPane.add(lblNewLabel);

		mntmTKHoaDon.addActionListener(this);
		mntmTKBangDia.addActionListener(this);
		mntmTKKhachHang.addActionListener(this);
		mntmTaiKhoan.addActionListener(this);

		int chucVu = 0;
		NV = new QuanLyNhanVien();
		List<NhanVien> nv = NV.docTuBang();
		for (NhanVien nhanVien : nv) {
			if (nhanVien.getMaNV().trim().equals(maNV.trim())) {
				lblNhanVien.setText(nhanVien.getTenNV());
				lblChucVu.setText(nhanVien.getChuVu());
				if (nhanVien.getChuVu().equals("Quản Lý"))
					chucVu = 1;
			}
		}

		if (chucVu == 0) {
			mntmCNNCC.setVisible(false);
			mntmCNNhanVien.setVisible(false);
		}

	}

	 private static void createAccount() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(mntmCNBangDia)) {
		
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienQuanLyBangDia bd = new GiaoDienQuanLyBangDia(maTK);
			bd.setVisible(true);
			setVisible(false);

		} else if (o.equals(mntmHuongDan)) {
			
//			NV = new QuanLyNhanVien();
//			String maTK = NV.layMaTK(maNV);
			GD_HuongDanSuDung hd = new GD_HuongDanSuDung();
			hd.setVisible(true);
//			setVisible(false);

		
		} 
		else if (o.equals(mntmCNKhachHang)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienQuanLyKhachHang kh = new GiaoDienQuanLyKhachHang(maTK);
			kh.setVisible(true);
			setVisible(false);

		
		} else if (o.equals(mntmCNNhanVien)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienQuanLyNhanVien nv = new GiaoDienQuanLyNhanVien(maTK);
			nv.setVisible(true);
			setVisible(false);
		
		} else if (o.equals(mntmCNNCC)) {
	
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienQuanLyNhaCungCap ncc = new GiaoDienQuanLyNhaCungCap(maTK);
			ncc.setVisible(true);
			setVisible(false);

			
		} else if (o.equals(mntmThueBangDia)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienDanhSachKhachHang kh = new GiaoDienDanhSachKhachHang(maNV,maTK);
			kh.setVisible(true);
			setVisible(false);
	
		} else if (o.equals(mntmTraBangDia)) {
			
			String maTK = NV.layMaTK(maNV);
			GiaoDienTraBangDia t = new GiaoDienTraBangDia(maTK);
			t.setVisible(true);
			setVisible(false);
			
		} else if (o.equals(mntmTKBangDia)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienTimKiemBangDia bd = new GiaoDienTimKiemBangDia(maTK);
			bd.setVisible(true);
			setVisible(false);
			
		} else if (o.equals(mntmTKHoaDon)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienTimKiemHoaDon hd = new GiaoDienTimKiemHoaDon(maTK);
			hd.setVisible(true);
			setVisible(false);
		
		} else if (o.equals(mntmTKKhachHang)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienTimKiemKhachHang kh = new GiaoDienTimKiemKhachHang(maTK);
			kh.setVisible(true);
			setVisible(false);
			
		} else if (o.equals(mntmCNTheLoai)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienQuanLyTheLoai tl = new GiaoDienQuanLyTheLoai(maTK);
			tl.setVisible(true);
			setVisible(false);
	
		} else if (o.equals(mntmCNQuocGia)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienQuanLyQuocGia qg = new GiaoDienQuanLyQuocGia(maTK);
			qg.setVisible(true);
			setVisible(false);

		} else if (o.equals(mntmThoat)) {
			setVisible(false);
		} else if (o.equals(mntmDangXuat)) {
			GiaoDienDangNhap dn = new GiaoDienDangNhap();
			dn.setVisible(true);
		
		} else if (o.equals(mntmYeuThich)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienThongKeSoBangDiaDuocYeuThich t = new GiaoDienThongKeSoBangDiaDuocYeuThich(maTK);;
			t.setVisible(true);
			setVisible(false);
		
		} else if (o.equals(mntmBDQuaHan)) {
			GiaoDienThongKeSoBangDiaQuaHan t = new GiaoDienThongKeSoBangDiaQuaHan();
			t.setVisible(true);
		
		} else if (o.equals(mntmDoanhThu)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienThongKeDoanhThu t = new GiaoDienThongKeDoanhThu(maTK);
			t.setVisible(true);
			setVisible(false);

		
		} else if (o.equals(mntmKHQuaHan)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienThongKeDanhSachKhachHangQuaHanTra t = new GiaoDienThongKeDanhSachKhachHangQuaHanTra(maTK);
			t.setVisible(true);
			setVisible(false);

	
		} else if (o.equals(mntmThueNhieu)) {
			
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienThongKeKhachHangThueBangDiaNhieuNhat t = new GiaoDienThongKeKhachHangThueBangDiaNhieuNhat(maTK);
			t.setVisible(true);
			setVisible(false);

			
		} else if (o.equals(mntmTaiKhoan)) {
			NV = new QuanLyNhanVien();
			String maTK = NV.layMaTK(maNV);
			GiaoDienDoiMatKhau t = new GiaoDienDoiMatKhau(maTK);
			t.setVisible(true);
			setVisible(false);

		}
		
	}
}
