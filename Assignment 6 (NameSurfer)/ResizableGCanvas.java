/*
 * File: ResizableGCanvas.java
 * ---------------------------
 * This file defines a GCanvas that triggers a call to update() whenever the
 * size of the canvas changes.  Your NameSurferGraph class extends this class
 * so that it responds correctly to changes in the window size.  You should
 * not need to make any changes to this class or call any of its methods
 * explicitly. 
 */

import acm.graphics.GCanvas;
import java.awt.event.*;

public class ResizableGCanvas extends GCanvas implements ComponentListener {

/**
 * Creates a new resizable GCanvas and adds the necessary listeners.
 */
	public ResizableGCanvas() {
		addComponentListener(this);
	}


/**
 * The update method is called automatically when the size of the
 * display changes.  Your NameSurferGraph subclass should override
 * the update method to recreate the NameSurfer display.
 */
	public void update() {
		/* Overridden by subclasses */
	}


/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }

}
