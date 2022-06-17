package GiaoDien;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Database.Database;
import DieuKhien.QuanLyNhanVien;
import DieuKhien.QuanLyTheLoai;
import DoiTuong.TheLoai;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GiaoDienQuanLyTheLoai extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scroll;
	private JLabel lblmaTL;
	private JTextField txtmaTL;
	private JLabel lbltenTL;
	private JTextField txttenTL;
	private JPanel panel_1;
	private QuanLyTheLoai qltl;
	private JButton btnthem, btncapNhat, btnxoaRong, btntroVe;
	private QuanLyNhanVien NV;
	private String maTK;

	public GiaoDienQuanLyTheLoai(String maTK) {
		this.maTK = maTK;
		setTitle("Giao Diện Quản Lý Thể Loại");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 373);
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

		String[] header = "Mã Thể Loại; Tên Thể Loại".split(";");
		tableModel = new DefaultTableModel(header, 0);
		scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.addMouseListener(this);
		Database.getInstance().getConnection();
		scroll.setBounds(10, 11, 383, 317);
		contentPane.add(scroll);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(403, 11, 323, 317);
		contentPane.add(panel);
		panel.setLayout(null);

		lblmaTL = new JLabel("Mã Thể Loại:");
		lblmaTL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblmaTL.setBounds(10, 51, 121, 31);
		panel.add(lblmaTL);

		txtmaTL = new JTextField();
		txtmaTL.setColumns(10);
		txtmaTL.setBounds(141, 55, 151, 27);
		panel.add(txtmaTL);

		lbltenTL = new JLabel("Tên Thể Loại:");
		lbltenTL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltenTL.setBounds(10, 113, 121, 31);
		panel.add(lbltenTL);

		txttenTL = new JTextField();
		txttenTL.setColumns(10);
		txttenTL.setBounds(141, 117, 151, 27);
		panel.add(txttenTL);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Thanh C\u00F4ng C\u1EE5", TitledBorder.LEADING, TitledBorder.TOP,
				null, Color.BLUE));
		panel_1.setBounds(10, 174, 303, 132);
		panel.add(panel_1);
		panel_1.setLayout(null);

		btnthem = new JButton("Thêm");
		btnthem.setBackground(Color.LIGHT_GRAY);
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setBounds(24, 25, 115, 31);
		btnthem.setIcon(new ImageIcon("icon/add_16px.png"));
		panel_1.add(btnthem);

		btncapNhat = new JButton("Cập Nhật");
		btncapNhat.setBackground(Color.LIGHT_GRAY);
		btncapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncapNhat.setBounds(24, 89, 115, 31);
		btncapNhat.setIcon(new ImageIcon("icon/update_16px.png"));
		panel_1.add(btncapNhat);

		btnxoaRong = new JButton("Xóa Rỗng");
		btnxoaRong.setBackground(Color.LIGHT_GRAY);
		btnxoaRong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnxoaRong.setBounds(179, 25, 115, 31);
		btnxoaRong.setIcon(new ImageIcon("icon/emptydelete_16px.png"));
		panel_1.add(btnxoaRong);

		btntroVe = new JButton("Trở Về");
		btntroVe.setBackground(Color.LIGHT_GRAY);
		btntroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btntroVe.setIcon(new ImageIcon("icon/back_to_16px.png"));
		btntroVe.setBounds(179, 89, 115, 31);
		panel_1.add(btntroVe);

		JLabel lblthongTinTL = new JLabel("Thông Tin Thể Loại");
		lblthongTinTL.setForeground(Color.BLUE);
		lblthongTinTL.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblthongTinTL.setBounds(79, 10, 187, 31);
		panel.add(lblthongTinTL);
		btnthem.addActionListener(this);
		btncapNhat.addActionListener(this);
		btnxoaRong.addActionListener(this);
		btntroVe.addActionListener(this);
		txtmaTL.setEditable(false);
		capNhatBangDuLieu();
	}

	public void capNhatBangDuLieu() {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tableModel.removeRow(i - 1);
		}
		qltl = new QuanLyTheLoai();
		List<TheLoai> list = qltl.docTuBang();
		for (TheLoai theLoai : list) {
			String[] rowData = { theLoai.getMaTheLoai(), theLoai.getTenTheLoai() };
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
	}

	public void dienThongTin(int row) {
		if (row != -1) {
			btnthem.setVisible(false);
			String ma = (String) table.getValueAt(row, 0);
			qltl = new QuanLyTheLoai();
			List<TheLoai> list = qltl.docTuBang();
			for (TheLoai theLoai : list) {
				if (ma.trim().equals(theLoai.getMaTheLoai().trim())) {
					txtmaTL.setText(theLoai.getMaTheLoai());
					txttenTL.setText(theLoai.getTenTheLoai());
				}
			}
		}
	}

	private boolean kTraDinhDang() {
		String ten = txttenTL.getText();
		if (ten.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Tên thể loại không được để trống!");
			txttenTL.requestFocus();
			txttenTL.selectAll();
			return false;
		} else if (!ten.matches("^[\\p{L} ]+$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng điền tên chính xác!");
			txttenTL.requestFocus();
			txttenTL.selectAll();
			return false;
		}
		qltl = new QuanLyTheLoai();
		List<TheLoai> list = qltl.docTuBang();
		for (TheLoai theLoai : list) {
			if(ten.equals(theLoai.getTenTheLoai())) {
				JOptionPane.showMessageDialog(this, "Thể loại đã có trong hệ thống!");
				txttenTL.requestFocus();
				txttenTL.selectAll();
				return false;
			}	
		}
		
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table)) {
			dienThongTin(table.getSelectedRow());
			txtmaTL.setEditable(false);
			txttenTL.setEditable(true);
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
		Object ob = e.getSource();
		if (ob.equals(btnthem)) {
			if (kTraDinhDang()) {
				qltl = new QuanLyTheLoai();
				String maTL = qltl.tuDongLayMa();
				String tenTL = txttenTL.getText();
				if (qltl.themTheLoai(maTL, tenTL)) {
					capNhatBangDuLieu();
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					btnthem.setVisible(true);
					txtmaTL.setText("");
					txttenTL.setText("");
				} else
					JOptionPane.showMessageDialog(this, "Thêm không thành công");
			}
		} else if (ob.equals(btncapNhat)) {
			if (kTraDinhDang()) {
				String maTL = txtmaTL.getText().trim();
				String tenTL = txttenTL.getText();
				if (qltl.capNhatTL(maTL, tenTL)) {
					capNhatBangDuLieu();
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
					btnthem.setVisible(true);
					txtmaTL.setText("");
					txttenTL.setText("");
				} else
					JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
			}
		} else if (ob.equals(btnxoaRong)) {
			btnthem.setVisible(true);
			txtmaTL.setText("");
			txttenTL.setText("");
		}  else if (ob.equals(btntroVe)) {
	
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		}

	}
}
