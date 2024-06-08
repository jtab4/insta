package insta.backend.repository;

import insta.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<User, Long> {

    List<User> findFirst5ByEmailNotOrderByIdDesc(String email);
}
