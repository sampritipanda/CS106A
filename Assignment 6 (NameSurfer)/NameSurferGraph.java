/*
 * File: NameSurferGraph.java
 * --------------------------
 * This class is responsible for updating the graph whenever the
 * list of entriesDisplayed changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class NameSurferGraph extends ResizableGCanvas
                             implements NameSurferConstants {

/**
 * Creates a new NameSurferGraph object that displays the data.
 */
	public NameSurferGraph() {
		entriesDisplayed = new ArrayList<NameSurferEntry>();
	}

/**
 * Clears the list of name surfer entriesDisplayed stored inside this class.
 */
	public void clear() {
		entriesDisplayed.clear();
	}

/**
 * Adds a new NameSurferEntry to the list of entriesDisplayed on the display.
 * Note that this method does not actually draw the graph, but
 * simply stores the entry; the graph is drawn by calling update.
 */
	public void addEntry(NameSurferEntry entry) {
		if(!(entriesDisplayed.contains(entry))){
			entriesDisplayed.add(entry);
		}
	}

/**
* Deletes an existing NameSurferEntry from the list of entriesDisplayed on the display.
* Note that this method does not actually draw the graph, but
* simply deletes the entry for the list; the graph is drawn by calling update.
*/
	public void deleteEntry(NameSurferEntry entry){
		if(entriesDisplayed.contains(entry)){
			entriesDisplayed.remove(entry);
		}
	}

/**
 * Updates the display image by deleting all the graphical objects from
 * the canvas and then reassembling the display according to the list
 * of entriesDisplayed.  Your application must call update after calling either
 * clear or addEntry; update is also called automatically whenever the
 * size of the canvas changes.
 */
	public void update() {
		removeAll();
		createGrid();
		if(entriesDisplayed.size() > 0){
			for(int i = 0; i < entriesDisplayed.size(); i++){
				NameSurferEntry entryToBeDisplayed = entriesDisplayed.get(i);
				drawEntry(entryToBeDisplayed, i);
			}
		}
	}
	
	private void createGrid(){
		drawVerticalLines();
		drawHorizontalLines();
		createDecadeLabels();
	}
	
	private void drawVerticalLines(){
		double seperation = getWidth()/NDECADES;
		for(int i = 0; i < NDECADES; i++){
			double x0 = i * seperation;
			double x1 = x0;
			double y0 = 0;
			double y1 = getHeight();
			add(new GLine(x0, y0, x1, y1));
		}
	}
	
	private void drawHorizontalLines(){
		GLine topBorder = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(topBorder);
		
		GLine bottomBorder = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(bottomBorder);
	}
	
	private void createDecadeLabels(){
		double seperation = getWidth()/NDECADES;
		for(int i = 0; i < NDECADES; i++){
			double x = i * seperation + 2;
			int y = getHeight() - 2;
			if(i >= 0 && i <= 9){
				add(new GLabel("19" + i + "0"), x, y);
			}
			else if(i == 10){
				add(new GLabel("2000"), x, y);
			}
			else {
				add(new GLabel("2010"), x, y);
			}
		}
	}
	
	private void drawEntry(NameSurferEntry entry, int color){
		color %= 4;
		double seperation = getWidth()/NDECADES;
		double totalYArea = getHeight() - (GRAPH_MARGIN_SIZE * 2);
		for(int i = 0; i < NDECADES - 1; i++){
			int rank1 = entry.getRank(i);
			int rank2 = entry.getRank(i + 1);
			double x0 = seperation * i;
			double x1 = seperation * (i + 1);
			double y0 = 0;
			double y1 = 0;
			if(rank1 != 0) {
			
				y0 = ((totalYArea/MAX_RANK) * rank1) + GRAPH_MARGIN_SIZE;
			}
			if(rank1 == 0) {
				y0 = getHeight() - GRAPH_MARGIN_SIZE;
			}
			if (rank2 != 0){
				y1 = ((totalYArea/MAX_RANK) * rank2) + GRAPH_MARGIN_SIZE;
			}
			if(rank2 == 0) {
				y1 = getHeight() - GRAPH_MARGIN_SIZE;
			}
			GLine line = new GLine(x0, y0, x1, y1);
						
			GLabel label1 = null;
			GLabel label2 = null;
			
			if(rank1 != 0){
				label1 = new GLabel(entry.getName() + " " + rank1);
			}
			else if (rank1 == 0){
				label1 = new GLabel(entry.getName() + " *");
			}
			if(rank2 != 0){
				label2 = new GLabel(entry.getName() + " " + rank2);
			}
			else if (rank2 == 0){
				label2 = new GLabel(entry.getName() + " *");
			}
			
			if(color == 0){
				line.setColor(Color.RED);
				label1.setColor(Color.RED);
				label2.setColor(Color.RED);
			}
			else if(color == 1){
				line.setColor(Color.BLUE);
				label1.setColor(Color.BLUE);
				label2.setColor(Color.BLUE);
			}
			else if(color == 2){
				line.setColor(Color.ORANGE);
				label1.setColor(Color.ORANGE);
				label2.setColor(Color.ORANGE);
			}
			else if(color == 3){
				line.setColor(Color.MAGENTA);
				label1.setColor(Color.MAGENTA);
				label2.setColor(Color.MAGENTA);
			}
			
			add(line);
			add(label1, x0 + 2, y0 - 2);
			add(label2, x1 + 2, y1 - 2);
		}
	}
	
/* Private Instance Variables */
	private ArrayList<NameSurferEntry> entriesDisplayed;

}
