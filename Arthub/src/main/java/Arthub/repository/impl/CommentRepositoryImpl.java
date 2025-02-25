package Arthub.repository.impl;

import Arthub.entity.Comment;
import Arthub.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Comment comment) {
        String sql = "INSERT INTO Comment (commentDetail, artworkID, userID, createdDate) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, comment.getCommentDetail(), comment.getArtworkID(), comment.getUserID(), comment.getCreatedDate());
    }

    @Override
    public List<Comment> getAllComments() {
        String sql = "SELECT * FROM Comment";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class));
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


}
