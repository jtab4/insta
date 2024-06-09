package insta.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import insta.backend.model.Following;
import java.util.List;
import java.util.Optional;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {
    Long countByUserId(Long userId);
    List<Following> findByUserId(Long userId);
    Optional<Following> findByUserIdAndFollowingId(Long userId, Long followingId); 
}
