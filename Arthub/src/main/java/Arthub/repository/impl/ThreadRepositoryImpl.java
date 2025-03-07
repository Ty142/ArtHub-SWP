package Arthub.repository.impl;

import Arthub.entity.Thread;
import Arthub.repository.ThreadRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ThreadRepositoryImpl implements ThreadRepository {


    @Override
    public List<Thread> GetThreadsByTopicId(int topicID) {
        String sql = "Select * from Thread where TopicID = ? ORDER BY ThreadID DESC";
        List<Thread> threads = new ArrayList<>();
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, topicID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Thread t = new Thread();
                t.setThreadID(rs.getInt("ThreadID"));
                t.setTitleThread(rs.getString("TitleThread"));
                t.setThreadDescription(rs.getString("ThreadDescription"));
                t.setLikes(rs.getInt("Likes"));
                t.setComments(rs.getInt("Comments"));
                Timestamp sqlTimestamp = rs.getTimestamp("DateCreated");
                if (sqlTimestamp != null) {
                    LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                    t.setDateCreated(localDateTime);
                }
                t.setTopicID(rs.getInt("TopicID"));
                t.setUserID(rs.getInt("UserID"));
                threads.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return threads;
    }

    @Override
    public Thread getTheLastUserIDInThreadByTopicId(int topicID) {
        List<Thread> threads = GetThreadsByTopicId(topicID);
        return threads.isEmpty() ? null : threads.get(0);
    }

    @Override
    public int TotalThreadByTopicID(int topicID) {
        String sql = "SELECT COUNT(*) as TotalThreads FROM Thread WHERE TopicID = ?";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, topicID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalThreads");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void InsertThread(Thread thread) {
        String sql = "INSERT INTO Thread values (?,?,?,?,?,?,?)";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, thread.getTitleThread());
            ps.setString(2, thread.getThreadDescription());
            ps.setInt(3, thread.getLikes());
            ps.setInt(4, thread.getComments());
            if (thread.getDateCreated() != null) {
                LocalDateTime utc7DateTime = thread.getDateCreated();`
                Timestamp timestamp = Timestamp.valueOf(utc7DateTime);
                ps.setTimestamp(5, timestamp);
            } else {
                ps.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // Mặc định nếu null
            }

            ps.setInt(6, thread.getTopicID());
            ps.setInt(7, thread.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Thread GetThreadsByThreadId(int threadID) {
        String sql = "SELECT * FROM Thread WHERE ThreadID = ?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, threadID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Thread t = new Thread();
                t.setThreadID(rs.getInt("ThreadID"));
                t.setTitleThread(rs.getString("TitleThread"));
                t.setThreadDescription(rs.getString("ThreadDescription"));
                t.setLikes(rs.getInt("Likes"));
                t.setComments(rs.getInt("Comments"));
                Timestamp sqlTimestamp = rs.getTimestamp("DateCreated");
                if (sqlTimestamp != null) {
                    LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                    t.setDateCreated(localDateTime);
                }
                t.setTopicID(rs.getInt("TopicID"));
                t.setUserID(rs.getInt("UserID"));
                return t;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
