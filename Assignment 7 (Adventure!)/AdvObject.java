/*
 * File: AdvObject.java
 * --------------------
 * This file defines a class that models an object in the
 * Adventure game.
 */

import java.io.*;
import acm.util.*;
 
/**
 * This class defines an object in the Adventure game.  An object is
 * characterized by the following properties:
 *
 * <ul>
 * <li>Its name, which is the noun used to refer to the object
 * <li>Its description, which is a string giving a short description
 * <li>The room number in which the object initially lives
 * </li>
 *
 * The external format of the objects file is described in the assignment.
 * The comments on the methods exported by this class show how to
 * use the initialized data structure.
 */

public class AdvObject {

/**
 * Returns the object name, which is the word used to refer to it.
 *
 * @return The name of the object
 */
	public String getName() {
		return objectName;
	}

/**
 * Returns the one-line description of the object.  This description should
 * start with an article, as in "a set of keys" or "an emerald the size of
 * a plover's egg."
 *
 * @return The description of the object
 */
	public String getDescription() {
		return objectDescription;
	}

/**
 * Returns the initial location of the object.  If this method returns 0,
 * that is taken to mean that the player is holding it.
 *
 * @return The room number in which the object initially resides
 */
	public int getInitialLocation() {
		return objectInitialLocation;
	}

/**
 * Creates a new object by reading its data from the specified reader.
 * If no data is left in the reader, this method returns <code>null</code>
 * instead of an <code>AdvObject</code> value.  Note that this is a
 * static method, which means that you need to call
 *
 *<pre><code>
 *     AdvObject.readObject(rd)
 *</code></pre>
 *
 * @param rd The reader from which the room data is read
 */
	public static AdvObject readObject(BufferedReader rd) {
		try {
			AdvObject object = new AdvObject();
			
			String line = rd.readLine();
			if(line == null) return null;
			if(line.equals("") || line.length() == 0){
				line = rd.readLine();
			}
			object.objectName = line;
			
			line = rd.readLine();
			object.objectDescription = line;
			
			line = rd.readLine();
			object.objectInitialLocation = Integer.parseInt(line);
			
			return object;
		} catch(IOException ex){
			throw new ErrorException(ex);
		} catch(NumberFormatException ex){
			throw new ErrorException("Illegal room number");
		}
	}
	
	private String objectName;
	private String objectDescription;
	private int objectInitialLocation;
	
}
