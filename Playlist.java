//Name: Devansh Brahmbhatt
import java.util.ArrayList;
/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Printing the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Printing the index of the audio content object first
	 * followed by ". " then using the printInfo() method of each audio content object to 
	 * make sure the index starts at 1 
	 */
	public void printContents()
	{
		for(int i=0;i<contents.size();i++){ //looping through contents list
			System.out.print((i+1) +". ");
			contents.get(i).printInfo(); //using the printInfo() method of each audio content object
			System.out.println("\n");
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		for(int i=0;i<contents.size();i++){ //looping through contents list
			contents.get(i).play();  //using the play() method of each audio content object
			System.out.println("\n");
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		if(contains(index)){ 
			System.out.println(this.getTitle());
			contents.get(index-1).play(); //playing the content on the given index
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		Playlist good = (Playlist)other;
		return this.title.equals(good.title); 
	}
	
	// Given an index of an audio content object in contents array list,
	// removing the audio content object from the array list
	// We use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)) return;
		contents.remove(index-1);
	}
	
	
}
