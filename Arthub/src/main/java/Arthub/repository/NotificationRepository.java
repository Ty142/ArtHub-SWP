package Arthub.repository;

import Arthub.entity.Notification;

import java.util.List;

public interface NotificationRepository {
    boolean save(Notification notification);
    Notification findByNotificationByFollowerIDAndFollowingID(int followerID, int followingID);
    List<Notification> getNotificationsOfTheUserFromUserId(int userId);
    void readNotificationByUserId(int userId);

}
