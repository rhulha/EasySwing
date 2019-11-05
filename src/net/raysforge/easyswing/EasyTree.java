package net.raysforge.easyswing;

import java.awt.event.*;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.*;
import javax.swing.tree.*;

//http://www.java-forum.org/java-faq-beitraege/23430-jtree-anwendung-1-directorychooser.html

public class EasyTree implements Scrollable {
	
	JTree tree = new JTree();
	JScrollPane scroller = new JScrollPane(tree);
	
	private DefaultMutableTreeNode rootNode;
	private EasyTreeModel model;
	
	public EasyTree(String rootNodeText) {

		rootNode = new DefaultMutableTreeNode(rootNodeText, true);
		model = new EasyTreeModel(rootNode);
		
		tree.setModel(model);
		tree.setShowsRootHandles(true);
		tree.putClientProperty("easyWrapper", this);
		
		// tree.setDragEnabled(true);
		
		//final LazyLoadingTreeController controller = new LazyLoadingTreeController(model);
		//tree.addTreeWillExpandListener(controller);
		
	}

	public JTree getJTree() {
		return tree;
	}

	public JScrollPane getScrollPane() {
		return scroller;
	}
	
	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}

	public EasyTreeModel getModel() {
		return model;
	}

	public void addTreeSelectionListener(TreeSelectionListener tsl) {
		tree.addTreeSelectionListener(tsl);
	}

	public void addSelectionListener(TreeSelectionListener tsl) {
		tree.addTreeSelectionListener(tsl);
	}

	public DefaultMutableTreeNode addNode(String name) {
		DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(name);
		model.insertNodeInto(dmtn, rootNode, rootNode.getChildCount());
		return dmtn;
	}

	public DefaultMutableTreeNode addNode(String name, DefaultMutableTreeNode parentNode) {
		DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(name);
		model.insertNodeInto(dmtn, parentNode, parentNode.getChildCount());
		return dmtn;
	}

	public DefaultMutableTreeNode getSelectedNode() {
		return getSelectedNode(tree.getSelectionPath().getPathCount()-1);
	}
	
	public DefaultMutableTreeNode getSelectedNode(int i) {
		TreePath selectionPath = tree.getSelectionPath();
		if( selectionPath.getPathCount() > i)
			return (DefaultMutableTreeNode) selectionPath.getPathComponent(i);
		return null;
	}

	public boolean isSelected(int i) {
		return ( tree.getSelectionPath() != null && tree.getSelectionPath().getPathCount() > i);
	}

	public void setShowsRootHandles(boolean b) {
		tree.setShowsRootHandles(b);
	}

	public void setEditable() {
		tree.setEditable(true);
	}

	public void addProperyChangeListener(PropertyChangeListener pcl) {
		tree.addPropertyChangeListener(pcl);
	}

	// for rename events
	public void addTreeModelListener(TreeModelListener tml) {
		tree.getModel().addTreeModelListener(tml);
	}

	public void setValueForPathChangedListener(ValueForPathChangedListener vfpcl) {
		model.setValueForPathChangedListener( vfpcl);
	}

	public TreePath getSelectionPath() {
		return tree.getSelectionPath();
	}

	public void setEditable(boolean b) {
		tree.setEditable(b);
	}

	public void expandRow(int i) {
		tree.expandRow(i);
	}

	public void repaint() {
		tree.repaint();
	}

	public void startEditingAtPath(TreePath tp) {
		tree.startEditingAtPath(tp);
	}

	public void startEditing(DefaultMutableTreeNode newChild) {
		tree.expandRow(0);
		tree.repaint();
		tree.startEditingAtPath(new TreePath(getModel().getPathToRoot(newChild)));
	}

	// (single) listen for dbl click or enter
	public void addActionListener(final ActionListener al) {

		final EasyTree et=this;
		tree.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==2){
		        	al.actionPerformed(new ActionEvent(et, 0, "treeAction"));
		        }
		    }
		});
		
	}

	public void addTreeWillExpandListener(final EasyTreeWillExpandListener treeWillExpandListener) {
		tree.addTreeWillExpandListener(new TreeWillExpandListener() {

			@Override
			public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
				treeWillExpandListener.treeWillExpand(event);
			}

			@Override
			public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {

			}
		});
		
	}

	public void setSelection(TreeNode node) {
		TreeNode[] pathToRoot = model.getPathToRoot(node);
		TreePath tp = new TreePath(pathToRoot);
		tree.setSelectionPath(tp);
		
	}

}
