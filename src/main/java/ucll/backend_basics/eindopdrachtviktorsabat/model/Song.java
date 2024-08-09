package ucll.backend_basics.eindopdrachtviktorsabat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Represents a song entity with attributes such as id, title, artist, album, length, and genre.
 */
 @Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;
    private String album;
    private int length; // length in seconds
    private String genre;

    // There is a many-to-many relationship between Song and Playlist,
    // but the owning side of this relationship is in the Playlist class.
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;


    /********************************************
     * Constructors
     ********************************************/
    public Song(String title, String artist, String album, int length, String genre) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.length = length;
        this.genre = genre;
        this.playlists = new ArrayList<>();
    }

    public Song() { this.playlists = new ArrayList<>();}


    /********************************************
     * Getters
     ********************************************/
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getLength() {
        return length;
    }

    public String getGenre() {
        return genre;
    }


    /********************************************
     * Setters
     ********************************************/
    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

}
