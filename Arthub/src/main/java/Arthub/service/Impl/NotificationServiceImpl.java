package Arthub.service.Impl;

import Arthub.entity.Notification;
import Arthub.repository.NotificationRepository;
import Arthub.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Notification findByNotificationByFollowerIDAndFollowingID(int followerID, int followingID) {
        return notificationRepository.findByNotificationByFollowerIDAndFollowingID(followerID, followingID);
    }

    @Override
    public List<Notification> getNotificationsOfTheUserFromUserId(int userId) {
        return notificationRepository.getNotificationsOfTheUserFromUserId(userId);
    }

    @Override
    public void readNotificationByUserId(int userId) {
        notificationRepository.readNotificationByUserId(userId);
    }

}
