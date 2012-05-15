

/*
 * File: TurtleTokenizer.java
 * --------------------------
 * This file implements a simple tokenizer for the TurtleGraphics system.
 */

/**
 * This class divides up a command string into individual tokens.
 * A token consists of one of two forms:
 *
 * (1) A letter, optionally followed by any number of decimal digits,
 *     as in "F20", "R120", or "D", or
 * (2) A string beginning with "{" and continuing up to the matching "}".
 *
 * The tokenizer ignores all whitespace characters separating tokens.
 */

public class TurtleTokenizer {

/**
 * Creates a new TurtleTokenizer that takes its input from the string str.
 * @param str The string to be scanned
 */
	public TurtleTokenizer(String str) {
		str = removeAllOccurrences(str, ' ');
		str = toUpperCase(str);
		cmd = str;
	}

/**
 * Returns true if there are more tokens to read and false if the tokenizer
 * has reached the end of the input.
 * @return A boolean value indicating whether there are any unread tokens
 */
	public boolean hasMoreTokens() {
		if(currentPosition < (cmd.length())){
			return true;
		}
		else {
			return false;
		}
	}

/**
 * Returns the next complete token.  If this method is called at the end
 * of the input, the tokenizer returns the empty string.
 * @return The next token in the input
 */
	public String nextToken() {
		String token = "";
		int braceCounter = 0;
		int SENTINEL = 0; // Token to not add the next letter to the previous token.
		int x = 0; // Variable to indicate that there is a command statement.
		for(int i = currentPosition; i < (cmd.length()); i++){
			if(((cmd.charAt(i)) == 'F') || ((cmd.charAt(i)) == 'R') || ((cmd.charAt(i)) == 'L') || ((cmd.charAt(i)) == 'X') || ((cmd.charAt(i)) == 'D') || ((cmd.charAt(i)) == 'U') || ((cmd.charAt(i)) == 'M') || ((cmd.charAt(i)) == 'A') || ((cmd.charAt(i)) == 'H') || ((cmd.charAt(i)) == 'S')){
				if(braceCounter == 0){
					if(SENTINEL == -1){
						break;
					}
					SENTINEL--;
					token += cmd.charAt(i);
				}
				currentPosition++;
			}
			if(cmd.charAt(i) >= '0' && cmd.charAt(i) <= '9'){
				if(braceCounter == 0){
					token += cmd.charAt(i);
				}
				currentPosition++;
			}
			if((cmd.charAt(i) == '{')){
				if((y == 0) && (currentPosition != 0)){
					y++;
					return token;
				}
				braceCounter++;
				if(braceCounter == 1){
					start = i;
				}
				currentPosition++;
				x = 1;
			}
			if((cmd.charAt(i) == '}')){
				braceCounter--;
				currentPosition++;
				if(braceCounter == 0){
					end = i;
					break;
				}
			}
		}
		if(x == 1){
			token = "";
			for(int j = start; j <= end; j++){
				token += cmd.charAt(j);
			}
			y = 0;
		}
		return token;
	}
	
	private String removeAllOccurrences(String str, char ch){
		String result = "";
		for(int i = 0; i < str.length(); i++){
			char x = str.charAt(i);
			if(x != ch){
				result += x;
			}
		}
		return result;
	}
	
	private String toUpperCase(String str){
		String result = "";
		for(int i = 0; i < str.length(); i++){
			char ch = Character.toUpperCase(str.charAt(i));
			result += ch;
		}
		return result;
	}
	
	

	private String cmd;
	private int start = 0;
	private int end = 0;
	private int currentPosition = 0;
	private int y = 0; // Variable to return the token before the opening brace.

}
