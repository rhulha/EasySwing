package net.raysforge.easyswing;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

public class EasySplitPane implements Scrollable {
	
	JSplitPane splitPane;
	
	public EasySplitPane(boolean horizontalSplit, int dividerLocation) {
		splitPane = new JSplitPane( horizontalSplit ? JSplitPane.HORIZONTAL_SPLIT : JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation( dividerLocation);
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public Component setLeft(Component c) {
		splitPane.setLeftComponent(c);
		return c;
	}

	public Component setRight(Component c) {
		splitPane.setRightComponent(c);
		return c;
	}

	public Component setTop(Component c) {
		splitPane.setTopComponent(c);
		return c;
	}

	public Component setBottom(Component c) {
		splitPane.setBottomComponent(c);
		return c;
	}

	public Scrollable setLeft(Scrollable scrollable) {
		splitPane.setLeftComponent(scrollable.getScrollPane());
		return scrollable;
	}

	public Scrollable setRight(Scrollable scrollable) {
		splitPane.setRightComponent(scrollable.getScrollPane());
		return scrollable;
	}

	public Scrollable setTop(Scrollable scrollable) {
		splitPane.setTopComponent(scrollable.getScrollPane());
		return scrollable;
	}

	public Scrollable setBottom(Scrollable scrollable) {
		splitPane.setBottomComponent(scrollable.getScrollPane());
		return scrollable;
	}

	@Override
	public JComponent getScrollPane() {
		return splitPane;
	}
}
