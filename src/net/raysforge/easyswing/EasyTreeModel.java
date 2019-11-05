package net.raysforge.easyswing;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class EasyTreeModel extends DefaultTreeModel {

	private static final long serialVersionUID = 1414385075038417002L;

	public EasyTreeModel(TreeNode root) {
		super(root);
	}
	
	private ValueForPathChangedListener vfpcl=null;
	
	public void setValueForPathChangedListener(ValueForPathChangedListener vfpcl)
	{
		this.vfpcl = vfpcl;
	}
	
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		if( vfpcl != null) {
			if( vfpcl.valueForPathChanged(path, newValue))
				super.valueForPathChanged(path, newValue);
		} else {
			super.valueForPathChanged(path, newValue);
		}
	}

}
