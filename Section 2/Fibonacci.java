/*
 * File: Fibonacci.java
 * ---------------------
 * 
 */

import acm.program.*;

public class Fibonacci extends ConsoleProgram {
	public void run() {
		println("This program lists the Fibonacci sequence.");
		int x = 0;
		int y = 1;
		while(y <= MAX_NUM){
			println(x);
			println(y);
			x += y;
			y += x;
		}
		if(x <= MAX_NUM){
			println(x);
		}
	}
	
	private static final int MAX_NUM = 10000;
	
}

