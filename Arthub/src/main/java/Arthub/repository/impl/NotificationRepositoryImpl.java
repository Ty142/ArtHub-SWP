package Arthub.repository.impl;

import Arthub.entity.Notification;
import Arthub.repository.NotificationRepository;
import Arthub.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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


}
