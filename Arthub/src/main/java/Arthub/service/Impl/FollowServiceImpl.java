package Arthub.service.Impl;

import Arthub.entity.Follow;
import Arthub.repository.FollowRepository;
import Arthub.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class FollowServiceImpl implements FollowService{

    @Autowired
    private FollowRepository followRepository;

    @Override
    public void getFollowing(Follow follow) throws SQLException {
         followRepository.updateFollowing(follow);
         followRepository.updateFollowCountOfFollowerByFollowerId(follow.getFollowerId());
         followRepository.updateFollowerCountOfFollowingByFollowingId(follow.getFollowingId());

    }

    @Override
    public void DeleteFollow(int followerId, int followingId) throws SQLException {
        followRepository.deleteFollow(followerId,followingId);
        followRepository.deleteFollowerCountOfFollowerByFollowingId(followingId);
        followRepository.deleteFollowCountsOfFollowingByFollowerId(followerId);
    }
}
