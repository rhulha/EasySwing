package net.raysforge.easyswing;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 * @author Smoochy
 */
public class GlobalEventListener implements AWTEventListener {

    private LinkedList<EventListener> mouseListeners;
    private LinkedList<EventListener> mouseMotionListeners;
    private LinkedList<EventListener> mouseWheelListeners;
    private LinkedList<EventListener> keyListeners;
    
    public void addMouseListener(MouseListener ml) {
        mouseListeners.add(ml);  
    }
    
    public void addMouseMotionListener(MouseMotionListener mml) {
        mouseMotionListeners.add(mml);
    }
    
    public void addMouseWheelListener(MouseWheelListener mwl) {
        mouseWheelListeners.add(mwl);
    }
    
    public void addKeyListener(KeyListener kl) {
        keyListeners.add(kl);
    }
    
    public void removeMouseListener(MouseListener ml) {
        mouseListeners.remove(ml);  
    }
    
    public void removeMouseMotionListener(MouseMotionListener mml) {
        mouseMotionListeners.remove(mml);
    }
    
    public void removeMouseWheelListener(MouseWheelListener mwl) {
        mouseWheelListeners.remove(mwl);
    }
    
    public void removeKeyListener(KeyListener kl) {
        keyListeners.remove(kl);
    }
    
    
    GlobalEventListener() {
        Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_WHEEL_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
        mouseListeners = new LinkedList<EventListener>();
        mouseMotionListeners = new LinkedList<EventListener>();
        mouseWheelListeners = new LinkedList<EventListener>();
        keyListeners = new LinkedList<EventListener>();
    }

    public void eventDispatched(AWTEvent event) {
        ListIterator<EventListener> li;
        
        if(event instanceof MouseWheelEvent) {
            // WHEEL
            MouseWheelEvent mwe = (MouseWheelEvent) event;
            li = mouseWheelListeners.listIterator();
            MouseWheelListener mwl;
            while(li.hasNext()) {
                mwl = (MouseWheelListener)li.next();
                mwl.mouseWheelMoved(mwe);
            }
            
        } else if(event instanceof MouseEvent) {
            
            MouseEvent me = (MouseEvent) event;
            // MOTION
            if(me.getID() == MouseEvent.MOUSE_DRAGGED || me.getID() ==  MouseEvent.MOUSE_MOVED) { // MouseMotionListener!
                MouseMotionListener mml;
                li = mouseMotionListeners.listIterator();
                switch(me.getID()) {
                	case MouseEvent.MOUSE_DRAGGED:
                        while(li.hasNext()) {
                            mml = (MouseMotionListener) li.next();
                            mml.mouseDragged(me);
                        }
                	    break;
                	case MouseEvent.MOUSE_MOVED:
                        while(li.hasNext()) {
                            mml = (MouseMotionListener) li.next();
                            mml.mouseMoved(me);
                        }
                	    break;
                }
                //MOUSENORMAL
            } else {
                MouseListener ml;
                li = mouseListeners.listIterator();
                switch(me.getID()) {
                	case MouseEvent.MOUSE_CLICKED:
                	    while(li.hasNext()) {
                	        ml = (MouseListener)li.next();
                	        ml.mouseClicked(me);
                	    }
                	    break;
                	case MouseEvent.MOUSE_ENTERED:
                	    while(li.hasNext()) {
                	        ml = (MouseListener)li.next();
                	        ml.mouseEntered(me);
                	    }
                	    break;
                	case MouseEvent.MOUSE_EXITED:
                	    while(li.hasNext()) {
                	        ml = (MouseListener)li.next();
                	        ml.mouseExited(me);
                	    }
                	    break;
                	case MouseEvent.MOUSE_PRESSED:
                	    while(li.hasNext()) {
                	        ml = (MouseListener)li.next();
                	        ml.mousePressed(me);
                	    }
                	    break;
                	case MouseEvent.MOUSE_RELEASED:
                	    while(li.hasNext()) {
                	        ml = (MouseListener)li.next();
                	        ml.mouseReleased(me);
                	    }
                	    break;
                }
                
            }
            // KEY
        } else if(event instanceof KeyEvent) {
            KeyListener kl;
            KeyEvent ke = (KeyEvent) event;
            li = keyListeners.listIterator();
            switch(ke.getID()) {
            	case KeyEvent.KEY_PRESSED:
                    while(li.hasNext()) {
                        kl = (KeyListener)li.next();
                        kl.keyPressed(ke);
                    }
            	    break;
            	case KeyEvent.KEY_RELEASED:
                    while(li.hasNext()) {
                        kl = (KeyListener)li.next();
                        kl.keyReleased(ke);
                    }
            	    break;
            	case KeyEvent.KEY_TYPED:
                    while(li.hasNext()) {
                        kl = (KeyListener)li.next();
                        kl.keyTyped(ke);
                    }
            	    break;
            }
            
        }
    }
    
}
