package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
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
import DieuKhien.QuanLyNhanVien;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class GiaoDienThongKeDoanhThu extends JFrame implements ActionListener,MouseListener{

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private Properties p;
	private UtilDateModel model1, model2;
	private JDatePanelImpl datePanel1, datePanel2;
	private JDatePickerImpl datePicker1, datePicker2;
	private JLabel lblthongKeTuan;
	private JLabel lbltongCong,lblngay,lblt;
	private JButton btninBaoCao,btnluu,btntroVe,btnthongKe;
	private QuanLyNhanVien NV;
	private String maTK;
	
	
	
	public GiaoDienThongKeDoanhThu(String maTK) {
		this.maTK = maTK;
		setTitle("Giao Diện Thống Kê Doanh Thu ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 705);
		setLocationRelativeTo(null);
		setResizable(false);
		taoGiaoDien();
	}
	public void taoGiaoDien() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] headers="STT;Ngày;Tổng Số Hóa Đơn;Tổng Tiền Thuê".split(";");
		tableModel =new DefaultTableModel(headers, 0);
		JScrollPane scrollPane = new JScrollPane(table=new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.addMouseListener(this);
		table.setRowHeight(28);
		scrollPane.setBounds(10, 113, 661, 419);
		contentPane.add(scrollPane);
		
		JLabel lbltuNgay = new JLabel("Từ Ngày :");
		lbltuNgay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltuNgay.setBounds(135, 76, 133, 27);
		contentPane.add(lbltuNgay);
		
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model1 = new UtilDateModel(Date.valueOf(LocalDate.now()));
		datePanel1 = new JDatePanelImpl(model1, p);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		datePicker1.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker1.setBackground(Color.WHITE);
		contentPane.add(datePicker1);
		datePicker1.setBounds(236, 76, 119, 21);

		
		JLabel label = new JLabel("-");
		label.setBounds(365, 84, 23, 13);
		contentPane.add(label);
		
		model2 = new UtilDateModel(Date.valueOf(LocalDate.now()));
		datePanel2 = new JDatePanelImpl(model2, p);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePicker2.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker2.setBackground(Color.WHITE);
		contentPane.add(datePicker2);
		datePicker2.setBounds(379, 76, 119, 21);
		
		lblthongKeTuan = new JLabel("THỐNG KÊ DOANH THU ");
		lblthongKeTuan.setForeground(Color.BLUE);
		lblthongKeTuan.setHorizontalAlignment(SwingConstants.LEFT);
		lblthongKeTuan.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblthongKeTuan.setBounds(231, 10, 233, 36);
		contentPane.add(lblthongKeTuan);
		
		lbltongCong = new JLabel("Tổng Cộng :");
		lbltongCong.setIcon(new ImageIcon("D:\\eclipse_workspace\\PhatTrienUngDung\\icon\\money_bag_32px.png"));
		lbltongCong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltongCong.setBounds(10, 542, 142, 27);
		contentPane.add(lbltongCong);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Thanh C\u00F4ng C\u1EE5", TitledBorder.LEADING, TitledBorder.TOP,

						null, Color.BLUE));
		panel.setBackground(Color.WHITE);
		panel.setBounds(74, 579, 544, 79);
		contentPane.add(panel);
		
		btninBaoCao = new JButton("In Báo Cáo");
		btninBaoCao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btninBaoCao.setBackground(Color.LIGHT_GRAY);
		btninBaoCao.setBounds(24, 25, 115, 31);
		panel.add(btninBaoCao);
		
		btnluu = new JButton("Lưu");
		btnluu.setBackground(Color.LIGHT_GRAY);
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnluu.setBounds(231, 25, 99, 31);
		btnluu.setIcon(new ImageIcon("icon/update_16px.png"));
		panel.add(btnluu);
		
		btntroVe = new JButton("Trở Về");
		btntroVe.setBackground(Color.LIGHT_GRAY);
		btntroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btntroVe.setBounds(435, 25, 99, 31);
		btntroVe.setIcon(new ImageIcon("icon/back_to_16px.png"));	
		panel.add(btntroVe);
		
		btnthongKe = new JButton("Thống Kê");
		btnthongKe.setBackground(new Color(135, 206, 235));
		btnthongKe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//btnthongKe.setBackground(new Color(135, 206, 250));
		btnthongKe.setBounds(529, 72, 120, 31);
		btnthongKe.setIcon(new ImageIcon("icon/statistics_16px.png"));
		contentPane.add(btnthongKe);
		
		lblt = new JLabel("");
		lblt.setForeground(Color.RED);
		lblt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblt.setBounds(140, 542, 99, 27);
		contentPane.add(lblt);
		
		JLabel lblNgy = new JLabel("Ngày :");
		lblNgy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgy.setBounds(529, 0, 64, 27);
		contentPane.add(lblNgy);
		
		lblngay = new JLabel("\r\n");
		lblngay.setForeground(Color.RED);
		lblngay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblngay.setBounds(572, 0, 99, 27);
		contentPane.add(lblngay);
		LocalDate ngay=LocalDate.now();
		lblngay.setText(ngay+"");
		btninBaoCao.addActionListener(this);
		btnluu.addActionListener(this);
		btntroVe.addActionListener(this);
		btnthongKe.addActionListener(this);
		//capNhatBangDuLieu();
	}
	public void capNhatBangDuLieu() {	
		
		int dem = 1;
		int rowCount=table.getRowCount();
		for (int i = rowCount; i >0; i--) {
			tableModel.removeRow(i-1);
		}
		try {
			Date ngayBD = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() +1, model1.getDay()));
			Date ngayEnd = Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() +1, model2.getDay()));
			Connection con=Database.getInstance().getConnection();
			String sql="select * from [dbo].[ThongKeDoanhThuTheoNgay]('"+ngayBD+"','"+ngayEnd+"')";
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery(sql);
			while (rs.next()) {
				String ngay = rs.getString(1);
				int tong = rs.getInt(2);
				double tongTien = rs.getDouble(3);
				String[] rowData= {dem+"",ngay,tong+"",tongTien+""};
				tableModel.addRow(rowData);
				dem++;
			}
			table.setModel(tableModel);
			tinhTong();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	public void tinhTong() {
		double t=0;
		int rowCount=table.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			String tong=(String) table.getValueAt(i, 3);
			Double tien=Double.parseDouble(tong);
			t+=tien;
		}
		lblt.setText(t+" VND");
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(btnthongKe)) {
			capNhatBangDuLieu();
		}else if(o.equals(btntroVe)) {
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		}
		
	}
}
