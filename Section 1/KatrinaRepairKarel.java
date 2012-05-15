/*
 * File: Katrina.java
 * ---------------------
 * 
 */

import acm.program.*;
import stanford.karel.*;

public class KatrinaRepairKarel extends SuperKarel {
	public void run() {
		while(frontIsClear()){
			moveToHouse();
			removeDebris();
			buildHouse();
			repositionForNewHouse();
		}
	}
	
	private void moveToHouse(){
		while(frontIsClear() && noBeepersPresent()){
			move();
		}
	}
	
	private void removeDebris(){
		pickBeeper();
		moveBackward();
	}
	
	private void buildHouse(){
		buildLeft();
		buildCenter();
		buildRight();
	}
	
	private void buildLeft(){
		turnLeft();
		for(int i = 0; i < 3; i++){
			putBeeper();
			move();
		}
	}
		
	private void buildCenter(){
		repositionForCenter();
		for(int i = 0; i < 3; i++){
			putBeeper();
			move();
		}
	}
	
	private void buildRight(){
		repositionForRight();
		for(int i = 0; i < 3; i++){
			putBeeper();
			move();
		}
	}
		
	private void repositionForCenter(){
		turnRight();
		move();
		turnRight();
	}
	
	private void repositionForRight(){
		turnLeft();
		move();
		turnLeft();
	}
	
	private void repositionForNewHouse(){
		turnAround();
		moveToWall();
		turnLeft();
		if(frontIsClear()){
			move();
		}
	}
	
	private void moveBackward(){
		turnAround();
		move();
		turnAround();
	}
	
	private void moveToWall(){
		while(frontIsClear()){
			move();
		}
	}
}

