import acm.program.*;

public class ScrabbleScoring extends ConsoleProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void run(){
		println("This program scores Scrabble points.");
		while(true){
			String word = readLine("Enter word: ");
			if(word.equals("")){
				break;
			}
			else {
				println("The word is worth " + getScore(word) + " Scrabble points.");
			}
		}
		println("Thanks for playing.");
	}
		
	private int getScore(String word){
		int score = 0;
		for(int i = 0; i < word.length(); i++){
			char ch = word.charAt(i);
			if(ch == 'A' || ch == 'E' || ch == 'I' || ch == 'L' || ch == 'N' || ch == 'O' || ch == 'R' || ch == 'S' || ch == 'T' || ch == 'U'){
				score += 1;
			}
			else if(ch == 'D' || ch == 'G'){
				score += 2;
			}
			else if(ch == 'B' || ch == 'C' || ch == 'M' || ch == 'P'){
				score += 3;
			}
			else if(ch == 'F' || ch == 'H' || ch == 'V' || ch == 'W' || ch == 'Y'){
				score += 4;
			}
			else if(ch == 'K'){
				score += 5;
			}
			else if(ch == 'J' || ch == 'X'){
				score += 8;
			}
			else if(ch == 'Q' || ch == 'Z'){
				score += 10;
			}
			else {
				score += 0;
			}
		}
		return score;
	}
}
