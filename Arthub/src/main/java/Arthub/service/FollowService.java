package Arthub.service;

import Arthub.entity.Follow;
import Arthub.entity.User;

import java.sql.SQLException;

public interface FollowService {
        void getFollowing(Follow follow) throws SQLException;

        void DeleteFollow(int followerId, int followingId) throws SQLException;

        User getFollowingUserFromFollowID(int followerId) throws SQLException;
}
