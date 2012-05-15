/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	public void run(){
		faceEast();
		moveToNewspaper();
		pickUpNewspaper();
		goToHome();
	}
	
	private void faceEast(){
		if(facingWest()){
			turnAround();
		}
		if(facingNorth()){
			turnRight();
		}
		if(facingWest()){
			turnLeft();
		}
	}
	
	private void moveToNewspaper(){
		moveToWall();
		turnRight();
		move();
		turnLeft();
		move();
	}
	
	private void pickUpNewspaper(){
		pickBeeper();
	}
	
	private void goToHome(){
		turnAround();
		move();
		turnRight();
		move();
		turnLeft();
		moveToWall();
		faceEast();
	}
	
	private void moveToWall(){
		while(frontIsClear()){
			move();
		}
	}
}
