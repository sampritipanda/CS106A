import java.io.*;
import java.util.*;
import acm.util.ErrorException;

public class FTDataBase {
	public FTDataBase(String filename){
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			albumMap = new TreeMap<String, FTAlbum>();
			artistIndex = new TreeMap<String, ArrayList<FTTrack>>();
			while(true){
				FTAlbum album = FTAlbum.readAlbum(rd);
				if(album == null) break;
				albumMap.put(album.getTitle(), album);
				updateArtistIndex(album);
			}
		} catch(IOException ex){
			throw new ErrorException("File not Found.");
		}
	}
	
	public FTAlbum getAlbumByTitle(String title){
		return (albumMap.get(title));
	}
	
	public FTAlbum[] getAlbums(){
		return (albumMap.values().toArray(new FTAlbum[albumMap.size()]));
	}
	
	public FTTrack[] getTracksByArtist(String artist){
		return (artistIndex.get(artist).toArray(new FTTrack[artistIndex.size()]));
	}
	
	private void updateArtistIndex(FTAlbum album){
		for(FTTrack track: album.getTracks()){
			ArrayList<FTTrack> trackList = artistIndex.get(track.getArtist());
			if(trackList == null) trackList = new ArrayList<FTTrack>();
			trackList.add(track);
			artistIndex.put(track.getArtist(), trackList);
		}
	}
		
	private TreeMap<String, FTAlbum> albumMap;
	private TreeMap<String, ArrayList<FTTrack>> artistIndex;
}
