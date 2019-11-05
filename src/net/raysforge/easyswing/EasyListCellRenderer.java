package net.raysforge.easyswing;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class EasyListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 6574887255807141877L;

	HashMap<Integer, Color> colors = new HashMap<Integer, Color>();

	public void setBackgroundColor(int i, Color c) {
		colors.put(i, c);
	}

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (colors.containsKey(index))
			c.setBackground(colors.get(index));
		return c;
	}
}