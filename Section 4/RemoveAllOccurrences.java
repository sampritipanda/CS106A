public class RemoveAllOccurrences{
	
	public String removeAllOccurrences(String str, char ch){
		String result = "";
		for(int i = 0; i < str.length(); i++){
			char x = str.charAt(i);
			if(x != ch){
				result += x;
			}
		}
		return result;
	}
}
