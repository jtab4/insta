package insta.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import insta.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
