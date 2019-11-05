package net.raysforge.easyswing;

import javax.swing.tree.TreePath;

public interface ValueForPathChangedListener {
	
	// return false to veto the change.
	public boolean valueForPathChanged(TreePath path, Object newValue);
	

}
