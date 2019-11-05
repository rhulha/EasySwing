package net.raysforge.easyswing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class EasyPanel implements Scrollable {

	JPanel panel;
	JToolBar toolbar = null;
	JPanel statusbar;
	JLabel statusLabel;
	private JComponent center;

	public EasyPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.putClientProperty("easyWrapper", this);
	}

	public EasyPanel(JPanel panel) {
		this.panel = panel;
		panel.putClientProperty("easyWrapper", this);
	}

	public JButton addToolBarItem(String text, String actionCommand, ActionListener al) {
		JButton b = addToolBarItem(text);
		b.setActionCommand(actionCommand);
		b.addActionListener(al);
		return b;
	}

	public JButton addToolBarItem(Icon icon, String actionCommand, ActionListener al) {
		JButton b = new JButton(icon);
		getToolBar().add(b);
		b.setActionCommand(actionCommand);
		b.addActionListener(al);
		return b;
	}

	public JButton addToolBarItem(String text) {
		JButton jButton = new JButton(text);
		getToolBar().add(jButton);
		return jButton;
	}

	public JToolBar getToolBar() {
		if (toolbar == null) {
			toolbar = new JToolBar();
			panel.add(toolbar, BorderLayout.NORTH);
		}
		return toolbar;
	}

	public JTextField addToolBarTextField() {
		JTextField tf = new JTextField();
		getToolBar().add(tf);
		return tf;
	}

	public JPanel getStatusBar() {
		if (statusbar == null) {
			statusbar = new JPanel();

			// Border paddingBorder =
			// BorderFactory.createEmptyBorder(10,10,10,10);
			// jLabel.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));
			// or
			// label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

			statusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));
			statusbar.setPreferredSize(new Dimension(panel.getWidth(), 26));
			statusbar.setLayout(new BoxLayout(statusbar, BoxLayout.X_AXIS));
			statusLabel = new JLabel("status");
			statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
			statusbar.add(statusLabel);

			panel.add(statusbar, BorderLayout.SOUTH);
		}
		return statusbar;
	}

	public JLabel getStatusLabel() {
		if (statusbar == null) {
			getStatusBar();
		}
		return statusLabel;
	}

	@Override
	public JComponent getScrollPane() {
		return panel;
	}

	public void setCenter(JComponent c) {
		this.center = c;
		this.panel.add(c, BorderLayout.CENTER);
	}
	
	public JComponent getCenterComponent() {
		return center;
	}
	
	

}
