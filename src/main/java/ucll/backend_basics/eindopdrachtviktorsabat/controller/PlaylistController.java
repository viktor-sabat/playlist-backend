package ucll.backend_basics.eindopdrachtviktorsabat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ucll.backend_basics.eindopdrachtviktorsabat.model.Playlist;
import ucll.backend_basics.eindopdrachtviktorsabat.model.Song;
import ucll.backend_basics.eindopdrachtviktorsabat.model.User;
import ucll.backend_basics.eindopdrachtviktorsabat.service.PlaylistService;

@RestController
// Specifies the base URL for all the endpoints in this controller.
@RequestMapping("/api/v1/user")
public class PlaylistController {
    private PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService){ this.playlistService = playlistService; }

    @PostMapping("/{id}/playlist")
    public User createPlaylistforUser(@PathVariable Long id, @RequestBody Playlist playlist){
        return playlistService.createPlaylist(id, playlist);
    }

    @PostMapping("/{userId}/playlist/{playlistId}/song")
    public Playlist addExistingSongToPlaylist(@PathVariable Long userId, @PathVariable Long playlistId, @RequestBody Song existingSong){
        return playlistService.addExistingSongToPlaylist(playlistId, existingSong);
    }

    @DeleteMapping("/{userId}/playlist/{playlistId}/song/{songId}")
    public void deleteExistingSongFromPlaylist(@PathVariable Long userId, @PathVariable Long playlistId, @PathVariable Long songId){
        this.playlistService.removeSongFromPlaylist(userId, playlistId, songId);
    }
}
