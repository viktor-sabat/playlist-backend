package ucll.backend_basics.eindopdrachtviktorsabat.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import ucll.backend_basics.eindopdrachtviktorsabat.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(Long id);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    List<User> findAll();
}
