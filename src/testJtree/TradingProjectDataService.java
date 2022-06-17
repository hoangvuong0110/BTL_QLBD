package testJtree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public enum TradingProjectDataService {
	instance;

	private DefaultMutableTreeNode rootNode;

	TradingProjectDataService() {
		rootNode = new DefaultMutableTreeNode("Hướng dẫn sử dụng");
		addModule("Hóa đơn", "Lập hóa đơn", "Tìm kiếm hóa đơn");
		addModule("Xe máy", "Thêm xe máy", "Xóa xe máy", "Cập nhật xe máy", "Tìm kiếm xe máy");
		addModule("Bảo hành", "Thêm phiếu bảo hành", "Thêm mục bảo hành", "Xóa mục bảo hành", "Cập nhật mục bảo hành");
		addModule("Nhân Viên", "Thêm nhân viên hành chính", "Cập nhật nhân viên hành chính", "Xóa nhân viên hành chính",
				"Tìm kiếm nhân viên hành chính", "Thêm nhân viên kỹ thuật", "Cập nhật nhân viên kỹ thuật",
				"Xóa nhân viên kỹ thuật", "Tìm kiếm nhân viên kỹ thuật", "Đổi mật khẩu");
		addModule("Khách hàng", "Thêm khách hàng", "Cập nhật khách hàng", "Xóa khách hàng", "Tìm kiếm khách hàng");
		addModule("Hợp đồng", "Tìm kiếm hợp đồng", "Tìm kiếm hợp đồng theo ngày", "Xem chi tiết hợp đồng");
		addModule("Dòng xe", "Thêm dòng xe", "Cập nhật dòng xe", "Xóa dòng xe");
		addModule("Hãng xe", "Thêm hãng xe", "Cập nhật hãng xe", "Xóa hãng xe");
		addModule("Loại xe", "Thêm loại xe", "Cập nhật loại xe", "Xóa loại xe");
		addModule("Xuất xứ", "Thêm xuất xứ", "Cập nhật xuất xứ", "Xóa xuất xứ");
		addModule("Thống kê");
	}

	private void addModule(String module, String... projects) {
		DefaultMutableTreeNode moduleNode = new DefaultMutableTreeNode(module);
		rootNode.add(moduleNode);
		for (String project : projects) {
			DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(project);
			moduleNode.add(projectNode);
		}
	}

	public TreeNode getProjectHierarchy() {
		return rootNode;
	}
}