/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/**
 * Creates a new NameSurferEntry from a data line as it appears in the
 * data file.  Each line begins with the name, which is followed by
 * integers giving the rank of that name for each decade.
 */
	public NameSurferEntry(String line) {
		for(int i = 0; i < line.length(); i++){
			char ch = line.charAt(i);
			if(Character.isLetter(ch)){
				name += ch;
			}
			if(ch == ' '){
				break;
			}
		}
		
		int rankStart = line.indexOf(" ") + 1;
		String numbers = line.substring(rankStart);
		StringTokenizer tokenizer = new StringTokenizer(numbers);
		for(int i = 0; tokenizer.hasMoreTokens(); i++){
			rank[i] =  Integer.parseInt(tokenizer.nextToken());
		}
	}

/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/**
 * Returns the rank associated with an entry for a particular decade.
 * The decade value is an integer indicating how many decades have passed
 * since the first year in the database, which is given by the constant
 * START_DECADE.  If a name has a rank below 1000, the rank value is 0.
 */
	public int getRank(int decade) {
		return rank[decade];
	}

/**
 * Returns a string that makes it easy to see the value of a NameSurferEntry.
 */
	public String toString() {
		String result = "";
		result = result + name + " [";
		for(int i = 0; i < NDECADES; i++){
			result = result + rank[i];
			if(i < NDECADES - 1){
				result += " ";
			}
		}
		result += "]";
		return result;
	}
	
/** Private instance variables */
	private String name = "";
	private int[] rank = new int[NDECADES];
}
