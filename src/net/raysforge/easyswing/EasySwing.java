package net.raysforge.easyswing;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import net.raysforge.easyswing.eventpanel.EventPanel;

public class EasySwing {

	JFrame frame = new JFrame();
	JToolBar toolbar = null;
	JPanel statusbar;
	JLabel statusLabel;

	public EasySwing(String name) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		init(name, (int)(screenSize.width*0.75), (int)(screenSize.height*0.75), JFrame.DISPOSE_ON_CLOSE);
	}

	public EasySwing(String name, int width, int height) {
		init(name, width, height, JFrame.DISPOSE_ON_CLOSE);
	}

	public EasySwing(String name, int width, int height, int defaultCloseOperation) {
		init(name, width, height, defaultCloseOperation);
	}
	
	private void init(String name, int width, int height, int defaultCloseOperation) {
		frame.setTitle(name);
		frame.setDefaultCloseOperation(defaultCloseOperation);
		frame.setSize(new Dimension(width, height));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			System.out.println(ex);
			Logger.getLogger(EasySwing.class.getName()).log(Level.SEVERE, null, ex);
		}

		frame.setLocationRelativeTo(null);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void show() {
		frame.setVisible(true);
	}

	public JMenu addMenuItem(String name) {
		if (frame.getJMenuBar() == null) {
			frame.setJMenuBar(new JMenuBar());
		}
		return frame.getJMenuBar().add(new JMenu(name));
	}

	public void addMenuItem(JMenu parent, String text, String actionCommand, ActionListener al) {
		JMenuItem mi = new JMenuItem(text);
		mi.setActionCommand(actionCommand);
		mi.addActionListener(al);
		parent.add(mi);
	}

	public void addMenuItem(JPopupMenu parent, String text, String actionCommand, ActionListener al) {
		JMenuItem mi = new JMenuItem(text);
		mi.setActionCommand(actionCommand);
		mi.addActionListener(al);
		parent.add(mi);
	}
	
	public void addButtonsForEveryIcon() {
		// just for demo purposes
		Set<Entry<Object, Object>> entrySet = UIManager.getDefaults().entrySet();
		for (Entry<Object, Object> e : entrySet) {
			if (e.getKey().toString().endsWith("Icon")) {
				System.out.println(e.getKey());
				Icon fileIcon = UIManager.getIcon(e.getKey());
				JButton addToolBarItem = addToolBarItem(fileIcon, e.getKey().toString(), null);
				addToolBarItem.setToolTipText(e.getKey().toString());
			}
		}
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

	public JButton addToolBarItem(JButton b, String actionCommand, ActionListener al) {
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
			Container contentPane = frame.getContentPane();
			contentPane.add(toolbar, BorderLayout.NORTH);
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
			statusbar.setPreferredSize(new Dimension(frame.getWidth(), 26));
			statusbar.setLayout(new BoxLayout(statusbar, BoxLayout.X_AXIS));
			statusLabel = new JLabel("status");
			statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
			statusbar.add(statusLabel);

			Container contentPane = frame.getContentPane();
			contentPane.add(statusbar, BorderLayout.SOUTH);
		}
		return statusbar;
	}

	public JLabel getStatusLabel() {
		if (statusbar == null) {
			getStatusBar();
		}
		return statusLabel;
	}

	public void add(Scrollable s) {
		frame.getContentPane().add(s.getScrollPane(), BorderLayout.CENTER);
	}

	public EasyTable setTableAsMainContent() {
		EasyTable et = new EasyTable();
		frame.getContentPane().add(et.getScrollPane(), BorderLayout.CENTER);
		return et;
	}

	public <T> EasyList<T> setListAsMainContent(Class<T> clazz) {
		EasyList<T> el = new EasyList<T>();
		frame.getContentPane().add(el.getScrollPane(), BorderLayout.CENTER);
		return el;
	}

	public EasySplitPane setSplitPaneAsMainContent(boolean horizontalSplit, int dividerLocation) {
		EasySplitPane esp = new EasySplitPane(horizontalSplit, dividerLocation);
		frame.getContentPane().add(esp.getSplitPane(), BorderLayout.CENTER);
		return esp;
	}
	
	public EventPanel setEventPanelAsMainContent() {
		EventPanel ep = new EventPanel();
		frame.getContentPane().add(ep, BorderLayout.CENTER);
		return ep;
	}

	public EasyDesktopPane setDesktopPaneAsMainContent() {
		EasyDesktopPane edp = new EasyDesktopPane();
		frame.getContentPane().add(edp.getDesktopPane(), BorderLayout.CENTER);
		return edp;
	}

	public Container getContentPane() {
		/*
		 * KeyEvents for JPanel ? addKeyListener(this); setFocusable(true);
		 * requestFocus();
		 */
		return frame.getContentPane();
	}

	public void addEasyFileDropTarget(Component c, FileDropListener fdl) {
		EasyFileDropTarget easyFileDropTarget = new EasyFileDropTarget(c);
		easyFileDropTarget.addFileDropListener(fdl);
	}

	JFileChooser jFileChooser = new JFileChooser();

	public File getFileFromFileOpenDialog() {
		if (jFileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			return jFileChooser.getSelectedFile();
		} else {
			return null;
		}
	}

	public File getFileFromFileSaveDialog(String... filename) {

		if (filename.length > 0)
			jFileChooser.setSelectedFile(new File(filename[0]));

		if (jFileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			return jFileChooser.getSelectedFile();
		} else {
			return null;
		}
	}

	public void addGlobalKeyEventListener(AWTEventListener eventListener) {
		Toolkit.getDefaultToolkit().addAWTEventListener(eventListener, AWTEvent.KEY_EVENT_MASK);
	}

	public JFileChooser getFileChooser() {
		// fileChooser.setSelectedFile(new File("file.myfile"));
		// fileChooser.setFileFilter(new FileNameExtensionFilter("MyFiles",
		// "my file"));
		return jFileChooser;
	}

	public void addWindowListener(WindowListener wl) {
		frame.addWindowListener(wl);
	}

	public int getWidth() {
		return frame.getWidth();
	}

	public int getHeight() {
		return frame.getHeight();
	}

	public BufferedImage getScreenShot() {
		return getScreenShot(frame.getContentPane());
	}
	public static BufferedImage getScreenShot(Component component) {
		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
		// call the Component's paint method, using
		// the Graphics object of the image.
		component.paint(image.getGraphics()); // alternately use .printAll(..)
		return image;
	}

	public void revalidate() {
		frame.revalidate();
	}

	public JMenuBar getMenuBar() {
		if (frame.getJMenuBar() == null) {
			frame.setJMenuBar(new JMenuBar());
		}
		return frame.getJMenuBar();
	}

    public void showAlert(String string) {
        JOptionPane.showMessageDialog(frame, string);
    }

}
