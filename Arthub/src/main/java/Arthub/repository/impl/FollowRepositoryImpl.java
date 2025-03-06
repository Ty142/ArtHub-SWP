package Arthub.repository.impl;

import Arthub.entity.Follow;
import Arthub.entity.User;
import Arthub.repository.FollowRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

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

    @Override
    public User getFollowingUserFromFollowID(int followerID) throws SQLException {
        String sql = "SELECT * FROM [User] u inner join Following f on f.FollowerID = u.UserID where f.FollowID =? ";
        User user = null;
        utils.ConnectUtils db = utils.ConnectUtils.getInstance();
        try {
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, followerID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setUserId(resultSet.getInt("UserId"));
                    user.setFirstName(resultSet.getString("FirstName"));
                    user.setLastName(resultSet.getString("LastName"));
                    user.setPhoneNumber(resultSet.getString("PhoneNumber"));
                    user.setAddress(resultSet.getString("Address"));
                    user.setBiography(resultSet.getString("Biography"));
                    user.setCoins(resultSet.getDouble("Coins"));
                    user.setCreatedAt(resultSet.getString("CreatedAt"));
                    user.setRankId(resultSet.getInt("RankId"));
                    user.setRoleId(resultSet.getInt("RoleId"));

                    Date sqlDate = resultSet.getDate("DateOfBirth");
                    LocalDate localDate = (sqlDate != null) ? ((java.sql.Date) sqlDate).toLocalDate() : null;
                    user.setDateOfBirth(localDate);
//                    user.setDateOfBirth(resultSet.getDate("DateOfBirth"));

                    user.setLastLogin(resultSet.getDate("LastLogin"));
                    user.setAccountId(resultSet.getInt("AccountId"));
                    user.setProfilePicture(resultSet.getString("ProfilePicture"));
                    user.setBackgroundPicture(resultSet.getString("BackgroundPicture"));
                    user.setFollowCounts(resultSet.getInt("FollowCounts"));
                    user.setFollowerCount(resultSet.getInt("FollowerCount"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching user by id", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return user;
    }


}
