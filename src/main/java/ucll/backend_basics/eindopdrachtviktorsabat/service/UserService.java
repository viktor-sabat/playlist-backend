package ucll.backend_basics.eindopdrachtviktorsabat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.backend_basics.eindopdrachtviktorsabat.model.User;
import ucll.backend_basics.eindopdrachtviktorsabat.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

    }

    public void deleteUserByid(Long id){
        userRepository.deleteById(id);
    }


}

