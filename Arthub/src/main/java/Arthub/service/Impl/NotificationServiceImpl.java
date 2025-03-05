package Arthub.service.Impl;

import Arthub.entity.Notification;
import Arthub.repository.NotificationRepository;
import Arthub.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Notification findByNotificationByFollowerIDAndFollowingID(int followerID, int followingID) {
        return notificationRepository.findByNotificationByFollowerIDAndFollowingID(followerID, followingID);
    }

}
