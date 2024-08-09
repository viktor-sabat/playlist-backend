package ucll.backend_basics.eindopdrachtviktorsabat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ucll.backend_basics.eindopdrachtviktorsabat.model.Song;

import java.util.List;


public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByAlbum(String album);
    List<Song> findByLengthGreaterThanEqual(Integer minLength);
    List<Song> findByLengthLessThanEqual(Integer maxLength);
    List<Song> findByLengthBetween(Integer minLength, Integer maxLength);
}