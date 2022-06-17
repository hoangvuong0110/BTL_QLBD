package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import DatePicker.DateLabelFormatter;
import DieuKhien.QuanLyNhanVien;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GiaoDienTimKiemKhachHang extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtKH;
	private JTextField txtSDT;
	private JTextField txtCMND;
	private Properties p;
	private UtilDateModel model1, model2;
	private JDatePanelImpl datePanel1, datePanel2;
	private JDatePickerImpl datePicker1, datePicker2;
	private JButton btnTimKiem, btnTroVe;
	private String maTK;
	private QuanLyNhanVien NV;

	public GiaoDienTimKiemKhachHang(String maTK) {
		this.maTK = maTK;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 434);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Tên Khách Hàng:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 77, 124, 23);
		contentPane.add(label);

		txtKH = new JTextField();
		txtKH.setColumns(10);
		txtKH.setBounds(144, 80, 240, 20);
		contentPane.add(txtKH);

		JLabel lblTmKimKhch = new JLabel("Tìm Kiếm Khách Hàng");
		lblTmKimKhch.setForeground(new Color(0, 0, 255));
		lblTmKimKhch.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTmKimKhch.setBounds(110, 11, 260, 39);
		contentPane.add(lblTmKimKhch);

		JLabel lblNgyngK = new JLabel("Ngày Đăng Ký:");
		lblNgyngK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgyngK.setBounds(10, 213, 124, 23);
		contentPane.add(lblNgyngK);

		JLabel label_2 = new JLabel("Từ Ngày:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(65, 247, 69, 23);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("Đến Ngày:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(65, 290, 69, 23);
		contentPane.add(label_3);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại:");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSinThoi.setBounds(10, 122, 124, 23);
		contentPane.add(lblSinThoi);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(144, 125, 240, 20);
		contentPane.add(txtSDT);

		JLabel lblCmndCccd = new JLabel("CMND / CCCD :");
		lblCmndCccd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCmndCccd.setBounds(10, 168, 124, 23);
		contentPane.add(lblCmndCccd);

		txtCMND = new JTextField();
		txtCMND.setColumns(10);
		txtCMND.setBounds(144, 171, 240, 20);
		contentPane.add(txtCMND);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBackground(Color.LIGHT_GRAY);
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(65, 361, 110, 23);
		btnTimKiem.setIcon(new ImageIcon("icon/search_16px.png"));
		contentPane.add(btnTimKiem);

		btnTroVe = new JButton("Trở Về");
		btnTroVe.setBackground(Color.LIGHT_GRAY);
		btnTroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTroVe.setBounds(236, 361,110, 23);
		btnTroVe.setIcon(new ImageIcon("icon/back_to_16px.png"));
		contentPane.add(btnTroVe);

		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model1 = new UtilDateModel();
		datePanel1 = new JDatePanelImpl(model1, p);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		datePicker1.setBounds(154, 250, 171, 20);
		contentPane.add(datePicker1);
		model1.setValue(Date.valueOf(LocalDate.now()));

		model2 = new UtilDateModel();
		datePanel2 = new JDatePanelImpl(model2, p);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePicker2.setBounds(154, 293, 171, 20);
		contentPane.add(datePicker2);
		model2.setValue(Date.valueOf(LocalDate.now()));

		ButtonGroup group = new ButtonGroup();

		btnTimKiem.addActionListener(this);
		btnTroVe.addActionListener(this);
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
			String sdt = txtSDT.getText();
			String cmnd = txtCMND.getText();
			Date tuNgay = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() + 1, model1.getDay()));
			Date denNgay = Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() + 1, model2.getDay()));
			GiaoDienQuanLyKhachHang kh = new GiaoDienQuanLyKhachHang(tenKH, sdt, cmnd, tuNgay, denNgay);
			kh.setVisible(true);
			setVisible(false);
		}
	}
}
