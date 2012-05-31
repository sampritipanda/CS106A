/*
 * File: Breakout.java
 * -------------------
 * Name: Sampriti Panda
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/* Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/* Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/* Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/* Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/* Number of rects per i */
	private static final int NBRICKS_PER_ROW = 10;

/* Number of is of rects */
	private static final int NBRICK_ROWS = 10;

/* Separation between rects */
	private static final int BRICK_SEP = 4;

/* Width of a rect */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/* Height of a rect */
	private static final int BRICK_HEIGHT = 8;

/* Radius of the ball in pixels */
	private static final int BALL_RADIUS = 20;

/* Offset of the top rect i from the top */
	private static final int BRICK_Y_OFFSET = 70;

/* Number of turns */
	private static final int NTURNS = 3;

/* Maximum X Velocity */	
	private static final double MIN_X_VELOCITY = 1.0;
	
/* Minimum X Velocity */		
	private static final double MAX_X_VELOCITY = 3.0;
	
/* Y Velocity */
 	private static final double BALL_Y_VELOCITY = 4.0;
 	
/* Pause after each move of the ball */
 	private static final int DELAY = 10;
 	
/* Increase in Velocity after every hit on the paddle */
 	private static final double DELTA_VEL = 0.05;

/* Runs the Breakout program. */
	public void run() {
		setup();
		for(int i = 0; i < NTURNS; i++){
			playGame();
			if(brickCounter == 0){
				ball.setVisible(false);
				removeAll();
				printWinner();
				break;
			}
			else{
				remove(ball);
			}
		}
		if(brickCounter > 0){
			ball.setVisible(false);
			removeAll();
			printLoser();
		}
		endGame();
	}
	
	private void setup(){
		for (int i = 0; i < NBRICK_ROWS; i++) {
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				int y_offset = BRICK_Y_OFFSET + ((NBRICK_ROWS - i) * BRICK_HEIGHT) + ((NBRICK_ROWS - i) * BRICK_SEP);
				int x_offset = (j * BRICK_WIDTH) + (BRICK_SEP * j);
				rect = new GRect(x_offset, y_offset, BRICK_WIDTH, BRICK_HEIGHT);
				add(rect);
				
				rect.setFilled(true);
				if (i < 2) {
					rect.setColor(Color.CYAN);
				}
				if (i == 2 || i == 3) {
					rect.setColor(Color.GREEN);
				}
				if (i == 4 || i == 5) {
					rect.setColor(Color.YELLOW);
				}
				if (i == 6 || i == 7) {
					rect.setColor(Color.ORANGE);
				}
				if (i == 8 || i == 9) {
					rect.setColor(Color.RED);
				}
				pause(20);
			}
		}
		
		score = new GLabel("0");
		score.setFont("Times New Roman-36");
		score.setColor(Color.RED);
		add(score, (WIDTH - score.getWidth())/ 2, 40);
		scoreCounter = 0;
		
		paddle = new GRect((getWidth() - PADDLE_WIDTH)/2, HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
		
		ball = new GOval(BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
	}
	
	public void mouseMoved(MouseEvent e){
		last = new GPoint(paddle.getLocation());
		if((e.getX() + PADDLE_WIDTH/2) < getWidth() && (e.getX() > PADDLE_WIDTH/2)){ // Change this part to fit within window
			paddle.move((e.getX() - paddle.getWidth()/2) - last.getX(), 0);
		}
	}
	
	private void playGame(){
		GLabel start = new GLabel("Click to Serve");
		start.setFont("Calibri-36");
		start.setColor(Color.RED);
		add(start, (getWidth() - start.getWidth())/2, (HEIGHT - start.getAscent())/2);
		waitForClick();
		remove(start);
		add(ball, (getWidth() - ball.getWidth())/2, (HEIGHT - ball.getHeight())/2);
		vy = BALL_Y_VELOCITY;
		vx = rgen.nextDouble(MIN_X_VELOCITY, MAX_X_VELOCITY);
		if(rgen.nextBoolean(0.5)){
			vx = -vx;
		}
		while(true){
			int counter = 0;
			ball.move(vx, vy);
			if((ball.getX() < 0) || ((ball.getX() + BALL_RADIUS) > getWidth())){
				vx = -vx;
			}
			if(ball.getY() < 0){
				vy = -vy;
			}
			collideObj = getCollidingObject();
			if(collideObj == paddle){
				Math.abs(vy += DELTA_VEL);
				if(ball.getY() >= getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - (BALL_RADIUS * 2) && ball.getY() < getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - (BALL_RADIUS * 2) + 4){
					if(vx > 0){
						vx = -vx;
					}
					counter++;
				}
				if(ball.getX() <= paddle.getX() + PADDLE_WIDTH - 2 && ball.getX() >= paddle.getX() + PADDLE_WIDTH){
					if(vx < 0){
						vx = -vx;
					}
					counter++;
				}
				vy = -vy;
				bounceClip.play();
				if(counter == 0){
					vx = rgen.nextDouble(MIN_X_VELOCITY, MAX_X_VELOCITY);
					if(rgen.nextBoolean(0.5)){
						vx = -vx;
					}
				}
			}
			else if(collideObj != null && collideObj != score){
				remove(collideObj);
				brickCounter--;
				vy = -vy;
				bounceClip.play();
				scoreCounter++;
				score.setLabel("" + scoreCounter);
			}
			pause(DELAY);
			if((brickCounter == 0) || ball.getY() > (HEIGHT - BALL_RADIUS)){
				break;
			}
		}
	}
	
	private GObject getCollidingObject(){
		if(getElementAt(ball.getX(), ball.getY()) != null){
			return (getElementAt(ball.getX(), ball.getY()));
		}
		else if(getElementAt((ball.getX() + BALL_RADIUS * 2), ball.getY()) != null){
			return (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY()));
		}
		else if(getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2) != null){
			return (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2));
		}
		else if(getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2) != null){
			return (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2));
		}
		else {
			return null;
		}
	}
		
	private void printWinner(){
		GLabel winner= new GLabel("Winner");
		winner.setColor(Color.RED);
		winner.setFont("Times New Roman-46");
		add(winner, (getWidth() - winner.getWidth())/2, (HEIGHT - winner.getAscent())/2);
		pause(1000);
		remove(winner);
	}
	
	private void printLoser(){
		GLabel loser = new GLabel("Loser");
		loser.setColor(Color.RED);
		loser.setFont("Times New Roman-36");
		add(loser, (getWidth() - loser.getWidth())/2, (HEIGHT - loser.getAscent())/2);
		pause(1000);
		remove(loser);
	}
	
	private void endGame(){
		paddle.setVisible(false);
		GLabel gameOver = new GLabel("Game Over");
		gameOver.setColor(Color.RED);
		gameOver.setFont("Calibri-36");
		if(brickCounter > 0){
			add(gameOver, (getWidth() - gameOver.getWidth())/2, (HEIGHT - gameOver.getAscent())/2);
			pause(1000);
			removeAll();
		}
	}
	
	/* Main method */
	public static void main(String[] args) {
		new Breakout().start(args);
	}
	
	private int scoreCounter;
	private GLabel score;
	private GRect rect;
	private GRect paddle;
	private GOval ball;
	private int brickCounter = 100;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private GPoint last;
	private double vx, vy;
	private GObject collideObj;
	private AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
}
