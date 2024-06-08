import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import insta.backend.model.User;
@RestController
public class FollowerController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/addFollower")
    public String addFollower(@RequestBody AddFollowerRequest request) {
        // Tworzymy obiekt Followers
        Followers followers = new Followers();

        // Ustawiamy użytkownika, którego ma obserwować obserwujący
        User visitedUser = entityManager.find(User.class, request.getVisitedUserId());
        followers.setUser(visitedUser);

        // Ustawiamy obserwującego
        User follower = entityManager.find(User.class, request.getVisitorId());
        followers.setFollower(follower);

        // Zapisujemy obiekt Followers do bazy danych
        entityManager.persist(followers);

        return "Follower added successfully";
    }
}
