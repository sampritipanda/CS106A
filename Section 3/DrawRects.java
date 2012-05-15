/*
 * File: DrawRects.java
 * --------------------
 * 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

public class DrawRects extends GraphicsProgram {

	private static final long serialVersionUID = 1L;

	public void init() {
		addMouseListeners();
	}

	public void mousePressed(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
		rect = new GRect(e.getX(), e.getY(), 0, 0);
		rect.setFilled(true);
		add(rect);
	}

	public void mouseDragged(MouseEvent e) {
		endX = e.getX();
		endY = e.getY();
		double x = Math.min(e.getX(), startX);
		double y = Math.min(e.getY(), startY);
		width = Math.abs(endX - startX);
		height = Math.abs(endY - startY);
		rect.setBounds(x, y, width, height);
	}

	private GRect rect;
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	private double width;
	private double height;

}
