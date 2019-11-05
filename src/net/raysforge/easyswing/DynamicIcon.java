/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.raysforge.easyswing;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.RenderingHints;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

/**
 *
 * @author Ray
 */
public class DynamicIcon implements ActionListener {

    private final JFrame frame;
    private String tip;
    private char iconChar;
    private final PopupMenu pm;
    private Image img;
	private TrayIcon ti;

    public DynamicIcon(JFrame frame, PopupMenu pm) {
        this.frame = frame;
        this.pm = pm;
    }

    public void setTip(String tip)
    {
        this.tip = tip;
    }

    public void setIconChar(char c)
    {
        this.iconChar = c;
    }

    public void setIconImage(Image img) {
        this.img = img;
    }

    public Image getDynamicIcon() {
        int w = 32;
        int h = 32;
        int pix[] = new int[w * h];
        int index = 0;
        for (int y = 0; y < h; y++) {
            int red = (y * 255) / (h - 1);
            for (int x = 0; x < w; x++) {
                int blue = (x * 255) / (w - 1);
                pix[index++] = (255 << 24) | (red << 16) | blue;
            }
        }
        Image imge = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(w, h, pix, 0, w));
        if( iconChar > 0)
        {
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g = (Graphics2D) bi.getGraphics();
            g.drawImage(imge, 0, 0, null);
            g.setColor(Color.RED);
            Font font = g.getFont();
            font = new Font(font.getName(), font.getStyle(), font.getSize()+2);
            g.setFont(font);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawString(""+iconChar, 3, 13);
            imge = bi;
        }
        return imge;
    }

    public TrayIcon addTryIcon() {
        try {
            ti = new TrayIcon(img == null ? getDynamicIcon() : img, tip, pm);
            if (frame != null) {
                ti.addActionListener(this);
            }
            SystemTray.getSystemTray().add(ti);
            return ti;
        } catch (AWTException ex) {
            Logger.getLogger(DynamicIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void remove()
    {
    	SystemTray.getSystemTray().remove(ti);
    }

    public void actionPerformed(ActionEvent e) {
        if (frame != null) {
            frame.setVisible(true);
            frame.setState(JFrame.NORMAL);
        }
    }
}
