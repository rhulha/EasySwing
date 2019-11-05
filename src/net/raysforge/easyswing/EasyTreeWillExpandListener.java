package net.raysforge.easyswing;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.ExpandVetoException;

public interface EasyTreeWillExpandListener {
	
	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException;

}
