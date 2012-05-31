/*
 * File: AdvMotionTableEntry.java
 * ------------------------------
 * This class keeps track of an entry in the motion table.
 */

/**
 * This class is used to store a single entry in the motion table.
 * In keeping with modern object-oriented design, the instance variables
 * of this class are private and can be obtained only through the accessor
 * methods getDirection, getDestinationRoom, and getKeyName.
 */

public class AdvMotionTableEntry {

/**
 * Creates a new motion table entry indicating that moving in the
 * direction specified by the string dir should take the player to
 * the specified room.  If the key parameter is not null, it specifies
 * the name of an object that the player must be carrying to travel
 * along this passage.
 *
 * @param dir The verb indicating the direction of motion (e.g., WEST)
 * @param room The number of the room to which that passage leads
 * @param key The name of an object needed to unlock that passage
 */
	public AdvMotionTableEntry(String dir, int room, String key) {
		direction = dir.toUpperCase();
		destinationRoom = room;
		keyName = (key == null) ? null : key.toUpperCase();
	}

/**
 * Returns the direction name from a motion table entry.
 *
 * @return The string specifying the direction of motion
 */
	public String getDirection() {
		return direction;
	}

/**
 * Returns the room number to which a particular direction leads.
 *
 * @return The number of the destination room
 */
	public int getDestinationRoom() {
		return destinationRoom;
	}

/**
 * Returns the name of the object required for travel along a locked
 * passage, or null if the passage is always available.
 *
 * @return The name of the object used as a key, or null if none
 */
	public String getKeyName() {
		return keyName;
	}

/* Private instance variables */
	private String direction;
	private int destinationRoom;
	private String keyName;
}
