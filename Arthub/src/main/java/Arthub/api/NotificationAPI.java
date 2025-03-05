package Arthub.api;

import Arthub.entity.Notification;
import Arthub.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationAPI {


    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{FollowerID}/{FollowingID}")
    public ResponseEntity<Notification> findByNotificationByFollowerIDAndFollowingID(
            @PathVariable("FollowerID") int followerID,
            @PathVariable("FollowingID") int followingID) {
        Notification notification = notificationService.findByNotificationByFollowerIDAndFollowingID(followerID, followingID);

        if (notification == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(notification);
        }
    }


}