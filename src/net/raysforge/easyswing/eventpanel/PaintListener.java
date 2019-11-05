/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.raysforge.easyswing.eventpanel;

import java.util.EventListener;

/**
 *
 * @author Ray
 */
public interface PaintListener extends EventListener {

    // return true to skip super.paint call.
    public boolean paint(PaintEvent pe);

}
