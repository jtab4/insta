package insta.backend.controller;

import insta.backend.model.User;
import insta.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findProfile/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        
        if (user != null) {
            

            User responseUser = new User();
            responseUser.setId(user.getId());
            responseUser.setName(user.getName());
            responseUser.setEmail(user.getEmail());
            responseUser.setPassword(null);

            return ResponseEntity.ok(responseUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
