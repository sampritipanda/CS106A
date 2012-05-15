/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run() {
		drawRow();
		while(leftIsClear()){
			repositionForRowToWest();
			drawRow();
			if(rightIsClear()){
				repositionForRowToEast();
				drawRow();
			}
			else {
				turnRight();
			}
		}
	}
	
	private void drawRow(){
		while(frontIsClear()){
			putBeeper();
			if(frontIsClear()){
				move();
				if(frontIsClear()){
					move();
				}
			}
		}
	}
	
	private void repositionForRowToWest(){
		turnLeft();
		move();
		turnLeft();
	}
	
	private void repositionForRowToEast(){
		turnRight();
		move();
		turnRight();
	}
}
