import java.util.StringTokenizer;

import acm.program.*;

public class StanfordSpeak extends ConsoleProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void run() {
		while(true) {
			String line = readLine("Enter line to \"Stanfordize\": ");
			if(line.equals("")){
				break;
			}
			else {
				println("In stanford, we say: " + createStanfordAbbreviation(line));
			}
		}
		println("Thanks for visiting Stanford.");
	}
	
	private String createStanfordAbbreviation(String str){
		String phrase = "";
		StringTokenizer tokenizer = new StringTokenizer(str);
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			phrase += abbreviateWord(token);
		}
		return phrase;
	}
	
	private String abbreviateWord(String str){
		String result = "";
		int vowelIndex = findFirstVowel(str);
		if(vowelIndex == -1){
			return str;
		}
		else {
			result = str.substring(0, vowelIndex + 1);
			if(isSonorant(str.charAt(vowelIndex + 1))){
				result += str.charAt(vowelIndex + 1);
			}
		}
		return result;
	}
	
	private int findFirstVowel(String str){
		for(int i = 0; i < str.length(); i++){
			if(isVowel(str.charAt(i))){
				return i;
			}
		}
		return -1;
	}

	private boolean isVowel(char ch){
		ch = Character.toUpperCase(ch);
		if(ch == 'A'|| ch == 'E'|| ch == 'I'|| ch == 'O'|| ch == 'U'){
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean isSonorant(char ch){
		ch = Character.toUpperCase(ch);
		if((ch == 'M') || (ch == 'N')){
			return true;
		}
		else {
			return false;
		}
	}
}
