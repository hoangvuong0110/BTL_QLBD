package GiaoDien;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.BiPredicate;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import constant.HuongDanSuDungConstant;
import testJtree.JTreeUtil;
import testJtree.TradingProjectDataService;
import testJtree.TradingProjectTreeRenderer;
import testJtree.TreeFilterDecorator;

public class GD_HuongDanSuDung extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTree tree;
	private JTextField txtTimKiem;
	private JButton btnDong;
	private JButton btnHome;
	private JEditorPane txtText;
	private JButton btnMo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_HuongDanSuDung frame = new GD_HuongDanSuDung();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GD_HuongDanSuDung() {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1800, 1000);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Hướng dẫn sử dụng");

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBackground(Color.WHITE);
		pnlTimKiem.setBounds(0, 0, 1782, 54);
		contentPane.add(pnlTimKiem);
		pnlTimKiem.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tìm kiếm:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 8, 105, 35);
		pnlTimKiem.add(lblNewLabel);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiem.setBounds(129, 13, 405, 30);
		pnlTimKiem.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnDong = new JButton("");
		//btnDong.setIcon(new ImageIcon(GD_HuongDanSuDung.class.getResource("/img/minus_30px.png")));
		btnDong.setBounds(1626, 13, 40, 30);
		pnlTimKiem.add(btnDong);

		btnHome = new JButton("");
		//btnHome.setIcon(new ImageIcon(GD_HuongDanSuDung.class.getResource("/img/home_page_30px.png")));
		btnHome.setBounds(1730, 13, 40, 30);
		pnlTimKiem.add(btnHome);

		JPanel pnlTree = new JPanel();
		pnlTree.setBackground(Color.WHITE);
		pnlTree.setBounds(0, 56, 363, 897);
		contentPane.add(pnlTree);
		pnlTree.setLayout(new BoxLayout(pnlTree, BoxLayout.X_AXIS));

		JScrollPane scrollPaneTree = new JScrollPane();
		pnlTree.add(scrollPaneTree);

		TreeNode projectHierarchyTreeNode = TradingProjectDataService.instance.getProjectHierarchy();
		tree = new JTree(projectHierarchyTreeNode);
		//JTreeUtil.setTreeExpandedState(tree, true);
		TreeFilterDecorator filterDecorator = TreeFilterDecorator.decorate(tree, createUserObjectMatcher(), txtTimKiem);

		btnMo = new JButton("");
		//btnMo.setIcon(new ImageIcon(GD_HuongDanSuDung.class.getResource("icon/search_16px.png")));
		btnMo.setBounds(1678, 13, 40, 30);
		pnlTimKiem.add(btnMo);
		tree.setCellRenderer(new TradingProjectTreeRenderer(() -> filterDecorator.getFilterField().getText()));

		tree.setRowHeight(25);
		tree.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		tree.setBounds(0, 0, 300, 490);
		scrollPaneTree.setViewportView(tree);

		JPanel pnlHDSD = new JPanel();
		pnlHDSD.setBackground(Color.WHITE);
		pnlHDSD.setBounds(365, 56, 1417, 897);
		contentPane.add(pnlHDSD);
		pnlHDSD.setLayout(new BoxLayout(pnlHDSD, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		pnlHDSD.add(scrollPane);

		txtText = new JEditorPane();
		txtText.setContentType("text/html");
		txtText.setText(HuongDanSuDungConstant.TIM_KIEM_HOP_DONG);
		txtText.setEditable(false);
		txtText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(txtText);

		//dangKySuKien();
		//JTreeUtil.setTreeExpandedState(tree, false);
		
		
		//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private static BiPredicate<Object, String> createUserObjectMatcher() {
		return (userObject, textToFilter) -> {
			return userObject.toString().toLowerCase().contains(textToFilter);
		};
	}

	/**
	 * Đăng ký sự kiện
	 */
	private void dangKySuKien() {
		btnHome.addActionListener(this);
		btnDong.addActionListener(this);
		btnMo.addActionListener(this);
		tree.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHome)) {
			txtText.setText(HuongDanSuDungConstant.GIOI_THIEU);
			tree.clearSelection();
			txtTimKiem.setText("");
		}
//		if (o.equals(btnDong)) {
//			JTreeUtil.setTreeExpandedState(tree, false);
//		}
//		if (o.equals(btnMo)) {
//			JTreeUtil.setTreeExpandedState(tree, true);
//		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath()
					.getLastPathComponent();
			
			switch (selectedNode.getUserObject().toString()) {
//			Hóa đơn
			case "Lập hóa đơn":
				txtText.setText(HuongDanSuDungConstant.TEST);
				break;
			case "Tìm kiếm hóa đơn":
				txtText.setText(HuongDanSuDungConstant.TIM_KIEM_HOP_DONG);
				break;
//				Xe máy
			case "Thêm xe máy":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Xóa xe máy":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Cập nhật xe máy":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Tìm kiếm xe máy":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
//				Nhân viên
			case "Thêm nhân viên hành chính":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Cập nhật nhân viên hành chính":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Xóa nhân viên hành chính":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Tìm kiếm nhân viên hành chính":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Thêm nhân viên kỹ thuật":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Cập nhật nhân viên kỹ thuật":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Xóa nhân viên kỹ thuật":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Tìm kiếm nhân viên kỹ thuật":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Đổi mật khẩu":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
//				Khách hàng
			case "Thêm khách hàng":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Cập nhật khách hàng":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Xóa khách hàng":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Tìm kiếm khách hàng":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
//				Dòng xe
			case "Thêm dòng xe":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Cập nhật dòng xe":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Xóa dòng xe":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
//				Hãng xe
			case "Thêm hãng xe":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Cập nhật hãng xe":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Xóa hãng xe":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
//				Loại xe
			case "Thêm loại xe":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Cập nhật loại xe":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Xóa loại xe":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
//				Xuất xứ
			case "Thêm xuất xứ":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Cập nhật xuất xứ":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Xóa xuất xứ":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
//				Thống kê
			case "Thống kê":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
//				Bảo hành
			case "Thêm phiếu bảo hành":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Thêm mục bảo hành":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Cập nhật mục bảo hành":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
			case "Xóa mục bảo hành":
				txtText.setText(selectedNode.getUserObject().toString());
				break;
//				Hợp đồng
			case "Tìm kiếm hợp đồng":
				txtText.setText(HuongDanSuDungConstant.TIM_KIEM_HOP_DONG);
				break;
			case "Tìm kiếm hợp đồng theo ngày":
//				txtText.setText(HuongDanSuDungConstant.TIM_KIEM_HOP_DONG_THEO_NGAY);
				break;
			case "Xem chi tiết hợp đồng":
//				txtText.setText(HuongDanSuDungConstant.XEM_CHI_TIET_HOP_DONG);
				break;
				
			}
			
		} catch (Exception e2) {
			// TODO: handle exception
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
}
