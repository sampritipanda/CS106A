import acm.program.*;

public class TestTurtleTokenizer extends ConsoleProgram {
	
	public void run(){
		while(true){
			String cmd = readLine("Enter a turtle program: ");
			TurtleTokenizer tokenizer = new TurtleTokenizer(cmd);
			for(int count = 0; tokenizer.hasMoreTokens() ; count++) {
				println(tokenizer.nextToken());
			}
		}
	}
}
