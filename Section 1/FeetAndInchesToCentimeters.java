/*
 * File: FeetAndInchesToCentimeters.java
 * ---------------------
 * 
 */

import acm.program.*;

public class FeetAndInchesToCentimeters extends ConsoleProgram {
	public void run() {
		println("This program converts feet and inches to centimeters.");
		int feet = readInt("Enter number of feet: ");
		int inches = readInt("Enter number of inches: ");
		double totalInches = (feet * INCHES_PER_FOOT) + inches;
		double centimeters = totalInches * CENTIMETERS_PER_INCH;
		println(+ feet +"ft " + inches +"in = " + centimeters +"cm");
	}
	
	private static final int INCHES_PER_FOOT = 12;
	private static final double CENTIMETERS_PER_INCH = 2.54;
}

