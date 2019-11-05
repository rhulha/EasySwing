package net.raysforge.easyswing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class ColorIcon implements Icon {
	
	private final Color c;
	private final int width;
	private final int height;

	public ColorIcon(Color c, int width, int height) {
		this.c = c;
		this.width = width;
		this.height = height;
	}

	@Override
	public int getIconHeight() {
		return height;
	}

	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public void paintIcon(Component comp, Graphics g, int x, int y) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
		
	}

}
