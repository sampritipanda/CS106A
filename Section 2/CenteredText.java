/*
 * File: CenteredText.java
 * ---------------------
 * 
 */

import acm.program.*;
import acm.graphics.*;

public class CenteredText extends GraphicsProgram {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void run() {
		GLabel label = new GLabel("CS106A rocks my socks!");
		label.setFont("SansSerif-28");
		double x = (getWidth() - label.getWidth())/2;
		double y = (getHeight() + label.getAscent())/2;
		label.setLocation(x, y);
		add(label);
	}
}

