import java.io.BufferedReader;
import java.io.IOException;
import acm.util.ErrorException;
import java.util.ArrayList;

public class FTAlbum {
	
	public String getTitle(){
		return title;
	}
	
	public int getTotalRunningTime(){
		int time = 0;
		for(int i = 0; i < trackList.size(); i++){
			time += trackList.get(i).getRunningTime();
		}
		return time;
	}
	
	public FTTrack[] getTracks(){
		return trackList.toArray(new FTTrack[trackList.size()]);
	}
	
	public static FTAlbum readAlbum(BufferedReader rd){
		try {
			String line = rd.readLine();
			if(line == null) return null;
			FTAlbum album = new FTAlbum();
			album.title = line;
			
			line = rd.readLine();
			if(!(line.subSequence(0, 3).equals("---"))) throw new ErrorException("Missing marker in line");
			
			album.trackList = new ArrayList<FTTrack>();
			while(true){
				line = rd.readLine();
				FTTrack track = FTTrack.readTrack(rd);
				if(track == null) break;
				track.setAlbum(album);
				album.trackList.add(track);
			}
			return album;
		} catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
	
	private String title;
	private ArrayList<FTTrack> trackList;
	
}
