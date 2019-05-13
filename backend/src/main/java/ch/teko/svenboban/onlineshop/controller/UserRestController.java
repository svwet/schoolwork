package ch.teko.svenboban.onlineshop.controller;

import ch.teko.svenboban.onlineshop.model.User;
import ch.teko.svenboban.onlineshop.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Petrovic Boban
 **/
@RestController
public class UserRestController {

    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{pid}")
    public User getUserByPid(@PathVariable String pid) {
        return userRepository.findUserByPid(pid);
    }

}
