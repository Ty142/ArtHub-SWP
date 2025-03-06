package Arthub.api;

import Arthub.entity.Notification;
import Arthub.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("{userId}")
    public ResponseEntity<List<Notification>> getNotificationsOfTheUserFromUserId(@PathVariable("userId") int userId) {
        try {
        List<Notification> notifications = notificationService.getNotificationsOfTheUserFromUserId(userId);
        if (notifications.isEmpty()) {
            return ResponseEntity.notFound().build();

        } else {
            return ResponseEntity.ok(notifications);
        }} catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{userId}")
    public ResponseEntity<Notification> makeReadNotificationByUserId(@PathVariable("userId") int userId) {
        try {
            notificationService.readNotificationByUserId(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}