package testJtree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.function.Supplier;

public class TradingProjectTreeRenderer extends DefaultTreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SPAN_FORMAT = "<span style='color:%s;'>%s</span>";
	private Supplier<String> filterTextSupplier;

	public TradingProjectTreeRenderer(Supplier<String> filterTextSupplier) {
		this.filterTextSupplier = filterTextSupplier;
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Object userObject = node.getUserObject();
		String text = String.format(SPAN_FORMAT, "rgb(0,0,0)", renderFilterMatch(node, userObject.toString()));
		this.setText("<html>" + text + "</html>");
		return this;
	}

	private String renderFilterMatch(DefaultMutableTreeNode node, String text) {
		if (node.isRoot()) {
			return text;
		}
		String textToFilter = filterTextSupplier.get();
		return HtmlHighlighter.highlightText(text, textToFilter);
	}
}