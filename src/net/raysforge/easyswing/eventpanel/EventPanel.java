
package net.raysforge.easyswing.eventpanel;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * @author Ray
 */
@SuppressWarnings("serial")
public class EventPanel extends JPanel {
    
    ArrayList<PaintListener> paintListeners = new ArrayList<PaintListener>();
    boolean paintPanal = true;

    public EventPanel() {
    }


    public synchronized void addPaintListener(PaintListener listener) {
        paintListeners.add(listener);
    }

    public synchronized void removePaintListener(PaintListener listener) {
        paintListeners.remove(listener);
    }

    @Override
    public void paintComponent(Graphics g) {
    	if( paintPanal)
    	{
        	super.paintComponent(g);
    	}
        for (PaintListener paintListener : paintListeners) {
            paintListener.paint( new PaintEvent(g));
        }
    }
}
