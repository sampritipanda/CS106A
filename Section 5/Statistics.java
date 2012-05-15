/*
 * File: Statistics.java
 * ------------------------
 * This file reads in an array of scores and computes two statistics
 * on the distribution: the mean and the standard deviation.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import acm.program.*;

public class Statistics extends ConsoleProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void run(){
		double[] scores = readScoresArray("Midterm.txt");
		double mean = calculateMean(scores);
		println("The mean of the distribution is " + mean);
		println("The standard deviation of the distribution is " + calculateStdDev(scores, mean));
	}
	
	private double calculateMean(double[] scores){
		double scoresSize = scores.length;
		double scoresSum = 0;
		for(int i = 0; i < scoresSize; i++){
			scoresSum += scores[i];
		}
		double mean = scoresSum/scoresSize;
		return mean;
	}
	
	private double calculateStdDev(double scores[], double mean){
		double scoresSize = scores.length;
		double total = 0;
		for(int i = 1; i <= scoresSize; i++){
			double delta = mean - scores[i - 1];
			total += Math.pow(delta, 2);
		}
		double stdDev = Math.sqrt(total/scoresSize);
		return stdDev;
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
}