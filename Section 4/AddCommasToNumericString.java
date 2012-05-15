import acm.program.*;

public class AddCommasToNumericString extends ConsoleProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void run() {
		while (true) {
		String digits = readLine("Enter a numeric string: ");
		if (digits.length() == 0) break;
		println(addCommasToNumericString(digits));
		}
	}
	
	private String addCommasToNumericString(String digits){
		String result = "";
		int counter = 0;
		int count = digits.length() - 1;
		for(int i = 0; i < digits.length(); i++){
			if(counter == 3){
				result = "," + result;
				counter = 0;
			}
			result = digits.charAt((count)) + result;
			counter++;
			count--;
		}
		return result;
	}
}
