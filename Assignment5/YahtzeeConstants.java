/*
 * File: YahtzeeConstants.java
 * ---------------------------
 * This file declares several constants that are shared by the
 * different modules in the Yahtzee game.
 */

public interface YahtzeeConstants {

/** The width of the application window */
	public static final int APPLICATION_WIDTH = 600;

/** The height of the application window */
	public static final int APPLICATION_HEIGHT = 350;

/** The number of dice in the game */
	public static final int N_DICE = 5;

/** The maximum number of players */
	public static final int MAX_PLAYERS = 4;

/** The total number of categories */
	public static final int N_CATEGORIES = 17;

/** The number of categories in which the player can score */
	public static final int N_SCORING_CATEGORIES = 13;

/** The constants that specify categories on the scoresheet */
	public static final int ONES = 0;
	public static final int TWOS = 1;
	public static final int THREES = 2;
	public static final int FOURS = 3;
	public static final int FIVES = 4;
	public static final int SIXES = 5;
	public static final int UPPER_SCORE = 6;
	public static final int UPPER_BONUS = 7;
	public static final int THREE_OF_A_KIND = 8;
	public static final int FOUR_OF_A_KIND = 9;
	public static final int FULL_HOUSE = 10;
	public static final int SMALL_STRAIGHT = 11;
	public static final int LARGE_STRAIGHT = 12;
	public static final int YAHTZEE = 13;
	public static final int CHANCE = 14;
	public static final int LOWER_SCORE = 15;
	public static final int TOTAL = 16;
}
