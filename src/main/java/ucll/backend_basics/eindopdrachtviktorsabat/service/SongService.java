package ucll.backend_basics.eindopdrachtviktorsabat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.backend_basics.eindopdrachtviktorsabat.model.Song;
import ucll.backend_basics.eindopdrachtviktorsabat.repository.SongRepository;

import java.util.List;

// Indicates that the class is a service component in the Spring context.
@Service
public class SongService {

    private SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    public Song updateSong(Long id, Song updatedSong) {
        Song existingSong = songRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Song not found with id: " + id));

        // Update song data
        existingSong.setTitle(updatedSong.getTitle());
        existingSong.setArtist(updatedSong.getArtist());
        existingSong.setAlbum(updatedSong.getAlbum());
        existingSong.setLength(updatedSong.getLength());
        existingSong.setGenre(updatedSong.getGenre());

        return songRepository.save(existingSong);
    }

    public List<Song> getSongsByAlbum(String album) {
        return songRepository.findByAlbum(album);
    }

    public List<Song> getSongsByLengthRange(Integer minLength, Integer maxLength) {
        if (minLength == null && maxLength == null) {
            throw new IllegalArgumentException("Both minLength and maxLength cannot be null");
        }
        if (minLength != null && maxLength != null && minLength > maxLength) {
            throw new IllegalArgumentException("minLength should be less than or equal to maxLength");
        }

        if (minLength != null && maxLength == null) {
            return songRepository.findByLengthGreaterThanEqual(minLength);
        }
        if (minLength == null && maxLength != null) {
            return songRepository.findByLengthLessThanEqual(maxLength);
        }
        return songRepository.findByLengthBetween(minLength, maxLength);
    }
}

