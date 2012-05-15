/**
 * This program reads a list of exam scores, with one score per line.
 * It then displays a histogram of those scores, divided into the
 * ranges 0-9, 10-19, 20-29, and so forth, up to the range containing
 * only the value 100.
 * @author Eric
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import acm.program.*;

public class Histogram extends ConsoleProgram{
	
	public void run(){
		double[] scores = readScoresArray("Midterm.txt");
		initHistogram();
		createHistogram(scores);
		printHistogram();
	}
	
	private void initHistogram(){
		histogramArray = new int[11];
	}
	
	private void createHistogram(double[] scores){
		for(int i = 0; i < scores.length; i++){
			int range = (int)(scores[i]/10);
			histogramArray[range]++;
		}
	}
	
	private void printHistogram(){
		for(int i = 0; i < histogramArray.length; i++){
			if(i < 10){
				println(i + "0-" + i + "9: " + printStars(histogramArray[i]));
			}
			else if(i == 10){
				println("  100: " + printStars(histogramArray[i]));
			}
		}
	}
	
	private String printStars(int range){
		String stars = "";
		for(int i = 0; i < range; i++){
			stars += "*";
		}
		return stars;
	}
	private double[] readScoresArray(String str) {
		int numLines = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(str));
			while(true){
				String line = br.readLine();
				if(line == null)break;
				numLines++;
			}
		} catch (FileNotFoundException e) {
			println("File not found.");
		} catch (IOException e) {
			println("IOException.");
		}
		double[] array = new double[numLines];
		int curLine = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(str));
			while(true){
				String line = br.readLine();
				if(line == null)break;
				array[curLine++] = Double.parseDouble(line);
			}
		} catch (FileNotFoundException e) {
			println("File not found.");
		} catch (IOException e) {
			println("IOException.");
		}
		return array;
	}
	
	private int[] histogramArray;
}

