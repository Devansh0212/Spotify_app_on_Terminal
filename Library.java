//Name: Devansh Brahmbhatt
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	AudioContentStore contentStore = new AudioContentStore();
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = ""; //Initializing empty string 
	
	public String getErrorMessage(){ // method for returning errorMsg
		return errorMsg;
	}

	public Library(){
		songs 		= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content and we are going to use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc
	 * to determine which list it belongs to above
	 * 
	 * To make sure that we do not add song/podcast/audiobook to a list if it is already there.We use the equals() method
	 * If it is already in a list,we set the errorMsg string and return false.Otherwise add it to the list and return true
	 */
	public void download(AudioContent content){
		if(content.getType() == Song.TYPENAME){ //checking the type of given content
			for(int i=0;i<songs.size();i++){   //checking if the song is already in the songs arraylist or not 
				if(songs.get(i).equals(content)){
					throw new AlreadyDownloaded("Song "+content.getTitle()+" already downloaded"); // printing " already downloaded " if song is already in the songs arraylist
				}
			}
			songs.add((Song)content);  // else add the content to songs arraylist by typecasting it to Song and returning true
			System.out.println("SONG "+ content.getTitle() +" Added to Library");
		}

		if(content.getType() == AudioBook.TYPENAME){ //checking the type of given content
			for(int i=0;i<audiobooks.size();i++){   //checking if the audiobook is already in the audiobooks arraylist  
				if(audiobooks.get(i).equals(content)){
					throw new AlreadyDownloaded("Audiobook "+content.getTitle()+" already downloaded");  //if the audiobook is in the audiobooks arraylist then setting the errormsg and returning false
				}
			}
			audiobooks.add((AudioBook)content);  //otherwise add the content to audiobooks of arraylist by typecasting it to Audiobook and returning true
			System.out.println("AUDIOBOOK "+ content.getTitle() +" Added to Library"); // printing the above
		}
	}

	public void downloadAG(AudioContent content){
		if(content.getType() == Song.TYPENAME){ //checking the type of given content
			for(Song s : songs){   //checking if the song is already in the songs arraylist
				if(s.equals(content)){
					throw new AlreadyDownloaded("Song "+content.getTitle()+" already downloaded"); // printing "already downloaded if it is in the audiobooks arraylist"
				}
			}		
			songs.add((Song)content);  //otherwise adding the content to songs arraylist by typecasting it to Song and returning true
		}
			
		if(content.getType() == AudioBook.TYPENAME){ //checking the type of given content
			for(AudioBook a : audiobooks){   //checking if the audiobook is already in the audiobooks arraylist  
				if(a.equals(content)){
					throw new AlreadyDownloaded("Audiobook "+content.getTitle()+" already downloaded");  //if the audiobook is in the audiobooks arraylist then setting the errormsg and returning false
				}
			}
			audiobooks.add((AudioBook)content);  //otherwise adding the content to audiobooks arraylist by typecasting it to Audiobook and returning true
		}
	}

	// Printing Information (printInfo()) about all songs in the array list
	public void listAllSongs(){
		for (int i = 0; i < songs.size(); i++)  //looping through every element of the songs arraylist and calling printInfo() method for each
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Printing Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks(){
		for (int i = 0; i < audiobooks.size(); i++)  //looping through every element of the audiobooks arraylist and calling printInfo() method for each
		{
			int index = i + 1; 
			System.out.print("" + index + ". ");  //printing the 1s indexing
			audiobooks.get(i).printInfo();
			System.out.println();	
		}
	}

  	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists(){
		for (int i = 0; i < playlists.size(); i++)  //looping through every element of the playlists arraylist and printing thier titles.
		{
			int index = i + 1; 
			System.out.print("" + index + ". "); 	//printing the 1s indexing
			System.out.println(playlists.get(i).getTitle()); 	
		}
	}
	
  	// Print the name of all artists. 
	public void listAllArtists(){
		ArrayList<String> artists = new ArrayList<String>();
		// Firstly we create a new (empty) array list of string 
		// Which goes through the songs array list and add the artist name to the new arraylist only if it is
		// If not already there.Once the artist arraylist is complete,print the artists names
		for(int i=0;i<songs.size();i++){     		 		 //checking if the artist is already in the artists arraylist
			if(artists.contains(songs.get(i).getArtist())){
				continue;									 //if the artist is already in the list then continue to next 
			}
			artists.add(songs.get(i).getArtist()); 			 //if the artist is not in the list then add it to the list
		}
		for(int i=0;i<artists.size();i++){
			System.out.println((i+1)+". "+artists.get(i));	 //printing all artists with 1s indexing
		}
		
	}

	// Delete a song from the library (i.e. the songs list) - 
	// and we also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public boolean deleteSong(int index){
		for(int i=0;i<playlists.size();i++){ //using a nested loop to go through the playlists arraylist and the contents arraylist of each playlist 
			for(int j=0;j<playlists.get(i).getContent().size();j++){
				if(playlists.get(i).getContent().get(j).equals(songs.get(index-1))){ // if the song is in the playlist or not 
					playlists.get(i).deleteContent(index-1);  //if yet(true) then deleting the song from that playlist
				}
			}
		}
		if (index < 1 || index > songs.size()){ //checking if the index is valid for songs arraylist	
			errorMsg = "Song Not Found"; //if not then setting the errormsg and returning false 
			return false;
		}
		songs.remove(index-1);  //otherwise removing the song from songs list and returning true 
		return true;
	}
	
  	// Sort songs in library by year
	public void sortSongsByYear(){
		// Use Collections.sort()
		Collections.sort(songs,new SongYearComparator()); 
	
	}
	// The Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>{
		public int compare(Song a,Song b){  // Comparing to songs by year
			if(a.getYear()>b.getYear()) return 1;
			else if(a.getYear()<b.getYear()) return -1;
			else return 0;
		} 
	}

	// Sort songs by length
	public void sortSongsByLength(){
	 	Collections.sort(songs,new SongLengthComparator()); 
	}
	// Using the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>{
		public int compare(Song a,Song b){  //comparing to songs by length
			if(a.getLength()>b.getLength()) return 1;
			else if(a.getLength()<b.getLength()) return -1;
			else return 0;
		} 
	}

	// Sort songs by title 
	public void sortSongsByName(){
	  Collections.sort(songs);
		
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index){
		if (index < 1 || index > songs.size()) //checking if the index is valid for songs arraylist
		{
			throw new NotFound("Song Not Found");  //if not then setting the errormsg and returning false 
		}
		songs.get(index-1).play();  //playing the song on the given index after coverting it to 0s indexing
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter){
		if (index < 1 || index > audiobooks.size())   //checking if the given index is valid 
		{
			throw new NotFound("Audiobook Not Found");
		}
		audiobooks.get(index-1).selectChapter(chapter);   //otherwise setting the selectChapter with given chapter
		audiobooks.get(index-1).play();  //playing the given chapter from the audiobook at the given index
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index){
		if (index < 1 || index > audiobooks.size()) //checking if the given index is valid 
		{
			throw new NotFound("Audiobook Not Found");
		}
		audiobooks.get(index-1).printTOC();  //printing the chapter titles of the given audiobook
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Making a new playlist and add to playlists array list
	// Making sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title){
		Playlist newPlaylist = new Playlist(title); //making new playlist
		for(int i=0;i<playlists.size();i++){  //checking if the new playlist is already in the list of playlists
			if(playlists.get(i).getTitle().equals(newPlaylist.getTitle())){
				throw new AlreadyExists("Playlist "+title+" Already Exists");  //if true then setting the errormsg
			}
		}
		playlists.add(newPlaylist); //otherwise adding new playlist to the playlist list
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title){
		for(int i=0;i<playlists.size();i++){ //looping through playlists and finding the playlist with the given title 
			if(playlists.get(i).getTitle().equals(title)){
				playlists.get(i).printContents(); //if there is one playlist which matches then printing its contents
			}
		}
		throw new NotFound("Playlist Not Found");  //if there are no matching playlists then setting the errormsg 
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle){
		for(int i=0;i<playlists.size();i++){  //looping through playlists and finding the playlist with the given title 
			if(playlists.get(i).getTitle().equals(playlistTitle)){ 
				playlists.get(i).playAll();  //if there is one playlist which matches then playing its contents
			}
		}
		throw new NotFound("Playlist Not Found");
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL){
		for(int i=0;i<playlists.size();i++){  //looping through playlists and finding the playlist with the given title
			if(playlists.get(i).getTitle().equals(playlistTitle)){ 
				playlists.get(i).play(indexInPL); //playing the content on the given index
			}
		}
		throw new NotFound("Playlist Not Found");
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	
	//Using the type parameter to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle){
		for(int i=0;i<playlists.size();i++){  //looping through playlists and finding the playlist with the given title
			if(playlists.get(i).getTitle().equals(playlistTitle)){
				//checking the type of the content and adding it to the correct playlist 
				if(type.equalsIgnoreCase(Song.TYPENAME)){  
					playlists.get(i).addContent(songs.get(index-1));
				}
				if(type.equalsIgnoreCase(AudioBook.TYPENAME)){
					playlists.get(i).addContent(audiobooks.get(index-1));
				}
			}
		}
		throw new NotFound("Playlist Not Found");
	}

  	// Delete a song/audiobook/podcast from a playlist with the given title
	// Making sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title){
		for(int i=0;i<playlists.size();i++){  //looping through playlists and finding the playlist with the given title
			if(playlists.get(i).getTitle().equals(title)){
				if (index < 1 || index > playlists.get(i).getContent().size()){  //checking if the given index is valid
					throw new NotFound("Content Not Found"); //if the index is invalid then setting the errormsg 
				}
				playlists.get(i).deleteContent(index); //if the given index if valid then deleting the content from the given index
			}
		}
		throw new NotFound("Playlist Not Found");
	}

	public class AlreadyDownloaded extends RuntimeException{
		public AlreadyDownloaded() {}

		public AlreadyDownloaded(String message){
			super(message);
		}
	}
	
	public class AlreadyExists extends RuntimeException{
		public AlreadyExists() {}

		public AlreadyExists(String message){
			super(message);
		}
	}

	public class NotFound extends RuntimeException{
		public NotFound() {}

		public NotFound(String message){
			super(message);
		}
	}
}

