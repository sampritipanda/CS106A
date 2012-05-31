/*
 * File: AdvRoom.java
 * ------------------
 * This file defines a class that models a single room in the
 * Adventure game.
 */

import java.io.*;
import java.util.*;
import acm.util.*;
 
/**
 * This class defines a single room in the Adventure game.  A room is
 * is characterized by the following properties:
 *
 * <ul>
 * <li>A room number, which must be greater than zero
 * <li>Its name, which is a one-line string identifying the room
 * <li>Its description, which is a line array describing the room
 * <li>A list of objects contained in the room
 * <li>A flag indicating whether the room has been visited
 * <li>A motion table specifying the exits and where they lead
 * </li>
 *
 * The external format of the room file is described in the assignment
 * handout.
 */

public class AdvRoom {

/**
 * Returns the room number.
 *
 * @return The room number
 */
	public int getRoomNumber() {
		return roomNumber;
	}

/**
 * Returns the room name, which is its one-line description.
 *
 * @return The room name
 */
	public String getName() {
		return roomName;
	}

/**
 * Returns an array of strings that correspond to the long description
 * of the room.
 *
 * @return An array of strings giving the long description of the room
 */
	
	public String[] getDescription() {
		return roomDescription;
	}
	
/**
 * Adds an object to the list of objects in the room.
 *
 * @param The AdvObject to be added
 */

	public void addObject(AdvObject obj) {
		objects.add(obj);
	}

/**
 * Removes an object from the list of objects in the room.
 *
 * @param The AdvObject to be removed
 */
	public void removeObject(AdvObject obj) {
		objects.remove(obj);
	}

/**
 * Checks whether the specified object is in the room.
 *
 * @param The AdvObject being tested
 * @return true if the object is in the room, and false otherwise
 */
	public boolean containsObject(AdvObject obj) {
		return objects.contains(obj);
	}

/**
 * Returns an array of all the objects in the room.
 *
 * @return An array containing the objects in the room
 */
	public AdvObject[] getObjects() {
		AdvObject[] objectArray = new AdvObject[objects.size()];
		int count = 0;
		for(AdvObject object: objects){
			objectArray[count++] = object;
		}
		return objectArray;
	}
/**
 * Sets the flag indicating that this room has been visited as specified
 * by the value of the parameter flag.  Calling setVisited(true) means
 * that the room has been visited; calling setVisited(false) restores
 * its initial state.
 *
 * @param flag The new state of the "visited" flag
 */
	public void setVisited(boolean flag) {
		visited = flag;
	}

/**
 * Returns true if the room has previously been visited.
 *
 * @return true if the room has previously been visited; false otherwise
 */
	public boolean hasBeenVisited() {
		return visited;
	}

/**
 * Returns the motion table associated with this room, which is an
 * array of directions, room numbers, and enabling objects stored
 * in a AdvMotionTableEntry.
 *
 * @return The array of motion table entries associated with this room
 */
	public AdvMotionTableEntry[] getMotionTable() {
		return motionTable;
	}

/**
 * Creates a new room by reading its data from the specified reader.
 * If no data is left in the reader, this method returns <code>null</code>
 * instead of an <code>AdvRoom</code> value.  Note that this is a
 * static method, which means that you need to call
 *
 *<pre><code>
 *     AdvRoom.readRoom(rd)
 *</code></pre>
 *
 * @param rd The reader from which the room data is read
 */
	public static AdvRoom readRoom(BufferedReader rd) {
		try {
			String line = rd.readLine();
			if(line == null) return null;
			AdvRoom room = new AdvRoom();
			room.roomNumber = Integer.parseInt(line);
			room.roomName = rd.readLine();
			ArrayList<String> roomDescriptionList = new ArrayList<String>();
			while(true){
				line = rd.readLine();
				if(line.equals(MARKER)) break;
				roomDescriptionList.add(line);
			}
			room.roomDescription = new String[roomDescriptionList.size()];
			int count1 = 0;
			for(String descriptionLine: roomDescriptionList){
				room.roomDescription[count1++] = descriptionLine;
			}
			while(true){
				line = rd.readLine();
				if(line == null || line.equals("")) break;
				parseMotionLine(room, line);
			}
			int counter = 0;
			room.motionTable = new AdvMotionTableEntry[room.motionTableList.size()];
			for(AdvMotionTableEntry motionEntry: room.motionTableList){
				room.motionTable[counter++] = motionEntry;
			}
			return room;
		}
		catch(IOException ex){
			throw new ErrorException(ex);
		}
		catch(NumberFormatException ex){
			throw new ErrorException("Illegal room number");
		}
	}

/** 
 * This method scans the motion line to separate the text of the motion verb
 * from the number of the next room. The motion verb and the next room number 
 * is entered into the HashMap in the AdvRoom structure.
 * 
 * @param room The room entry in which the motionTable has to be 
 * @param line The line to to be separated(parsed). 
 */
	private static void parseMotionLine(AdvRoom room, String line){
		line = line.trim();
		int motionVerbEnd = line.indexOf(" ");
		String motionVerb = line.substring(0, motionVerbEnd);
		int nextRoomNumber = 0;
		if(Character.isDigit(line.charAt(line.length() - 1))){
			nextRoomNumber = Integer.parseInt(line.substring(motionVerbEnd).trim());
			room.motionTableList.add(new AdvMotionTableEntry(motionVerb, nextRoomNumber, null));
		}
		else {
			int roomNumberEnd = line.indexOf("/");
			nextRoomNumber = Integer.parseInt(line.substring(motionVerbEnd, roomNumberEnd).trim());
			String key = line.substring(roomNumberEnd + 1).trim();
			room.motionTableList.add(new AdvMotionTableEntry(motionVerb, nextRoomNumber, key));
		}
	}
	
/** Private Instance Variables */
	private int roomNumber;
	private String roomName;
	private String[] roomDescription;
	private AdvMotionTableEntry[] motionTable;
	private ArrayList<AdvMotionTableEntry> motionTableList = new ArrayList<AdvMotionTableEntry>();
	private boolean visited;
	private ArrayList<AdvObject> objects = new ArrayList<AdvObject>();
	
/** Constants */
	private static final String MARKER = "-----";

}
