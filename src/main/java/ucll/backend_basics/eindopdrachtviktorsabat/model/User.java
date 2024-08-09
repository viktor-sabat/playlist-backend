package ucll.backend_basics.eindopdrachtviktorsabat.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a user entity with attributes such as id, firstName, lastName, and email.
 */
@Entity
// potentially conflicts with reserved keywords in sql databases 'user'
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    // Cascade is used to make sure that playlists get deleted when the user (owner) is being deleted.
    // Specifies that the user attribute in the Playlist class owns (maps) this relationship.
    // If a playlist gets deleted from the list of user's playlists it shall also be deleted from the table Playlists in the DB.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Playlist> playlists = new ArrayList<>();


    /********************************************
     * Constructors
     ********************************************/
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User() { }


    /********************************************
     * Getters
     ********************************************/
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }


    /********************************************
     * Setters
     ********************************************/
    public void addPlaylist(Playlist playlist) {
        if (this.playlists == null) {
            this.playlists = new ArrayList<>();
        }
        playlist.setUser(this); // Ensure the playlist is linked to this user
        this.playlists.add(playlist);
    }
}

