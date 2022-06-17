package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
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
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;

public class GiaoDienThongKeDanhSachKhachHangQuaHanTra extends JFrame implements ActionListener,MouseListener{

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;

	private Properties p;
	private UtilDateModel model1, model2;
	private JDatePanelImpl datePanel1, datePanel2;
	private JLabel lblthongKeTuan;
	private JLabel lbltongso;
	private JButton btninBaoCao,btnluu,btntroVe;
	private JLabel lblngay;
	private JLabel lblngayhientai;
	private JLabel lblNgy;
	private JLabel lblt;
	private QuanLyNhanVien NV;
	private String maTK;
	
	
	
	public GiaoDienThongKeDanhSachKhachHangQuaHanTra(String maTK) {
		this.maTK = maTK;
		setTitle("Giao Diện Thống Kê Danh Sách Khách Hàng Quá Hạn Trả Đĩa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 705);
		setLocationRelativeTo(null);
		setResizable(false);
		taoGiaoDien();
	}
	public void taoGiaoDien() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] headers="Mã Khách Hàng;Tên Khách Hàng;Số Điện Thoại;Ngày Thuê;Ngày Đã Thuê;Số Ngày Trễ".split(";");
		tableModel =new DefaultTableModel(headers, 0);
		JScrollPane scrollPane = new JScrollPane(table=new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.addMouseListener(this);
		table.setRowHeight(28);
		scrollPane.setBounds(10, 96, 700, 436);
		contentPane.add(scrollPane);
		
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		model1 = new UtilDateModel(java.sql.Date.valueOf(LocalDate.now()));
		datePanel1 = new JDatePanelImpl(model1, p);

		
		JLabel label = new JLabel("-");
		label.setBounds(365, 84, 23, 13);
		contentPane.add(label);
		
		model2 = new UtilDateModel(java.sql.Date.valueOf(LocalDate.now()));
		datePanel2 = new JDatePanelImpl(model2, p);
		
		lblthongKeTuan = new JLabel("THỐNG KÊ DANH SÁCH KHÁCH HÀNG ĐÃ QUÁ HẠN TRẢ ĐĨA");
		lblthongKeTuan.setForeground(Color.BLUE);
		lblthongKeTuan.setHorizontalAlignment(SwingConstants.LEFT);
		lblthongKeTuan.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblthongKeTuan.setBounds(100, 35, 533, 36);
		contentPane.add(lblthongKeTuan);
		
		lbltongso = new JLabel("Tổng Số Khách Hàng Hiện Quá Hạn Trả :\r\n");
		lbltongso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltongso.setBounds(30, 542, 272, 27);
		contentPane.add(lbltongso);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Thanh C\u00F4ng C\u1EE5", TitledBorder.LEADING, TitledBorder.TOP,

						null, Color.BLUE));
		panel.setBackground(Color.WHITE);
		panel.setBounds(89, 579, 544, 79);
		contentPane.add(panel);
		
		btninBaoCao = new JButton("In Báo Cáo");
		btninBaoCao.setForeground(Color.BLACK);
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
		
		lblngay = new JLabel("Ngày :");
		lblngay.setForeground(Color.RED);
		lblngay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblngay.setBounds(624, 10, 93, 22);
		contentPane.add(lblngay);
		LocalDate ngayHienTai=LocalDate.now();
		lblngay.setText(ngayHienTai+"");
		
		lblngayhientai = new JLabel("");
		lblngayhientai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblngayhientai.setBounds(630, 0, 80, 22);
		contentPane.add(lblngayhientai);
		
		lblNgy = new JLabel("Ngày :");
		lblNgy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgy.setBounds(575, 10, 59, 22);
		contentPane.add(lblNgy);
		
		lblt = new JLabel("");
		lblt.setForeground(Color.RED);
		lblt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblt.setBounds(312, 544, 59, 22);
		contentPane.add(lblt);
		btninBaoCao.addActionListener(this);
		btnluu.addActionListener(this);
		btntroVe.addActionListener(this);
		capNhatBangDuLieu();
	}
	public void capNhatBangDuLieu() {
		int rowCount=table.getRowCount();
		for (int i = rowCount; i >0; i--) {
			tableModel.removeRow(i-1);
		}
		try {
			Connection con=Database.getInstance().getConnection();
			String sql="select * from [dbo].[ThongKeDanhSachKhachHangTreHan]()";
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery(sql);
			while (rs.next()) {
				String ma=rs.getString(1);
				String ten=rs.getString(2);
				String sdt=rs.getString(3);
				Date ngaylapHD=rs.getDate(4);
				int soNgayDaThue=rs.getInt(5);
				int soNgayTreHan=rs.getInt(6);
				String[] rowData= {ma,ten,sdt,ngaylapHD+"",soNgayDaThue+"",soNgayTreHan+""};
				tableModel.addRow(rowData);
			}
			table.setModel(tableModel);
			tinhTong();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void tinhTong() {
		int tong=0;
		int rowCoun=table.getRowCount();
		for (int i = 0; i < rowCoun; i++) {
			tong++;
		}
		lblt.setText(tong+"");
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
		if(o.equals(btntroVe)) {
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		}else if(o.equals(btnluu)) {
		
		}else if(o.equals(btninBaoCao)) {
			
		}
		
	}
}
