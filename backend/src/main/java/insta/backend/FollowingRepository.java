package insta.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import insta.backend.model.Following;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {
    Long countByUserId(Long userId);
}
