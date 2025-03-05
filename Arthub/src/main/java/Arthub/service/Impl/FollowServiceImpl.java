package Arthub.service.Impl;

import Arthub.entity.Follow;
import Arthub.event.UserInteractionEvent;
import Arthub.repository.FollowRepository;
import Arthub.service.FollowService;
import Arthub.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class FollowServiceImpl implements FollowService{

    @Autowired
    private FollowRepository followRepository;


    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void getFollowing(Follow follow) throws SQLException {
         followRepository.updateFollowing(follow);
         followRepository.updateFollowCountOfFollowerByFollowerId(follow.getFollowerId());
         followRepository.updateFollowerCountOfFollowingByFollowingId(follow.getFollowingId());


         UserInteractionEvent event = new UserInteractionEvent(this,
                                            notificationService.findByNotificationByFollowerIDAndFollowingID
                                                    (follow.getFollowerId(),follow.getFollowingId()));
         eventPublisher.publishEvent(event);

    }

    @Override
    public void DeleteFollow(int followerId, int followingId) throws SQLException {
        followRepository.deleteFollow(followerId,followingId);
        followRepository.deleteFollowerCountOfFollowerByFollowingId(followingId);
        followRepository.deleteFollowCountsOfFollowingByFollowerId(followerId);
    }
}
