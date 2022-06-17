package testJtree;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.function.BiPredicate;

public class TreeFilterDecorator {
	private final JTree tree;
	private DefaultMutableTreeNode originalRootNode;
	private BiPredicate<Object, String> userObjectMatcher;
	private JTextField filterField;

	public TreeFilterDecorator(JTree tree, BiPredicate<Object, String> userObjectMatcher, JTextField filterField) {
		this.tree = tree;
		this.originalRootNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
		this.userObjectMatcher = userObjectMatcher;
		this.filterField = filterField;
	}

	public static TreeFilterDecorator decorate(JTree tree, BiPredicate<Object, String> userObjectMatcher, JTextField filterField) {
		TreeFilterDecorator tfd = new TreeFilterDecorator(tree, userObjectMatcher, filterField);
		tfd.init();
		return tfd;
	}

	public JTextField getFilterField() {
		return filterField;
	}

	private void init() {
		initFilterField();
	}

	private void initFilterField() {
		filterField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filterTree();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterTree();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filterTree();
			}
		});
	}

	private void filterTree() {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		String text = filterField.getText().trim().toLowerCase();
		if (text.equals("") && tree.getModel().getRoot() != originalRootNode) {
			model.setRoot(originalRootNode);
			JTreeUtil.setTreeExpandedState(tree, true);
		} else {
			DefaultMutableTreeNode newRootNode = matchAndBuildNode(text, originalRootNode);
			model.setRoot(newRootNode);
			JTreeUtil.setTreeExpandedState(tree, true);
		}
	}

	private DefaultMutableTreeNode matchAndBuildNode(final String text, DefaultMutableTreeNode oldNode) {
		if (!oldNode.isRoot() && userObjectMatcher.test(oldNode.getUserObject(), text)) {
			return JTreeUtil.copyNode(oldNode);
		}
		DefaultMutableTreeNode newMatchedNode = oldNode.isRoot() ? new DefaultMutableTreeNode(oldNode.getUserObject())
				: null;
		for (DefaultMutableTreeNode childOldNode : JTreeUtil.nodeChildren(oldNode)) {
			DefaultMutableTreeNode newMatchedChildNode = matchAndBuildNode(text, childOldNode);
			if (newMatchedChildNode != null) {
				if (newMatchedNode == null) {
					newMatchedNode = new DefaultMutableTreeNode(oldNode.getUserObject());
				}
				newMatchedNode.add(newMatchedChildNode);
			}
		}
		return newMatchedNode;
	}
}