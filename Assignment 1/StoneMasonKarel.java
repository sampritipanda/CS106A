/*
 * File: StoneMasonKarel.java
 * --------------------------
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	public void run(){
		while(frontIsClear()){
			repairColumnFromDown();
			repositionForColumnFromTop();
			repairColumnFromTop();
			repositionForColumnFromDown();
		}
	}


	private void repairColumnFromDown(){
		turnLeft();
		while(frontIsClear()){
			if(noBeepersPresent()){
				putBeeper();
			}
			move();
		}
		OBOB();
	}

	private void repairColumnFromTop(){
		turnRight();
		while(frontIsClear()){
			if(noBeepersPresent()){
				putBeeper();
			}
			move();
		}
	}
	
	private void repositionForColumnFromTop(){
		turnRight();
		for(int i = 0; i < 4; i++){
			move();
		}
		if(leftIsClear()){
			repairColumnFromDown();
			turnRight();
		}
	}
	
	private void repositionForColumnFromDown(){
		turnLeft();
		if(frontIsClear()){
			for(int i = 0; i < 4; i++){
				move();
			}
		}
		if(rightIsClear()){
			repairColumnFromDown();
			turnLeft();
			repairColumnFromTop();
		}
	}
	
	private void OBOB(){
		if(noBeepersPresent()){
			putBeeper();
		}
	}
	
	private void moveToWall(){
		while(frontIsClear()){
			move();
		}
	}
}		