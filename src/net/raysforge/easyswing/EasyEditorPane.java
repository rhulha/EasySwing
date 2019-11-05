package net.raysforge.easyswing;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class EasyEditorPane implements HyperlinkListener, Scrollable {
	
	JEditorPane editorPane = new JEditorPane();
	JScrollPane scroller = new JScrollPane(editorPane);
	
	public EasyEditorPane() {
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		editorPane.addHyperlinkListener(this);
	}
	
	public JEditorPane getEditorPane() {
		return editorPane;
	}

	public JScrollPane getScrollPane() {
		return scroller;
	}

	public void setHTML(String s) {
		editorPane.setText(s);
		
	}

	public void setHTML(File file) {
		try {
			InputStream stream = new FileInputStream(file);
			byte buf[] = new byte[(int)file.length()];
			stream.read(buf);
			stream.close();
			String string = new String(buf, Charset.forName("utf-8"));
			string = string.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
			editorPane.setText(string);
			editorPane.setCaretPosition(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == 
            HyperlinkEvent.EventType.ACTIVATED) {
			URL url = e.getURL();
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(url.toString()), null);
		}

		
	}

	public Document getDocument() {
		return editorPane.getDocument();
	}

	public void clear() {
		try {
			getDocument().remove(0, getDocument().getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	public void insertString(String string) {
		try {
			getDocument().insertString(0, string, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

}
