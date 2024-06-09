package insta.backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import insta.backend.model.Post;
import insta.backend.repository.PostRepository;
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/add-post/{userId}")
    public ResponseEntity<Post> addPostForUser(@PathVariable Long userId, @RequestBody Post post) {
        
        Post savedPost = postRepository.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
}
