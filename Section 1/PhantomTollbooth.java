/*
 * File: Precedence.java
 * ---------------------
 * 
 */

import acm.program.*;

public class PhantomTollbooth extends ConsoleProgram {
	public void run() {
		int answer = 4 + 9 - 2 * 16 + 1 / 3 * 6 - 67 + 8 * 2 - 3 + 26 - 1 / 34 + 3 / 7 + 2 - 5;
		println("4 + 9 - 2 * 16 + 1 / 3 * 6 - 67 + 8 * 2 - 3 + 26 - 1 / 34 + 3 / 7 + 2 - 5 is " + answer + " according to Java's precedence rules.");
	}
}

