package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import DatePicker.DateLabelFormatter;
import DieuKhien.QuanLyNhanVien;
import DoiTuong.NhanVien;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GiaoDienTimKiemHoaDon extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtKH;
	private JTextField textField;
	private JTextField textField_1;
	private Properties p;
	private UtilDateModel model1, model2;
	private JDatePanelImpl datePanel1, datePanel2;
	private JDatePickerImpl datePicker1, datePicker2;
	private JComboBox cboNV;
	private JButton btnTimKiem, btnTroVe;
	private QuanLyNhanVien qlNV;
	private JRadioButton rabDaTra, rabChuaTra;
	private String maTK;
	private QuanLyNhanVien NV;

	public GiaoDienTimKiemHoaDon(String maTK) {
		this.maTK = maTK;
		setTitle("Tìm Kiếm Hóa Đơn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 432);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTmKimHa = new JLabel("Tìm Kiếm Hóa Đơn");
		lblTmKimHa.setBounds(99, 11, 200, 39);
		lblTmKimHa.setFont(new Font("Tahoma", Font.PLAIN, 23));
		contentPane.add(lblTmKimHa);

		JLabel lblTnKhchHng = new JLabel("Tên Khách Hàng:");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnKhchHng.setBounds(10, 66, 124, 23);
		contentPane.add(lblTnKhchHng);

		JLabel lblTnNhnVin = new JLabel("Tên Nhân Viên:");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnNhnVin.setBounds(10, 111, 100, 23);
		contentPane.add(lblTnNhnVin);

		JLabel lblnNgy = new JLabel("Đến Ngày:");
		lblnNgy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnNgy.setBounds(65, 249, 69, 23);
		contentPane.add(lblnNgy);

		JLabel lblTNgy = new JLabel("Từ Ngày:");
		lblTNgy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTNgy.setBounds(75, 203, 69, 23);
		contentPane.add(lblTNgy);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBackground(Color.LIGHT_GRAY);
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(70, 358, 110, 23);
		btnTimKiem.setIcon(new ImageIcon("icon/search_16px.png"));
		contentPane.add(btnTimKiem);

		btnTroVe = new JButton("Trở Về");
		btnTroVe.setBackground(Color.LIGHT_GRAY);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setBounds(230, 358, 110, 23);
		btnTroVe.setIcon(new ImageIcon("icon/back_to_16px.png"));
		contentPane.add(btnTroVe);

		JLabel lblTrngThi = new JLabel("Trạng Thái:");
		lblTrngThi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrngThi.setBounds(10, 297, 90, 23);
		contentPane.add(lblTrngThi);

		JLabel lblNgyLpHa = new JLabel("Ngày Lập Hóa Đơn:");
		lblNgyLpHa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgyLpHa.setBounds(10, 161, 134, 23);
		contentPane.add(lblNgyLpHa);

		txtKH = new JTextField();
		txtKH.setBounds(135, 69, 240, 20);
		contentPane.add(txtKH);
		txtKH.setColumns(10);
		cboNV = new JComboBox();
		cboNV.setBounds(135, 113, 237, 22);
		contentPane.add(cboNV);

		ButtonGroup group = new ButtonGroup();

		rabDaTra = new JRadioButton("Đã Trả");
		rabDaTra.setBackground(Color.WHITE);
		rabDaTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rabDaTra.setBounds(135, 299, 78, 23);
		contentPane.add(rabDaTra);

		rabChuaTra = new JRadioButton("Chưa Trả");
		rabChuaTra.setBackground(Color.WHITE);
		rabChuaTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rabChuaTra.setBounds(256, 299, 116, 23);
		contentPane.add(rabChuaTra);

		group.add(rabDaTra);
		group.add(rabChuaTra);
		rabDaTra.setSelected(true);

		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model1 = new UtilDateModel();
		datePanel1 = new JDatePanelImpl(model1, p);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		datePicker1.setBounds(154, 206, 145, 20);
		contentPane.add(datePicker1);

		model2 = new UtilDateModel();
		datePanel2 = new JDatePanelImpl(model2, p);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePicker2.setBounds(154, 252, 145, 20);
		contentPane.add(datePicker2);

		btnTimKiem.addActionListener(this);
		btnTroVe.addActionListener(this);

		qlNV = new QuanLyNhanVien();
		List<NhanVien> list = qlNV.docTuBang();
		cboNV.addItem("---------------------------------------------------");
		for (NhanVien Nhanvien : list) {
			String[] rowData = { Nhanvien.getMaNV(), Nhanvien.getTenNV(), Nhanvien.getNgaySinh().toString(),
					Nhanvien.getcMND(), Nhanvien.getSdt(), Nhanvien.getEmail() };
			cboNV.addItem(Nhanvien.getTenNV());
		}
		model2.setValue(Date.valueOf(LocalDate.now()));
		model1.setValue(Date.valueOf(LocalDate.now()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTroVe)) {
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		} else if (o.equals(btnTimKiem)) {
			String tenKH = txtKH.getText();
			String tenNV = (String) cboNV.getSelectedItem();
			if (tenNV.equals("---------------------------------------------------"))
				tenNV = "";
			Date tuNgay = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() + 1, model1.getDay()));
			Date denNgay = Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() + 1, model2.getDay()));
			int trangThai;
			if (rabDaTra.isSelected())
				trangThai = 1;
			else
				trangThai = 0;
			GiaoDienDanhSachHoaDon hd = new GiaoDienDanhSachHoaDon(tenKH, tenNV, tuNgay, denNgay, trangThai,maTK);
			hd.setVisible(true);
			setVisible(false);
			
		}
	}
}
