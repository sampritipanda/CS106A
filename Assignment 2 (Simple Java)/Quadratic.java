/*
 * File: Quadratic.java
 * --------------------
 * This program is a stub for the Quadratic problem, which finds the
 * roots of the quadratic equation.
 */

import acm.program.*;

public class Quadratic extends ConsoleProgram {

	public void run() {
		println("Enter coefficients for the quadratic equation:");
		double a = readDouble("a: ");
		double b = readDouble("b: ");
		double c = readDouble("c: ");
		double first = 0;  // The first solution(+)
		double second = 0; // The second solution(-)
		first = (-b + Math.sqrt((b * b) - (4 * a * c)))/(2 * a);
		second = (-b - Math.sqrt((b * b) - (4 * a * c)))/(2 * a);
		println("The first solution is " + first + ".");
		println("The second solution is " + second + ".");
	}
}

