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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DieuKhien.QuanLyNhanVien;
import DieuKhien.QuanLyQuocGia;
import DieuKhien.QuanLyTheLoai;
import DoiTuong.QuocGia;
import DoiTuong.TheLoai;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class GiaoDienQuanLyQuocGia extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JPanel panel;
	private JLabel lblthongTinQG;
	private JLabel lblmaQG;
	private JLabel lbltenQG;
	private JPanel panel_1;
	private JButton btnthem;
	private JButton btncapNhat;
	private JButton btnxoaRong;
	private JButton btntroVe;
	private JTextField txtmaQG;
	private JTextField txttenQG;
	private QuanLyQuocGia qlqg;
	private QuanLyNhanVien NV;
	private String maTK;

	public GiaoDienQuanLyQuocGia(String maTK) {
		this.maTK = maTK;
		setTitle("Giao Diện Quản Lý Quốc Gia");
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

		String[] header = "Mã Quốc Gia; Tên Quốc Gia".split(";");
		tableModel = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.addMouseListener(this);
		scrollPane.setBounds(10, 10, 383, 317);
		contentPane.add(scrollPane);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(403, 10, 321, 317);
		contentPane.add(panel);
		panel.setLayout(null);

		lblthongTinQG = new JLabel("Thông Tin Quốc Gia");
		lblthongTinQG.setForeground(Color.BLUE);
		lblthongTinQG.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblthongTinQG.setBounds(61, 11, 232, 31);
		panel.add(lblthongTinQG);

		lblmaQG = new JLabel("Mã Quốc Gia:");
		lblmaQG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblmaQG.setBounds(10, 51, 121, 31);
		panel.add(lblmaQG);

		lbltenQG = new JLabel("Tên Quốc Gia:");
		lbltenQG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltenQG.setBounds(10, 111, 121, 31);
		panel.add(lbltenQG);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Thanh C\u00F4ng C\u1EE5", TitledBorder.LEADING, TitledBorder.TOP,

				null, Color.BLUE));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 175, 303, 132);
		panel.add(panel_1);

		btnthem = new JButton("Thêm");
		btnthem.setBackground(Color.LIGHT_GRAY);
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setIcon(new ImageIcon("icon/add_16px.png"));
		btnthem.setBounds(24, 25, 115, 31);
		panel_1.add(btnthem);

		btncapNhat = new JButton("Cập Nhật");
		btncapNhat.setBackground(Color.LIGHT_GRAY);
		btncapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncapNhat.setIcon(new ImageIcon("icon/update_16px.png"));
		btncapNhat.setBounds(24, 89, 115, 31);
		panel_1.add(btncapNhat);

		btnxoaRong = new JButton("Xóa Rỗng");
		btnxoaRong.setBackground(Color.LIGHT_GRAY);
		btnxoaRong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnxoaRong.setIcon(new ImageIcon("icon/emptydelete_16px.png"));
		btnxoaRong.setBounds(170, 25, 115, 31);
		panel_1.add(btnxoaRong);

		btntroVe = new JButton("Trở về");
		btntroVe.setBackground(Color.LIGHT_GRAY);
		btntroVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btntroVe.setIcon(new ImageIcon("icon/back_to_16px.png"));
		btntroVe.setBounds(170, 89, 115, 31);
		panel_1.add(btntroVe);

		txtmaQG = new JTextField();
		txtmaQG.setEditable(false);
		txtmaQG.setColumns(10);
		txtmaQG.setBounds(130, 51, 151, 27);
		panel.add(txtmaQG);

		txttenQG = new JTextField();
		txttenQG.setColumns(10);
		txttenQG.setBounds(130, 119, 151, 27);
		panel.add(txttenQG);
		btnthem.addActionListener(this);
		btncapNhat.addActionListener(this);
		btnxoaRong.addActionListener(this);
		btntroVe.addActionListener(this);
		txtmaQG.setEditable(false);
		capNhatBangDuLieu();
	}

	public void capNhatBangDuLieu() {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			tableModel.removeRow(i - 1);
		}
		qlqg = new QuanLyQuocGia();
		List<QuocGia> list = qlqg.docTuBang();
		for (QuocGia quocGia : list) {
			String[] rowData = { quocGia.getMaQuocGia(), quocGia.getTenQuocGia() };
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
	}

	private boolean kTraDinhDang() {
		String ten = txttenQG.getText();
		if (ten.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Tên Quốc Gia không được để trống!");
			txttenQG.requestFocus();
			txttenQG.selectAll();
			return false;
		} else if (!ten.matches("^[\\p{L} ]+$")) {
			JOptionPane.showMessageDialog(this, "Vui lòng điền tên chính xác!");
			txttenQG.requestFocus();
			txttenQG.selectAll();
			return false;
		}
		qlqg = new QuanLyQuocGia();
		List<QuocGia> list = qlqg.docTuBang();
		for (QuocGia quocGia : list) {
			if(ten.equals(quocGia.getTenQuocGia())) {
				JOptionPane.showMessageDialog(this, "Quốc Gia đã có trong hệ thống!");
				txttenQG.requestFocus();
				txttenQG.selectAll();
				return false;
			}
		}
		
		return true;
	}

	public void dienThongTin(int row) {
		if (row != -1) {
			btnthem.setVisible(false);
			String ma = (String) table.getValueAt(row, 0);
			qlqg = new QuanLyQuocGia();
			List<QuocGia> list = qlqg.docTuBang();
			for (QuocGia quocGia : list) {
				if (ma.trim().equals(quocGia.getMaQuocGia().trim())) {
					txtmaQG.setText(quocGia.getMaQuocGia());
					txttenQG.setText(quocGia.getTenQuocGia());
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table)) {
			dienThongTin(table.getSelectedRow());
			txtmaQG.setEditable(false);
			txttenQG.setEditable(true);
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
				qlqg = new QuanLyQuocGia();
				String maQG = qlqg.tuDongLayMa();
				String tenQG = txttenQG.getText();
				if (qlqg.themTheLoai(maQG, tenQG)) {
					capNhatBangDuLieu();
					JOptionPane.showMessageDialog(this, "Thêm thành công");
				} else
					JOptionPane.showMessageDialog(this, "Thêm không thành công");
			}
		} else if (ob.equals(btncapNhat)) {
			if (kTraDinhDang()) {
				String maQG = txtmaQG.getText().trim();
				String tenQG = txttenQG.getText();
				if (qlqg.capNhatTL(maQG, tenQG)) {
					capNhatBangDuLieu();
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				} else
					JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
			}
		} else if (ob.equals(btnxoaRong)) {
			btnthem.setVisible(true);
			txtmaQG.setText("");
			txttenQG.setText("");
		}  else if (ob.equals(btntroVe)) {
	
			NV = new QuanLyNhanVien();
			String maNV = NV.layMaNV(maTK);
			GiaoDienTong t = new GiaoDienTong(maNV);
			t.setVisible(true);
			setVisible(false);
		}

	}
}
