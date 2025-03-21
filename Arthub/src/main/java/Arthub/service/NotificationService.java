package Arthub.service;

import Arthub.entity.Notification;
import Arthub.entity.Withdraw;

import java.util.List;

public interface NotificationService {
    Notification findByNotificationByFollowerIDAndFollowingID(int followerID, int followingID);
    List<Notification> getNotificationsOfTheUserFromUserId(int userId);
    void readNotificationByUserId(int userId);

}
