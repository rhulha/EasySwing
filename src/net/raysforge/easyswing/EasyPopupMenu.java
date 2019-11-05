package net.raysforge.easyswing;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class EasyPopupMenu {
	
	JPopupMenu pop = new JPopupMenu();
	private ActionListener al;

	public EasyPopupMenu(ActionListener al) {
		this.al = al;
		pop.putClientProperty("easyWrapper", this);
	}
	
	public JMenuItem add(String text) {
		JMenuItem mi = new JMenuItem(text);
		mi.addActionListener(al);
		mi.setActionCommand(text);
		pop.add(mi);
		return mi;
	}

	

}
