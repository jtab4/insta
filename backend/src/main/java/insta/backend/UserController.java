package insta.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import insta.backend.model.User;
import insta.backend.repository.UserRepository;
import insta.backend.util.HashingUtil;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(409).body("User with this email already exists");
        }

        
        String hashedPassword = HashingUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);
        return ResponseEntity.ok("User added successfully");
    }
}
