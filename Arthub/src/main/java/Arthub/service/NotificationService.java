package Arthub.service;

import Arthub.entity.Notification;

public interface NotificationService {
    Notification findByNotificationByFollowerIDAndFollowingID(int followerID, int followingID);
}
