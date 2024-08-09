package ucll.backend_basics.eindopdrachtviktorsabat.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Represents a playlist entity with attributes such as id, name, maxSongs, and user.
 */
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int maxSongs;

    // Specifies the column in the database table (user_id) that maps this relationship. It's marked as nullable = false, meaning it's required.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    //Specifies the details of the join table (playlist_song) that connects Playlist and Song.
    @JoinTable(
            name = "playlist_song",
            // Defines the column in the playlist_song table that references the primary key of the Playlist entity (playlist_id).
            joinColumns = @JoinColumn(name = "playlist_id"),
            // Defines the column in the playlist_song table that references the primary key of the Song entity (song_id).
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs;


    /********************************************
     * Constructors
     ********************************************/
    public Playlist(String name, int maxSongs, User user) {
        this.name = name;
        this.maxSongs = maxSongs;
        this.user = user;
        this.songs = new ArrayList<>();
    }

    public Playlist() { this.songs = new ArrayList<>();}


    /********************************************
     * Getters
     ********************************************/
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxSongs() {
        return maxSongs;
    }

    public User getUser() {
        return user;
    }

    public List<Song> getSongs() {
        return songs;
    }


    /********************************************
     * Setters
     ********************************************/

    public void addSong(Song song) {
        if (this.songs.size() >= this.maxSongs) {
            throw new IllegalArgumentException("Playlist is full");
        }
        this.songs.add(song);

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void removeSong(Song song){
        this.songs.remove(song);
    }
}
