package insta.backend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import insta.backend.model.Notification;
import insta.backend.repository.NotificationRepository;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        
        List<Notification> userNotifications = notificationRepository.findTop4ByUserIdOrderByDataDesc(userId);
        return ResponseEntity.ok(userNotifications);
    }
}
