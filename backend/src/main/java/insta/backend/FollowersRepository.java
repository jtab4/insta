package insta.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import insta.backend.model.Followers;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {
    boolean existsByUserIdAndFollowerId(Long userId, Long followerId);
    Long countByUserId(Long userId);
}
