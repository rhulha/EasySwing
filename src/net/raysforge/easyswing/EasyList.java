package net.raysforge.easyswing;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

public class EasyList<T> implements Scrollable {

	DefaultListModel<T> listModel = new DefaultListModel<T>();
	JList<T> list = new JList<T>(listModel);
	JScrollPane scroller = new JScrollPane(list);
	EasyListCellRenderer elcr = new EasyListCellRenderer();

	public EasyList() {
		list.setCellRenderer(elcr);
		list.putClientProperty("easyWrapper", this);
	}

	public EasyList(ListSelectionListener listener) {
		list.setCellRenderer(elcr);
		list.addListSelectionListener(listener);
		list.putClientProperty("easyWrapper", this);
	}

	public void setBackgroundColor(int i, Color c) {
		elcr.setBackgroundColor(i, c);
	}

	@Override
	public JScrollPane getScrollPane() {
		return scroller;
	}

	public JList<T> getJList() {
		return list;

	}

	public DefaultListModel<T> getListModel() {
		return listModel;
	}

	public void setFontSize(float size) {
		list.setFont(list.getFont().deriveFont(size));
	}

	public void addElement(T obj) {
		listModel.addElement(obj);
	}

	public void addListSelectionListener(ListSelectionListener listener) {
		list.addListSelectionListener(listener);
	}

	public void remove(int i) {
		listModel.remove(i);
	}

	public void removeAll() {
		listModel.removeAllElements();
	}

	public T get(int i) {
		return listModel.get(i);
	}

	public void set(int index, T obj) {
		listModel.set(index, obj);
	}

	public int getSize() {
		return listModel.getSize();
	}

	public void repaint() {
		list.repaint();
	}

	public int getSelectedIndex() {
		return list.getSelectedIndex();
	}

	public T getSelectedValue() {
		return list.getSelectedValue();
	}

	public void addActionListener(final ActionListener al) {

		getJList().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					al.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "ENTER"));
				}
			}
		});

		getJList().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					al.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "ENTER"));
				}
			}
		});

	}

}
