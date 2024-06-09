package insta.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import insta.backend.model.Post;
import insta.backend.model.User;
import insta.backend.repository.PostRepository;
import insta.backend.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/add-post/{userId}")
    public ResponseEntity<Post> addPostForUser(@PathVariable Long userId, @RequestBody Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/user-posts/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Post> userPosts = postRepository.findByUser(user);
        return new ResponseEntity<>(userPosts, HttpStatus.OK);
    }
    @PostMapping("/add-like/{postId}")
    public ResponseEntity<Post> addLikeToPost(@PathVariable Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setLikes(post.getLikes() + 1); 
        Post updatedPost = postRepository.save(post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
}
}
