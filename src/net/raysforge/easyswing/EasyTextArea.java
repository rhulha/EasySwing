package net.raysforge.easyswing;

import java.awt.event.KeyListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;

public class EasyTextArea implements Scrollable {

	JTextArea textArea = new JTextArea();
	JScrollPane scroller = new JScrollPane(getTextArea());

	@Override
	public JScrollPane getScrollPane() {
		return scroller;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void appendString(String string) {
		textArea.append(string);
	}

	public void setText(String t) {
		textArea.setText(t);
	}

	public String getText() {
		return textArea.getText();
	}

	public Document getDocument() {
		return textArea.getDocument();
	}

	public void addKeyListener(KeyListener kl) {
		textArea.addKeyListener(kl);
	}

	public void setCaretPosition(int i) {
		textArea.setCaretPosition(i);
	}
}
