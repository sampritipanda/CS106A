/*
 * File: Adventure.java
 * --------------------
 * This program plays the CS106A Adventure game.
 */

import acm.program.*;
import java.util.*;
import java.io.*;
import acm.util.*;
 
/**
 * This class is the main program class for the Adventure game.
 */

public class Adventure extends ConsoleProgram {

	private HashMap<Integer, AdvRoom> rooms;
	private HashMap<String, AdvObject> objects;
	private HashMap<String, String> synonyms;
	private HashSet<String> motionSet;
	private String adventure;
	private int currentRoomNumber = 1;
	private AdvRoom room;
	private AdvObject object;
	private ArrayList<AdvObject> inventory = new ArrayList<AdvObject>();
	
	public void run() {
		println("Welcome to Adventure!");
		adventure = readLine("Enter the name of the adventure: ");
		initDataStructure();
		while(currentRoomNumber != 0){
			playTurn();
		}
	}
	
	private void initDataStructure(){
		initRooms();
		initObjects();
		initSynonyms();
	}
	
	private void initRooms(){
		try {
			rooms = new HashMap<Integer, AdvRoom>();
			motionSet = new HashSet<String>();
			String roomFile = adventure + "Rooms.txt";
			BufferedReader rd = new BufferedReader(new FileReader(roomFile));
			while(true){
				AdvRoom room = AdvRoom.readRoom(rd);
				if(room == null) break;
				initMotionTable(room.getMotionTable());
				int roomNumber = room.getRoomNumber();
				rooms.put(new Integer(roomNumber), room);
			}
			rd.close();
		} catch(IOException ex){
			throw new ErrorException("Rooms file does not exist.");
		}
	}
	
	private void initObjects(){
		try {
			objects = new HashMap<String, AdvObject>();
			String objectFile = adventure + "Objects.txt";
			BufferedReader rd = new BufferedReader(new FileReader(objectFile));
			while(true){
				AdvObject object = AdvObject.readObject(rd);
				if(object == null) break;
				String objectName =  object.getName();
				objects.put(objectName, object);
				rooms.get(object.getInitialLocation()).addObject(object);
			}
			rd.close();
		} catch(IOException ex){}
	}
	
	private void initSynonyms(){
		try {
			String synonymsFile = adventure + "Synonyms.txt";
			synonyms = new HashMap<String, String>();
			BufferedReader rd = new BufferedReader(new FileReader(synonymsFile));
			while(true){
				String line = rd.readLine();
				if(line == null) break;
				line = removeAllWhitespace(line);
				String synonym = line.substring(0, line.indexOf("="));
				String word = line.substring(line.indexOf("=") + 1);
				synonyms.put(synonym, word);
			}
			rd.close();
		} catch(IOException ex){}
	}

	private String removeAllWhitespace(String line){
		String result = "";
		for(int i = 0; i < line.length(); i++){
			char ch = line.charAt(i);
			if(ch != ' ') result += ch;
		}
		return result;
	}
	
	private void playTurn(){
		room = rooms.get(currentRoomNumber);
		writeDescription();
		if(!(roomIsForced())){
			room.setVisited(true);
		}
		while(true){
			ArrayList<String> commands = null;
			if(!(roomIsForced())){
				commands = readCommand();
			}
			int nextRoomNumber = executeCommand(commands);
			if(nextRoomNumber != -1){
				currentRoomNumber = nextRoomNumber;
				break;
			}
		}
	}
	
	private void writeDescription(){
		if(!(room.hasBeenVisited())){
			String[] description = room.getDescription();
			for(String line: description){
				println(line);
			}
		} else {
			println(room.getName());
		}
		for(AdvObject obj: room.getObjects()){
			println("There is " + obj.getDescription() + " here.");
		}
	}
	
	private ArrayList<String> readCommand(){
		String cmd = "";
		while(true){
			cmd = readLine("> ");
			if(!(cmd.equals(""))) break;
		}
		StringTokenizer tokenizer = new StringTokenizer(cmd);
		ArrayList<String> commands = new ArrayList<String>();
		while(tokenizer.hasMoreTokens()){
			commands.add(tokenizer.nextToken().toUpperCase());
		}
		commands = convertSynonymsToWords(commands);
		if(commands.size() > 1){
			object = objects.get(commands.get(1));
			if(object == null){
				println("I don't understand the word \"" + commands.get(1) + "\".");
			}
		} else {
			object = null;
		}
		return commands;
	}
	
	private int executeCommand(ArrayList<String> commands){
		if(commands == null){
			return executeMotionCommand(null);
		}
		String command = commands.get(0);
		if(motionSet.contains(command)){
			int nextRoomNumber = executeMotionCommand(command);
			if(nextRoomNumber != -1) return nextRoomNumber;
		}
		else if(command.equals("QUIT")){
			executeQuitCommand();
		}
		else if(command.equals("HELP")){
			printInstructions();
		}
		else if(command.equals("INVENTORY")){
			displayInventory();
		}
		else if(command.equals("LOOK")){
			room.setVisited(false);
			writeDescription();
			room.setVisited(true);
		}
		else if(command.equals("TAKE")){
			takeObject(commands);
		}
		else if(command.equals("DROP")){
			dropObject(commands);
		}
		else {
			println("I don't know how to apply that word here.");
		}
		
		return -1;
	}
	
