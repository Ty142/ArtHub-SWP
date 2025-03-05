package Arthub.repository.impl;

import Arthub.entity.Follow;
import Arthub.repository.FollowRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;

@Repository
public class FollowRepositoryImpl implements FollowRepository{
    @Override
    public void updateFollowing(Follow follow) {
        String procedureCall = "{call UpdateOrInsertFollowing(?, ?, ?)}";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn =  db.openConection();
            CallableStatement cs = conn.prepareCall(procedureCall);
            cs.setInt(1, follow.getFollowerId());
            cs.setInt(2, follow.getFollowingId());
            LocalDate localDate = follow.getDateFollow();
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
            cs.setDate(3, sqlDate);
            cs.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFollow(int FollowerID, int FollowingID) {
        String sql = "UPDATE Following SET Status = 0 WHERE FollowerID = ? AND FollowingID = ?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn =  db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, FollowerID);
            ps.setInt(2, FollowingID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkFollowExists(int followerId, int followingId) {
        String sql = "SELECT COUNT(*) FROM Following WHERE followerID = ? AND followingID = ? AND Status = 1";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, followerId);
            ps.setInt(2, followingId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            rs.close();
            ps.close();
            conn.close();
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error checking follow status: " + e.getMessage(), e);
        }
    }

    @Override
    public int getFollowCountsByFollowerId(int followerID) {
        String sql = "SELECT u.FollowCounts From [User] u where u.UserId = ?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, followerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int getFollowerCountsByFollowingId(int followingID) throws SQLException {
        String sql = "SELECT u.FollowerCount From [User] u where u.UserId = ?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, followingID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    @Override
    public void updateFollowCountOfFollowerByFollowerId(int followerID) {
        String sql = "UPDATE [User] set FollowCounts = ? where UserId = ?";
        int followCounts = getFollowCountsByFollowerId(followerID);
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, followCounts + 1);
            ps.setInt(2, followerID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateFollowerCountOfFollowingByFollowingId(int followingID) throws SQLException {
        String sql = "Update [User] set FollowerCount = ? where UserId = ?";
        int followerCount = getFollowerCountsByFollowingId(followingID);
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, followerCount + 1);
            ps.setInt(2, followingID);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFollowerCountOfFollowerByFollowingId(int followingID) throws SQLException {
        String sql = "UPDATE [User] set FollowerCount = ? where UserId = ?";
        int followerCount = getFollowerCountsByFollowingId(followingID);
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, followerCount - 1);
            ps.setInt(2, followingID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFollowCountsOfFollowingByFollowerId(int followerID) {
        String sql = "UPDATE [User] set FollowCounts = ? where UserId = ?";
        int followCounts = getFollowCountsByFollowerId(followerID);
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, followCounts - 1);
            ps.setInt(2, followerID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
