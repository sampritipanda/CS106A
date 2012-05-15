/*
 * File: Hailstone.java
 * --------------------
 * This program is a stub for the Hailstone problem, which computes
 * Hailstone sequence described in Assignment #2.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {

	public void run() {
		println("This program computes Hailstone sequences.");
		int hailstoneNumber = readInt("Enter a number: ");
		int result = hailstoneNumber;
		int count = 0;
		while(result != 1){
			if(numberIsEven(result)){
				println(result + " is even, so I take half: " + (result/2));
				result /= 2;
			}
			else{
				println(result + " is odd, so I make 3n+1: " + ((result * 3) + 1));
				result = (result * 3) + 1;
			}
			count++;
		}
		println("The process took " + count + " steps to reach 1.");
	}
	
	private boolean numberIsEven(int n){
		return ((n % 2) == 0);
	}
}
