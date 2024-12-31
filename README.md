# Music and Library management system

MyAudio is an audio content management application that allows users to organize and manage their songs, audiobooks, podcasts, and playlists. This README provides an overview of the program's structure, functionality, and recent modifications based on the assignment requirements.

## Program Structure

The program is organized into several classes, each serving a specific purpose:

1. **Library:**
   - Contains logic for managing audio content (songs, audiobooks, podcasts, playlists).
   - Methods for adding, removing, and manipulating audio content.

2. **MyAudioUI:**
   - Main class with the user interface for interacting with the application.
   - Reads user actions and invokes corresponding methods in Library and AudioContentStore classes.

3. **AudioContent:**
   - Superclass for various audio content types (e.g., Song, Audiobook).
   - Contains common instance variables and methods for audio content.

4. **AudioContentStore:**
   - Simulates an online store for audio content.
   - Predefined audio content and methods for listing store contents and downloading content to the library.

5. **Song:**
   - Subclass of AudioContent specific to songs.
   - Includes artist name, composer, genre, and lyrics.

6. **Audiobook:**
   - Subclass of AudioContent specific to audiobooks.
   - Includes author, narrator, chapters, and chapter contents.

7. **Playlist:**
   - Manages playlists and associated audio content.
   - Methods to create, modify, and play playlists.

8. **Podcast:**
   - Bonus subclass of AudioContent for podcasts.
   - Includes host, seasons, and episode details.

## Functionality

The program offers the following functionalities:

- Manage audio content in a library.
- Interact with an online store to view and download audio content.
- Create, modify, and play playlists.
- Search for audio content by title, artist, genre, or partial matches.
- Play specific songs, audiobooks, or podcast episodes.

## Recent Modifications and Additions

1. **Enhanced Search Functionality:**
   - Added actions: SEARCH, SEARCHA, SEARCHG to search for audio content by title, artist, or genre.
   - Improved search capabilities using maps for efficient search operations.

2. **Flexible Download:**
   - Modified the DOWNLOAD action to support downloading a range of songs/books from the store.
   - Added DOWNLOADA action to download all audio content by a specific artist.
   - Added DOWNLOADG action to download all songs of a specific genre.

3. **Exception Handling:**
   - Replaced error message handling with custom exceptions for better error management.
   - Catch exceptions in the MyAudioUI class and display appropriate error messages to the user.

4. **File I/O:**
   - Implemented file reading functionality in AudioContentStore to populate the store with audio content from a file ("store.txt").

5. **Map Usage:**
   - Utilized maps to enhance search capabilities based on title, artist, and genre.

6. **Bonus Functionality:**
   - Introduced SEARCHP action for partial matches in audio content.

## Usage

To run the MyAudio app:
1. Compile the Java files.
   (javac MyAudioUI.java Library.java AudioContent.java AudioContentStore.java)
2. Run the MyAudioUI class.
   (java MyAudioUI)
3. Follow the on-screen prompts to interact with the application and utilize its features.


