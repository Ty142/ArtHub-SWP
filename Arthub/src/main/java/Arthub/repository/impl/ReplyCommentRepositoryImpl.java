package Arthub.repository.impl;

import Arthub.entity.ReplyComment;
import Arthub.repository.ReplyCommentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReplyCommentRepositoryImpl implements ReplyCommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ReplyComment> getAllReplyComments() {
        String sql = "SELECT * FROM ReplyComment ORDER BY ReplyCommentID DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ReplyComment.class));
    }

    @Override
    public List<ReplyComment> getReplyCommentsByCommentID(int commentID) {
        String sql = "SELECT * FROM ReplyComment WHERE CommentID = ? ORDER BY ReplyCommentID DESC";
        return jdbcTemplate.query(sql, new Object[]{commentID}, new BeanPropertyRowMapper<>(ReplyComment.class));
    }

    @Override
    public ReplyComment save(ReplyComment replyComment) {
        String sql = "INSERT INTO ReplyComment (CommentID, ReplierID, CommentDetail, DateOfInteract) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, replyComment.getCommentID(), replyComment.getReplierID(), replyComment.getCommentDetail(), replyComment.getDateOfInteract());
        return replyComment;
    }

    public List<ReplyComment> findAll() {
        String sql = "SELECT * FROM ReplyComment";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ReplyComment.class));
    }

    @Override
    public List<ReplyComment> getReplyCommentsByArtworkID(int ArtworkID) {
        List<ReplyComment> result = new ArrayList<>();
        String sql = "select ReplycommentID from ReplyComment r" +
                "  join Comment c on c.CommentID = r.commentID" +
                "  join Artworks a on a.ArtworkID = c.ArtworkID" +
                "  where a.ArtworkID = ?";

        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ArtworkID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ReplyComment comment = new ReplyComment();
                comment.setReplyCommentID(resultSet.getInt("ReplycommentID"));
               result.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    @Override
    public void deleteReplyComments(List<ReplyComment> replyComments) {
        for (ReplyComment comment : replyComments) {
            String sql = "DELETE FROM ReplyComment WHERE ReplyCommentID =?";
            jdbcTemplate.update(sql, comment.getReplyCommentID());
        }
    }
}