	private ArrayList<String> convertSynonymsToWords(ArrayList<String> oldCommands){
		ArrayList<String> newCommands = new ArrayList<String>();
		for(String cmd: oldCommands){
			if(synonyms.containsKey(cmd)){
				newCommands.add(synonyms.get(cmd));
			} else {
				newCommands.add(cmd);
			}
		}
		return newCommands;
	}
	
	private void initMotionTable(AdvMotionTableEntry[] motionTable){
		for(int i = 0; i < motionTable.length; i++){
			AdvMotionTableEntry entry = motionTable[i];
			String motion = entry.getDirection();
			motionSet.add(motion);
		}
	}
	
	private void executeQuitCommand(){
		while(true){	
			String response = readLine("Are you sure you want to quit now? ");
			response = response.toLowerCase();
			if(response.equals("yes")) System.exit(0);
			else if(response.equals("no")) break;
			else println("Please answer \"yes\" or \"no\".");
		}
	}
	
	private void printInstructions(){
		println("Welcome to Adventure!");
		println("Somewhere nearby is Colossal Cave, where others have found fortunes in");
		println("treasure and gold, though it is rumored that some who enter are never");
		println("seen again.  Magic is said to work in the cave.  I will be your eyes");
		println("and hands.  Direct me with natural English commands; I don't understand");
		println("all of the English language, but I do a pretty good job.");
		println("");
		println("It's important to remember that cave passages turn a lot, and that");
		println("leaving a room to the north does not guarantee entering the next from");
		println("the south, although it often works out that way.  You'd best make yourself");
		println("a map as you go along.");
		println("");
		println("Much of my vocabulary describes places and is used to move you there.");
		println("To move, try words like IN, OUT, EAST, WEST, NORTH, SOUTH, UP, or DOWN.");
		println("I also know about a number of objects hidden within the cave which you");
		println("can TAKE or DROP.  To see what objects you're carrying, say INVENTORY.");
		println("To reprint the detailed description of where you are, say LOOK.  If you");
		println("want to end your adventure, say QUIT.");
	}
	
	private void displayInventory(){
		println("You are carrying: ");
		for(int i = 0; i < inventory.size(); i++){
			println(inventory.get(i).getDescription());
		}
	}
	
	private void takeObject(ArrayList<String> commands) {
		if(commands.size() == 1 || object == null){
			println("I don't know what you want me to take.");
		} else {
			if(room.containsObject(object)){
				room.removeObject(object);
				inventory.add(object);
				println("Taken.");
			} else {
				println("I don't see that here.");
			}
		}
	}
	
	private void dropObject(ArrayList<String> commands) {
		if(commands.size() == 1 || object == null){
			println("I don't know what you want me to drop.");
		} else {
			AdvObject object = objects.get(commands.get(1));
			if(!(inventory.contains(object))){
				println("You're not carrying that.");
			} else {
				room.addObject(object);
				inventory.remove(object);
				println("Dropped.");
			}
		}
	}
	
	private int executeMotionCommand(String cmd){
		AdvMotionTableEntry[] motionTable = room.getMotionTable();
		int nextRoom = -1;
		for(int i = 0; i < motionTable.length; i++){
			AdvMotionTableEntry motionEntry = motionTable[i];
			if(motionEntry.getDirection().equals("FORCED")){
				return executeForcedCommand(motionTable);
			}
			else if(motionEntry.getDirection().equals(cmd)){
				String key = motionEntry.getKeyName();
				AdvObject keyObject = null;
				if(key != null){
					keyObject = objects.get(key);
					if(inventory.contains(keyObject)){
						return motionEntry.getDestinationRoom();
					}
				} else {
					nextRoom = motionEntry.getDestinationRoom();
				}
			}
		}
		if(nextRoom == -1){
			println("There is no way to move in that direction.");
		}
		return nextRoom;
	}
	
	private int executeForcedCommand(AdvMotionTableEntry[] motionTable){
		int nextRoom = 0;
		for(int i = 0; i < motionTable.length; i++){
			AdvMotionTableEntry motionEntry = motionTable[i];
			String key = motionEntry.getKeyName();
			AdvObject keyObject = null;
			if(key != null){
				keyObject = objects.get(key);
				if(inventory.contains(keyObject)){
					return motionEntry.getDestinationRoom();
				}
			} else {
				nextRoom = motionEntry.getDestinationRoom();
			}
		}
		return nextRoom;
	}
	
	private boolean roomIsForced(){
		AdvMotionTableEntry[] motionTable = room.getMotionTable();
		for(int i = 0; i < motionTable.length; i++){
			AdvMotionTableEntry motionEntry = motionTable[i];
			if(motionEntry.getDirection().equals("FORCED")) return true;
		}
		return false;
	}
}