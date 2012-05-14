/*
 * File: Rainbow.java
 * ------------------
 * This program is a stub for the Rainbow problem, which displays
 * a rainbow by adding consecutively smaller circles to the canvas.
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.Color;

public class Rainbow extends GraphicsProgram {

	public void run() {
		double xOval = -getWidth()/2;
	    double yOval = getHeight()/15;
	    double heightOval = getHeight()*2;
	    double widthOval = getWidth()*2;
		
	    Color colours[] = new Color[7];
	    colours[0] = Color.RED;
		colours[1] = Color.ORANGE;
		colours[2] = Color.YELLOW;
		colours[3] = Color.GREEN;
		colours[4] = Color.BLUE;
		colours[5] = Color.PINK;
		colours[6] = Color.CYAN;
		
		GRect background = new GRect(0, 0, getWidth(), getHeight());
		background.setFilled(true);
		background.setFillColor((colours[6]));
		add(background);
		
		for(int i = 0; i < 7; i++){
			GOval newCircle = new GOval(xOval, i * yOval + yOval, widthOval, heightOval + i * yOval);
			add(newCircle);
	        newCircle.setFilled(true);
	        newCircle.setColor(colours[i]);
		}
	}
}
