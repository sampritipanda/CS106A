import java.io.*;
import java.util.*;
import acm.util.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		BufferedReader rd = openFile(filename);
		try {
			while(true){
				String line = rd.readLine();
				if(line == null){
					break;
				}
				NameSurferEntry nameEntry = new NameSurferEntry(line);
				namesData.put(nameEntry.getName(), nameEntry);
			}
			rd.close();
		} catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
	
	private BufferedReader openFile(String filename){
		BufferedReader rd = null;
		while(rd == null){
			try {
				rd = new BufferedReader(new FileReader(filename));
			} catch(IOException ex){
				throw new ErrorException(ex);
			}
		}
		return rd;
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		if(name.equals("")){
			return null;
		}
		else {
			char first = name.charAt(0);
			if(Character.isLowerCase(first) == true) {
				first = Character.toUpperCase(first);
			}
			name = first + (name.substring(1)).toLowerCase();
			if(namesData.containsKey(name)){
				return namesData.get(name);
			}
			else {
				return null;
			}
		}
	}
	
/* Private Instance Variables */
	private HashMap<String, NameSurferEntry> namesData = new HashMap<String, NameSurferEntry>();
}

