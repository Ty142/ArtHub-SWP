package Arthub.repository;

import Arthub.entity.Notification;

public interface NotificationRepository {
    boolean save(Notification notification);
    Notification findByNotificationByFollowerIDAndFollowingID(int followerID, int followingID);

}
