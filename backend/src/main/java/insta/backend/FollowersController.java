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
import insta.backend.repository.NotificationRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.Optional;
import insta.backend.model.Notification;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;


@RestController
@RequestMapping("/followers")
public class FollowersController {

    @Autowired
    private FollowersRepository followersRepository;

    @Autowired
    private FollowingRepository followingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/add")
public ResponseEntity<String> addFollower(@RequestBody Followers newFollower) {

    Long userId = newFollower.getUser().getId();
    Long followerId = newFollower.getFollower().getId();

    boolean userExists = userRepository.existsById(userId);
    boolean followerExists = userRepository.existsById(followerId);

    if (!userExists || !followerExists) {
        return ResponseEntity.status(404).body("User or follower not found");
    }

    
    followersRepository.save(newFollower);

    
    User followerUser = userRepository.findById(followerId).orElse(null);
    String followerName = followerUser != null ? followerUser.getName() : "Unknown User";

    
    String notificationText = "Nowy obserwujący: " + followerName;

    
    Notification newNotification = new Notification();
    newNotification.setText(notificationText);
    newNotification.setData(new Date());
    newNotification.setUser(newFollower.getUser());
    notificationRepository.save(newNotification);

    
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
    @PostMapping("/remove")
    public ResponseEntity<String> removeFollower(@RequestBody Followers followerData) {
        Long userId = followerData.getUser().getId();
        Long followerId = followerData.getFollower().getId();

        Optional<Followers> followerOptional = followersRepository.findByUserIdAndFollowerId(userId, followerId);

        if (followerOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Follower not found");
        }

        followersRepository.delete(followerOptional.get());
        
        
        Optional<Following> followingOptional = followingRepository.findByUserIdAndFollowingId(followerId, userId);
        if (followingOptional.isPresent()) {
            followingRepository.delete(followingOptional.get());
        }

        return ResponseEntity.ok("Follower removed successfully");
}
}
