package Arthub.repository.impl;

import Arthub.entity.Comment;
import Arthub.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Comment comment) throws Exception {
        String sql = "INSERT INTO Comment (commentDetail, artworkID, userID, createdDate) VALUES (?, ?, ?, ?)";
        try(Connection connection = utils.ConnectUtils.getInstance().openConection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, comment.getCommentDetail());
            statement.setInt(2, comment.getArtworkID());
            statement.setInt(3, comment.getUserID());
            statement.setTimestamp(4, new Timestamp(comment.getCreatedDate().getTime()));
            statement.executeUpdate();
        }

    }

    @Override
    public List<Comment> getAllComments() throws SQLException {
        String sql = "SELECT * FROM Comment";
       List<Comment> comments = new ArrayList<Comment>();
        try(Connection connection = utils.ConnectUtils.getInstance().openConection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setCommentID(resultSet.getInt("commentID"));
                comment.setCommentDetail(resultSet.getString("commentDetail"));
                comment.setArtworkID(resultSet.getInt("artworkID"));
                comment.setUserID(resultSet.getInt("userID"));
                comment.setCreatedDate(resultSet.getDate("createdDate"));
                comments.add(comment);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    @Override
    public List<Comment> getCommentsByArtworkID(int artworkID) {
        String sql = "SELECT * FROM Comment WHERE artworkID = ?";
        return jdbcTemplate.query(sql, new Object[]{artworkID}, new BeanPropertyRowMapper<>(Comment.class));
    }

    // Hàm lấy tất cả comment
    public List<Comment> findAll() {
        String sql = "SELECT * FROM Comment";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class));
    }




    @Override
    public void deleteCommentByArtworkID(int artworkID) {
        String sql = "DELETE FROM Comment WHERE artworkID = ?";
        try(Connection connection = utils.ConnectUtils.getInstance().openConection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, artworkID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveCommentInThread(Comment comment) {
        String sql = "INSERT INTO Comment (commentDetail, userID, createdDate, ThreadID) VALUES (?, ?, ?, ?)";
        try(Connection connection = utils.ConnectUtils.getInstance().openConection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, comment.getCommentDetail());
            statement.setInt(2, comment.getUserID());
            statement.setTimestamp(3, new Timestamp(comment.getCreatedDate().getTime()));
            statement.setInt(4, comment.getThreadID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> getAllCommentsByThreadID(int ThreadID) {
        String sql = "SELECT * FROM Comment where ThreadID = " + ThreadID ;
        List<Comment> comments = new ArrayList<>();
        try(Connection connection = utils.ConnectUtils.getInstance().openConection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setCommentID(resultSet.getInt("commentID"));
                comment.setCommentDetail(resultSet.getString("commentDetail"));
                comment.setUserID(resultSet.getInt("userID"));
                comment.setCreatedDate(resultSet.getDate("createdDate"));
                comment.setThreadID(resultSet.getInt("ThreadID"));
                comments.add(comment);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return comments;
    }



}
