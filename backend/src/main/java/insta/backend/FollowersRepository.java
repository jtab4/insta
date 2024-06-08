package insta.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import insta.backend.model.Followers;
import java.util.List;
@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {
    boolean existsByUserIdAndFollowerId(Long userId, Long followerId);
    Long countByUserId(Long userId);
    List<Followers> findByUserId(Long userId);
}
