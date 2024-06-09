package insta.backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import insta.backend.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findTop4ByUserIdOrderByDataDesc(Long userId);
}
