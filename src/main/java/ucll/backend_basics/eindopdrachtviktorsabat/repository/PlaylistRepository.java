package ucll.backend_basics.eindopdrachtviktorsabat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucll.backend_basics.eindopdrachtviktorsabat.model.Playlist;

import java.util.Optional;


public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    boolean existsByNameAndUserId(String name, Long userId);

    boolean existsById(Long playlistId);

    Optional<Playlist> findByIdAndUserId(Long playlistId, Long userId);
}
