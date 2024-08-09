package ucll.backend_basics.eindopdrachtviktorsabat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ucll.backend_basics.eindopdrachtviktorsabat.model.User;
import ucll.backend_basics.eindopdrachtviktorsabat.service.UserService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){ this.userService = userService; }

    @PostMapping
    public User createUser(@RequestBody User user){ return this.userService.createUser(user); }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserByid(id);
    }
}
