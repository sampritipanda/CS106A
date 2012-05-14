/*
 * File: FindRange.java
 * --------------------
 * This program is a stub for the FindRange problem, which finds the
 * smallest and largest values in a list of integers.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	private static final int SENTINEL = 0;
	private static int maxNum = SENTINEL;
	private static int minNum = SENTINEL;
	
	public void run() {
		intro();
		inputValues();
		printResults();
	}
	
	private void intro(){
		println("This program finds the smallest and largest integers in a list. Enter values, one per line, using a 0 to signal the end of the list.");
		
	}
	
	private void inputValues(){
		while(true){
			int input = readInt(" ? ");
			if(input == SENTINEL){
				break;
			}
			if(input > maxNum||maxNum == SENTINEL){
				maxNum = input;
			}
			if(input < minNum||minNum == SENTINEL){
				minNum = input;
			}
		}
	}
	
	private void printResults(){
		if(minNum == SENTINEL){
			println("No value was entered by the user");
		}
		else {
			println("The smallest value is " + minNum);
			println("The largest value is " + maxNum);
		}
	}
}
