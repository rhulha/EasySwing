package net.raysforge.easyswing;

import java.awt.Container;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EasyTabbedPane implements Scrollable {

	JTabbedPane pane;
	
	HashMap<Container, Scrollable> map = new HashMap<>(); 

	public EasyTabbedPane() {
		pane = new JTabbedPane();
	}

	public void addTab(String title, Scrollable s) {
		map.put(s.getScrollPane(), s);
		pane.addTab(title, s.getScrollPane());
	}

	/*
	public void addTab(String title, Container c) {
		pane.addTab(title, c);
	}
	*/

	@Override
	public JComponent getScrollPane() {
		return pane;
	}

	public Scrollable getActiveTab() {
		return map.get(pane.getSelectedComponent());
	}

	public String getActiveTabName() {
		return pane.getTitleAt(pane.getSelectedIndex());
	}

}
