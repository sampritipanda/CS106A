/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game from Assignment #5.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
/*
 * The run method uses the IODialog class from the acm.io package to initialize
 * the game.  It first allows the user to choose the number of players and then
 * reads in the name of each player.  Once the initialization is complete, the
 * run method initializes the Yahtzee display and calls the playGame method.
 */
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players", 1, MAX_PLAYERS);
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
		ui = new YahtzeeUI(playerNames);
		playGame();
	}

/*
 * This method plays a single game of Yahtzee.
 */
	private void playGame() {
		for(int i = 0; i < N_SCORING_CATEGORIES; i++){
			for(int j = 0; j < nPlayers; j++){
				rollDice(j);
				selectCategory(j);
				calculateRoundScores(j);
			}
		}
		calculateTotalScores();
		findWinner();
	}
	
	private void rollDice(int j){
		ui.printMessage(playerNames[j] + "'s turn!, Click the \"Roll Dice\" button to roll the dice.");
		ui.waitForPlayerToClickRoll(j);
		dice = new int[N_DICE];
		for(int k = 0; k < N_DICE; k++){
			dice[k] = rgen.nextInt(1, 6);
		}
		ui.displayDice(dice);
		for(int k = 0; k < 2; k++){
			ui.printMessage("Select the dice you wish to re-roll and click \"Roll Again\"");
			ui.waitForPlayerToSelectDice();
			for(int l = 0; l < N_DICE; l++){
				if(ui.isDieSelected(l)){
					dice[l] = rgen.nextInt(1, 6);
				}
			}
			ui.displayDice(dice);
		}
	}
	
	private void selectCategory(int j){
		ui.printMessage("Select a category for this roll");
		category = ui.waitForPlayerToSelectCategory();
		while(true){
			if(categoryOccupied[j][category] == 1){
				ui.printMessage("You already picked that category. Please choose another category");
				category = ui.waitForPlayerToSelectCategory();
			}
			else {
				break;
			}
		}
		categoryOccupied[j][category] = 1;
	}
	
	private void calculateRoundScores(int j){
		if(checkCategory(dice, category)){
			setCategoryScore(j, category);
		}
		else {
			scores[j][category] = 0;
		}
		ui.updateScorecard(category, j, scores[j][category]);
		scores[j][TOTAL] += scores[j][category];
		ui.updateScorecard(TOTAL, j, scores[j][TOTAL]);
	}
	
	private void setCategoryScore(int player, int category){
		int score = 0;
		if(category >= ONES && category <= SIXES){
			for(int i = 0; i < N_DICE; i++){
				if(dice[i] == category + 1){
					score += dice[i];
				}
			}
		}
		else if(category == THREE_OF_A_KIND){
			for(int i = 0; i < N_DICE; i++){
				score += dice[i];
			}
		}
		else if(category == FOUR_OF_A_KIND){
			for(int i = 0; i < N_DICE; i++){
				score += dice[i];
			}
		}
		else if(category == FULL_HOUSE){
			score += 25;
		}
		else if(category == SMALL_STRAIGHT){
			score += 30;
		}
		else if(category == LARGE_STRAIGHT){
			score += 40;
		}
		else if(category == YAHTZEE){
			score += 50;
		}
		else if(category == CHANCE){
			for(int i = 0; i < N_DICE; i++){
				score += dice[i];
			}
		}
		else {
			score += 0;
		}
		scores[player][category] = score;
	}
	
	private void calculateTotalScores(){
		totalScores = new int[nPlayers];
		for(int i = 0; i < nPlayers; i++){
			scores[i][UPPER_SCORE] = scores[i][ONES] + scores[i][TWOS] + scores[i][THREES] + scores[i][FOURS] + scores[i][FIVES] + scores[i][SIXES];
			ui.updateScorecard(UPPER_SCORE, i, scores[i][UPPER_SCORE]);
			if(scores[i][UPPER_SCORE] >= 63){
				scores[i][UPPER_BONUS] = 35;
				scores[i][TOTAL] += scores[i][UPPER_BONUS];
				ui.updateScorecard(UPPER_BONUS, i, scores[i][UPPER_BONUS]);
				ui.updateScorecard(TOTAL, i, scores[i][TOTAL]);
			}
			else {
				scores[i][UPPER_BONUS] = 0;
				scores[i][TOTAL] += scores[i][UPPER_BONUS];
				ui.updateScorecard(UPPER_BONUS, i, scores[i][UPPER_BONUS]);
				ui.updateScorecard(TOTAL, i, scores[i][TOTAL]);
			}
			scores[i][LOWER_SCORE] = scores[i][THREE_OF_A_KIND] + scores[i][FOUR_OF_A_KIND] + scores[i][FULL_HOUSE] + scores[i][SMALL_STRAIGHT] + scores[i][LARGE_STRAIGHT] + scores[i][YAHTZEE] + scores[i][CHANCE];
			ui.updateScorecard(LOWER_SCORE, i, scores[i][LOWER_SCORE]);
			totalScores[i] = (scores[i][TOTAL]);
		}
	}
	
	private void findWinner(){
		int winner = findLargestScorer(totalScores);
		ui.printMessage("Congratulations, " + playerNames[winner] + ", you're the winner with a total score of " + totalScores[winner] + "!");
	}
		
	private int findLargestScorer(int[] totalScores){
		int winner = 0;
		int maxScore = 0;
		for(int i = 0; i < totalScores.length; i++){
			if(totalScores[i] > maxScore){
				maxScore = totalScores[i];
				winner = i;
			}
		}
		return winner;
	}
	
	private boolean checkCategory(int[] dice, int category){
		boolean categoryCheck = false;
		
		ArrayList<Integer>ones = new ArrayList<Integer>();
		ArrayList<Integer>twos = new ArrayList<Integer>();
		ArrayList<Integer>threes = new ArrayList<Integer>();
		ArrayList<Integer>fours = new ArrayList<Integer>();
		ArrayList<Integer>fives = new ArrayList<Integer>();
		ArrayList<Integer>sixes = new ArrayList<Integer>();
		
		for(int i = 0; i < N_DICE; i++){
			if(dice[i] == 1){
				ones.add(1);
			}
			else if(dice[i] == 2){
				twos.add(1);
			}
			else if(dice[i] == 3){
				threes.add(1);
			}
			else if(dice[i] == 4){
				fours.add(1);
			}
			else if(dice[i] == 5){
				fives.add(1);
			}
			else if(dice[i] == 6){
				sixes.add(1);
			}
		}
		
		if(category >= ONES && category <= SIXES){
			categoryCheck = true;
		}
		else if(category == THREE_OF_A_KIND){
			if(ones.size() >= 3 || twos.size() >= 3 || threes.size() >= 3 || fours.size() >= 3 || fives.size() >= 3 || sixes.size() >= 3){
				categoryCheck = true;
			}
		}
		else if(category == FOUR_OF_A_KIND){
			if(ones.size() >= 4 || twos.size() >= 4 || threes.size() >= 4 || fours.size() >= 4 || fives.size() >= 4 || sixes.size() >= 4){
				categoryCheck = true;
			}
		}
		else if(category == FULL_HOUSE){
			if(ones.size() == 3 || twos.size() == 3 || threes.size() == 3 || fours.size() == 3 || fives.size() == 3 || sixes.size() == 3){
				if(ones.size() == 2 || twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2 || sixes.size() == 2){
					categoryCheck = true;
				}
			}
		}
		else if(category == SMALL_STRAIGHT){
			if(ones.size() == 1 && twos.size() == 1 && threes.size() == 1 && fours.size() == 1){
				categoryCheck = true;
			}
			else if(twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1){
				categoryCheck = true;
			}
			else if(threes.size() == 1 && fours.size() == 1 && fives.size() == 1 && sixes.size() == 1){
				categoryCheck = true;
			}
		}
		else if(category == LARGE_STRAIGHT){
			if(ones.size() == 1 && twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1){
				categoryCheck = true;
			}
			else if(twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1 && sixes.size() == 1){
				categoryCheck = true;
			}
		}
		else if(category == YAHTZEE){
			if(ones.size() == 5 || twos.size() == 5 || threes.size() == 5 || fours.size() == 5 || fives.size() == 5 || sixes.size() == 5){
				categoryCheck = true;
			}
		}
		else if(category == CHANCE){
			categoryCheck = true;
		}
		return categoryCheck;
	}
	

/* Set the window dimensions */
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 500;

/* Private instance variables */
	private int[] totalScores;
	private int dice[];
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeUI ui;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int [][] scores = new int[nPlayers + 2][N_CATEGORIES + 2];
	private int[][] categoryOccupied = new int[nPlayers + 2][N_CATEGORIES + 2];
	private int category;
}
