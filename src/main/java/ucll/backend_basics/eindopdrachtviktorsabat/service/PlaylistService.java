package ucll.backend_basics.eindopdrachtviktorsabat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.backend_basics.eindopdrachtviktorsabat.model.Playlist;
import ucll.backend_basics.eindopdrachtviktorsabat.model.Song;
import ucll.backend_basics.eindopdrachtviktorsabat.model.User;
import ucll.backend_basics.eindopdrachtviktorsabat.repository.PlaylistRepository;
import ucll.backend_basics.eindopdrachtviktorsabat.repository.SongRepository;
import ucll.backend_basics.eindopdrachtviktorsabat.repository.UserRepository;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;
    private UserRepository userRepository;

    private SongRepository songRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public User createPlaylist(Long userId, Playlist playlist) {
        // Ensure playlist name is unique for the user
        if (playlistRepository.existsByNameAndUserId(playlist.getName(), userId)) {
            throw new IllegalArgumentException("Playlist name already exists for the user");
        }

        // Fetch the user from the repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        user.addPlaylist(playlist);

        this.playlistRepository.save(playlist);

        return this.userRepository.save(user);
    }

    public Playlist addExistingSongToPlaylist(Long playlistId, Song existingSong){
        if(!playlistRepository.existsById(playlistId))
            throw new IllegalArgumentException("Playlist doe not exist");

        if(!songRepository.existsById(existingSong.getId()))
            throw new IllegalArgumentException("Song does not exist");

        // If playlist does exist we can check if it is perhaps full
        Playlist playlistToBeExpanded = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));

        playlistToBeExpanded.addSong(existingSong);
        existingSong.addPlaylist(playlistToBeExpanded);

        songRepository.save(existingSong);
        return playlistRepository.save(playlistToBeExpanded);
    }

    public void removeSongFromPlaylist(Long userId, Long playlistId, Long songId) {
        // Fetch the user to check if it exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Fetch the playlist to check if it exists and belongs to the user
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found with ID: " + playlistId + " for user ID: " + userId));

        // Fetch the song to check if it exists
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("Song not found with ID: " + songId));

        // Check if the song is in the playlist
        if (!playlist.getSongs().contains(song)) {
            throw new IllegalArgumentException("Song with ID: " + songId + " is not in the playlist with ID: " + playlistId);
        }

        // Remove the song from the playlist
        playlist.removeSong(song);

        // Save the updated playlist
        playlistRepository.save(playlist);
    }
}
