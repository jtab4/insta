package insta.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import insta.backend.model.User;
import insta.backend.repository.UserRepository;
import insta.backend.util.HashingUtil;

@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser) {
        
        User existingUser = userRepository.findByEmail(loginUser.getEmail());
        if (existingUser == null) {
            return ResponseEntity.status(401).body("User not found");
        }

        
        String hashedPassword = HashingUtil.hashPassword(loginUser.getPassword());

        
        if (hashedPassword.equals(existingUser.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid password");
        }
    }
}
