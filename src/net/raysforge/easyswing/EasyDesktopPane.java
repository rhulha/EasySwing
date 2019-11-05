package net.raysforge.easyswing;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;

public class EasyDesktopPane {

	JDesktopPane dp = new JDesktopPane();

	public EasyDesktopPane() {
		Color color = UIManager.getColor("Panel.background");
		dp.setBackground(color);
	}

	public EasyInternalFrame addInternalFrame(String name, int w, int h) {
		EasyInternalFrame eif = new EasyInternalFrame(name, w, h);
		dp.add(eif.getInternalFrame());
		return eif;
	}

	public Component getDesktopPane() {
		return dp;
	}

	public void setBackground(Color background) {
		dp.setBackground(background);
	}

	public EasyInternalFrame getActiveFrame() {
		JInternalFrame selectedFrame = dp.getSelectedFrame();
		return (EasyInternalFrame) selectedFrame.getClientProperty("easyWrapper");
		
	}

}
