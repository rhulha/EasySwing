package net.raysforge.easyswing;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JInternalFrame;

public class EasyInternalFrame {

	JInternalFrame jif;
	
	Scrollable mainContent;

	public EasyInternalFrame(String name, int w, int h) {
		jif = new JInternalFrame(name, true, true, true, true);
		jif.setSize(w, h);
		jif.setVisible(true);
		jif.putClientProperty("easyWrapper", this);
	}

	public Component getInternalFrame() {
		return jif;
	}

	public EasyTable setTableAsMainContent(final Class<?>[] columnClasses) {
		EasyTable et = new EasyTable(columnClasses);
		mainContent = et;
		jif.getContentPane().add(et.getScrollPane(), BorderLayout.CENTER);
		return et;
	}

	public Scrollable getMainContent() {
		return mainContent;
		
	}

}
