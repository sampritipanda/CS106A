import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	public void run() {
		for (int i = 0; i < N_CIRCLES; i++) {
			animateCircle();
		}
	}

	private void animateCircle() {
		GOval circle = createRandomCircle();
		double radius = circle.getWidth() / 2;
		circle.setSize(0, 0);
		circle.move(radius, radius);
		add(circle);
		while (circle.getWidth() / 2 < radius) {
			growCircle(circle);
			pause(PAUSE_TIME);
		}
	}
	
	private GOval createRandomCircle() {
		double r = rgen.nextDouble(MIN_RADIUS, MAX_RADIUS);
		double x = rgen.nextDouble(0, getWidth() - 2 * r);
		double y = rgen.nextDouble(0, getHeight() - 2 * r);
		GOval circle = new GOval(x, y, 2 * r, 2 * r);
		circle.setFilled(true);
		circle.setColor(rgen.nextColor());
		return circle;
	}

	private void growCircle(GOval circle) {
		double radius = circle.getWidth() / 2 + DELTA_RADIUS;
		circle.setSize(2 * radius, 2 * radius);
		circle.move(-DELTA_RADIUS, -DELTA_RADIUS);
	}

	private RandomGenerator rgen = new RandomGenerator();

	private static final int N_CIRCLES = 10;
	private static final double MIN_RADIUS = 5;
	private static final double MAX_RADIUS = 150;
	private static final double DELTA_RADIUS = 1;
	private static final double PAUSE_TIME = 20;
}