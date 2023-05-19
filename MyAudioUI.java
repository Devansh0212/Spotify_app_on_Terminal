//Name: Devansh Brahmbhatt

import java.util.*;
// Simulation of a Simple Text-based Music App (like Apple Music,spotify)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try{
				String action = scanner.nextLine();

				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all songs
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD")) 
				{
					int FromIndex = 0; // initializing FromIndex to zero 
					int ToIndex = 0;// initializing ToIndex to zero 
					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt())
					{
						FromIndex = scanner.nextInt();
					}

					System.out.print("To Store Content #: ");
					if (scanner.hasNextInt())
					{
						ToIndex = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					
					for(int i=FromIndex;i<=ToIndex;i++){
						AudioContent content = store.getContent(i);
						try{
								mylibrary.download(content);
						}catch(RuntimeException exception){
							System.out.println(exception.getMessage());
						}
					}
				}
				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{
					System.out.print("Song Number: ");
					int index = scanner.nextInt();
					mylibrary.playSong(index);
				}
				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
					System.out.print("Audio Book Number: ");
					int index = scanner.nextInt();
					mylibrary.printAudioBookTOC(index);
				}
				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					System.out.print("Audio Book Number: ");
					int index = scanner.nextInt(); 

					System.out.print("Chapter: ");
					int chapter = scanner.nextInt();
					mylibrary.playAudioBook(index,chapter);
				}
				// Specify a playlist title (string) 
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					System.out.print("Playlist Title: ");
					String title = scanner.next();
					mylibrary.playPlaylist(title);
				}
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook/podcast in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					System.out.print("Playlist Title: ");
					String title = scanner.next();
					
					System.out.print("Content Number: ");
					int index = scanner.nextInt();
					mylibrary.playPlaylist(title,index);
				}
				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					System.out.print("Library Song #: ");
					int index = scanner.nextInt();
					mylibrary.deleteSong(index);
				}
				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					System.out.print("Playlist Title: ");
					String title = scanner.next();
					mylibrary.makePlaylist(title);
				}
				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					System.out.print("Playlist Title: ");
					String title = scanner.next();
					mylibrary.printPlaylist(title);
				}
				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					System.out.print("Playlist Title: ");
					String title = scanner.next();

					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					String type = scanner.next();

					System.out.print("Library Content #: ");
					int index = scanner.nextInt();
					mylibrary.addContentToPlaylist(type,index,title);
				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					System.out.print("Playlist Title: ");
					String title = scanner.next();

					System.out.print("Playlist Content #: ");
					int index = scanner.nextInt();
					mylibrary.delContentFromPlaylist(index,title);
				}
				
				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}
				else if(action.equalsIgnoreCase("SEARCH"))
				{
					System.out.print("Title: ");
					String title = scanner.nextLine();
					store.search(title);
				}

				else if(action.equalsIgnoreCase("SEARCHA"))
				{
					System.out.print("Artist: ");
					String artists = scanner.nextLine();
					ArrayList<Integer> index = store.searchA(artists);

					for(int i=0;i<index.size();i++){
						System.out.print(index.get(i)+1 + ". ");
						store.getContent(index.get(i)+1).printInfo();
						System.out.println("\n");
					}
				}

				else if(action.equalsIgnoreCase("SEARCHG"))
				{
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					String genre = scanner.nextLine();
					ArrayList<Integer> index = store.searchG(Song.Genre.valueOf(genre));

					for(int i=0;i<index.size();i++){
						System.out.print(index.get(i)+1 + ". ");
						store.getContent(index.get(i)+1).printInfo();
						System.out.println("\n");
					}
				}

				else if(action.equalsIgnoreCase("DOWNLOADA"))
				{
					System.out.print("Artist Name: ");
					String artist = scanner.nextLine();

					ArrayList<Integer> list_1 = store.searchA(artist);
							
					for(int i : list_1){
						AudioContent FromStoreContent = store.getContent(i+1);
						try{
							mylibrary.downloadAG(FromStoreContent);
						}catch(RuntimeException exception){
							System.out.println(exception.getMessage());
						}
					}
				}

				else if(action.equalsIgnoreCase("DOWNLOADG"))
				{
					System.out.print("Genre: ");
					String genre = scanner.nextLine();

					ArrayList<Integer> list_2 = store.searchG(Song.Genre.valueOf(genre));
							
					for(int i : list_2){
						AudioContent FromStoreContent_2 = store.getContent(i+1);
						try{
							mylibrary.downloadAG(FromStoreContent_2);
						}catch(RuntimeException exception){
							System.out.println(exception.getMessage());
						}
					}
				}

				else if(action.equalsIgnoreCase("SEARCHP")){
					System.out.print("Search for: ");
					String partial = scanner.nextLine();

					ArrayList<Integer> index = store.searchP(partial);

					for(int i=0;i<index.size();i++){
						System.out.print(index.get(i)+1 + ". ");
						store.getContent(index.get(i)+1).printInfo();
						System.out.println("\n");
					}
				}
			} 
			catch(RuntimeException exception){
				System.out.println(exception.getMessage());
			}
			System.out.print("\n>");
			scanner.close();
		}
	}
}
