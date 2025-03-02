package Arthub.service;

import Arthub.entity.Follow;

import java.sql.SQLException;

public interface FollowService {
        void getFollowing(Follow follow) throws SQLException;

        void DeleteFollow(int followerId, int followingId) throws SQLException;
}
