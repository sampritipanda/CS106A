import java.util.*;
import java.io.*;
import acm.util.*;

public class PotionsManager {
	
	public PotionsManager(String filename){
		potions = new TreeMap<String, ArrayList<String>>();
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while(true){
				String potionName = rd.readLine();
				if(potionName == null) break;
				ArrayList<String> ingridients = new ArrayList<String>();
				while(true){
					String line = rd.readLine();
					if(line == null || line.length() == 0) break;
					ingridients.add(line);
				}
				potions.put(potionName, ingridients);
			}
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	public String[] getPotionNames(){
		String[] potionNames = new String[potions.size()];
		int count = 0;
		for(String name: potions.keySet()){
			potionNames[count++] = name;
		}
		return potionNames;
	}
	
	public String[] getIngredients(String potionName){
		if(potions.containsKey(potionName)){
			ArrayList<String> ingridientsList = potions.get(potionName);
			String[] ingridients = new String[ingridientsList.size()];
			int count = 0;
			for(String ingridient: ingridientsList){
				ingridients[count++] = ingridient;
			}
			return ingridients;
		} else {
			return null;
		}
	}
	
	private Map<String, ArrayList<String>> potions;
	
}
