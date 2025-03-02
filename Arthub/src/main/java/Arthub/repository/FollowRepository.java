package Arthub.repository;

import Arthub.entity.Follow;

import java.sql.SQLException;

public interface FollowRepository {
    void updateFollowing(Follow follow);

    void deleteFollow(int FollowerID, int FollowingID);

     boolean checkFollowExists(int followerId, int followingId);

     int getFollowCountsByFollowerId(int followerID);

    int getFollowerCountsByFollowingId(int followingID) throws SQLException;

    void updateFollowCountOfFollowerByFollowerId( int followerID);

    void updateFollowerCountOfFollowingByFollowingId(int followingID) throws SQLException;

    void deleteFollowerCountOfFollowerByFollowingId(int followingID) throws SQLException;

    void deleteFollowCountsOfFollowingByFollowerId(int followerID);
}
