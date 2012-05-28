/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initialising the interactors at the bottom of the window.
 */
	public void init() {		
		//Entry Input Field
		JLabel name = new JLabel("Name to be displayed  ");
		add(name, SOUTH);
		nameField = new JTextField(JTEXT_FIELD_SIZE);
		add(nameField, SOUTH);
		add(new JButton("Graph"), SOUTH);
		
		//Clear Button
		add(new JButton("Clear"), SOUTH);
				
		//Entry Delete Field
		add(new JLabel("Name to be deleted  "), NORTH);
		entryDeleteField = new JTextField(JTEXT_FIELD_SIZE);
		add(entryDeleteField, NORTH);
		add(new JButton("Delete"), NORTH);
		
		addActionListeners();
		
		namesDataBase = new NameSurferDataBase(NAMES_DATA_FILE);
		
		graph = new NameSurferGraph();
		add(graph, CENTER);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Graph")){
			String name = nameField.getText();
			NameSurferEntry entry = namesDataBase.findEntry(name);
			if(entry != null){
				graph.addEntry(entry);
				graph.update();
			}
		}
		else if(e.getActionCommand().equals("Clear")){
			graph.clear();
			graph.update();
		}
		else if(e.getActionCommand().equals("Delete")){
			String name = entryDeleteField.getText();
			NameSurferEntry entry = namesDataBase.findEntry(name);
			if(entry != null){
				graph.deleteEntry(entry);
				graph.update();
			}
		}
	}
	
/* Private Instance Variable */
	private JTextField nameField;
	private JTextField entryDeleteField;
	private NameSurferDataBase namesDataBase;
	private NameSurferGraph graph;
}
