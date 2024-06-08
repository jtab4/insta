package insta.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import insta.backend.model.Followers;
import insta.backend.model.Following;
import insta.backend.model.User;
import insta.backend.repository.FollowersRepository;
import insta.backend.repository.FollowingRepository;
import insta.backend.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/followers")
public class FollowersController {

    @Autowired
    private FollowersRepository followersRepository;

    @Autowired
    private FollowingRepository followingRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addFollower(@RequestBody Followers newFollower) {

        Long userId = newFollower.getUser().getId();
        Long followerId = newFollower.getFollower().getId();

        boolean userExists = userRepository.existsById(userId);
        boolean followerExists = userRepository.existsById(followerId);

        if (!userExists || !followerExists) {
            return ResponseEntity.status(404).body("User or follower not found");
        }

        // Zapisz nowego followersa
        followersRepository.save(newFollower);

        
        Following following = new Following();
        following.setUser(newFollower.getFollower());
        following.setFollowing(newFollower.getUser());
        followingRepository.save(following);

        return ResponseEntity.ok("Follower added successfully");
    }

    
    @GetMapping("/count/{userId}")
    public ResponseEntity<Long> countFollowersForUser(@PathVariable Long userId) {
        Long followersCount = followersRepository.countByUserId(userId);
        return ResponseEntity.ok(followersCount);
    }

    
    @GetMapping("/count-following/{userId}")
    public ResponseEntity<Long> countFollowingForUser(@PathVariable Long userId) {
        Long followingCount = followingRepository.countByUserId(userId);
        return ResponseEntity.ok(followingCount);
    }

    
    @GetMapping("/followers-list/{userId}")
    public ResponseEntity<List<User>> getFollowersForUser(@PathVariable Long userId) {
        List<Followers> followers = followersRepository.findByUserId(userId);
        List<User> followerUsers = followers.stream()
                .map(Followers::getFollower)
                .collect(Collectors.toList());
        return ResponseEntity.ok(followerUsers);
    }

    
    @GetMapping("/following-list/{userId}")
    public ResponseEntity<List<User>> getFollowingForUser(@PathVariable Long userId) {
        List<Following> following = followingRepository.findByUserId(userId);
        List<User> followingUsers = following.stream()
                .map(Following::getFollowing)
                .collect(Collectors.toList());
        return ResponseEntity.ok(followingUsers);
    }
}
