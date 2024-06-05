package insta.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import insta.backend.model.User;
import insta.backend.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User added successfully");
    }
}
