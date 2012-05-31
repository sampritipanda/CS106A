import java.io.BufferedReader;
import java.io.IOException;
import acm.util.ErrorException;

public class FTTrack {
	
	public String getSongTitle(){
		return title;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public int getRunningTime(){
		return runTime;
	}
	
	public FTAlbum getAlbum(){
		return album;
	}
	
	public String toString(){
		return(artist + ": \"" + title + "\" (" + FTTrack.convertSecondsToTimeString(runTime) + ")");
	}
	
	public static FTTrack readTrack(BufferedReader rd){
		try {
			String line = rd.readLine();
			if(line.equals("") || line.length() == 0 || line == null) return null;
			FTTrack track = new FTTrack();
			int colon = line.indexOf(":");
			if(colon == -1){
				throw new ErrorException("Missing colon after artist.");
			}
			track.artist = line.substring(0, colon);
			
			int startQuote = line.indexOf("\"", colon + 1);
			int endQuote = line.indexOf("\"", startQuote + 1);
			if(startQuote == -1 || endQuote == -1){
				throw new ErrorException("Missing quotes around title.");
			}
			track.title = line.substring(startQuote + 1, endQuote);
			
			int startBracket = line.indexOf("(", endQuote + 1);
			int endBracket = line.indexOf(")", startBracket + 1);
			track.runTime = FTTrack.convertTimeStringToSeconds(line.substring(startBracket + 1, endBracket));
			
			return track;
		} catch(IOException ex){
			throw new ErrorException(ex);
		} catch(NumberFormatException ex){
			throw new ErrorException("Illegal running time.");
		}
	}
	
	public static int convertTimeStringToSeconds(String time){
		int colon = time.indexOf(":");
		int minutes = Integer.parseInt(time.substring(0, colon));
		int seconds = Integer.parseInt(time.substring(colon + 1));
		return minutes * 60 + seconds;
	}
	
	public static String convertSecondsToTimeString(int seconds){
		String secondsString = "" + seconds % 60;
		String minutesString = "" + seconds/60;
		return (minutesString + ":" + secondsString);
	}
	
	void setAlbum(FTAlbum album){
		this.album = album;
	}
	
	private FTAlbum album;
	private String title;
	private String artist;
	private int runTime;
}
