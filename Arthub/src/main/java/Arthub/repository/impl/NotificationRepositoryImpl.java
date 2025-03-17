package Arthub.repository.impl;

import Arthub.entity.Notification;
import Arthub.entity.Withdraw;
import Arthub.repository.NotificationRepository;
import Arthub.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Notification notification) {
            return false;
    }

    @Override
    public Notification findByNotificationByFollowerIDAndFollowingID(int followerID, int followingID) {
        String sql = "SELECT *" +
                "FROM [Arthub].[dbo].[Notification] AS n " +
                "INNER JOIN [Arthub].[dbo].[Following] AS f ON n.FollowID = f.FollowID " +
                "WHERE f.FollowingID = ? AND f.FollowerID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{followingID, followerID}, new BeanPropertyRowMapper<>(Notification.class));
    }

    @Override
    public List<Notification> getNotificationsOfTheUserFromUserId(int userId) {
        String sql = "SELECT * FROM Notification WHERE ProfileNoti = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(Notification.class));
    }

    @Override
    public void readNotificationByUserId(int userId) {
        String sql = "UPDATE Notification SET is_read = 1 WHERE ProfileNoti = ?";
        jdbcTemplate.update(sql, new Object[]{userId});
    }

    @Override
    public Notification saveNotificationByWithdraw(Withdraw withdraw) {
        String sql = "INSERT INTO Notification (Message,CreatedAt,ProfileNoti, Amount) " +
                "VALUES (?,?,?,?)";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, "#2");
            Timestamp timestamp = Timestamp.valueOf(withdraw.getDateRequest());
            statement.setTimestamp(2, timestamp);
            statement.setInt(3, withdraw.getUserID());
            statement.setDouble(4, withdraw.getCoinWithdraw()*25000);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return new Notification(generatedKeys.getInt(1), "#2", timestamp, withdraw.getUserID(), withdraw.getCoinWithdraw());
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
