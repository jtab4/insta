package insta.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import insta.backend.model.Post;
import insta.backend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
    @Query("SELECT p FROM Post p WHERE p.user.id IN :userIds")
    List<Post> findTop10ByUserIds(@Param("userIds") List<Long> userIds);
}
