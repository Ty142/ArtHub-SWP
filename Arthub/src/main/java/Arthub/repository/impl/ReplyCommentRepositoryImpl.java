package Arthub.repository.impl;

import Arthub.entity.ReplyComment;
import Arthub.repository.ReplyCommentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ReplyCommentRepositoryImpl implements ReplyCommentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ReplyComment> getAllReplyComments() {
        String sql = "SELECT * FROM ReplyComment";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ReplyComment.class));
    }

    @Override
    public List<ReplyComment> getReplyCommentsByCommentID(int commentID) {
        String sql = "SELECT * FROM ReplyComment WHERE CommentID = ?";
        return jdbcTemplate.query(sql, new Object[]{commentID}, new BeanPropertyRowMapper<>(ReplyComment.class));
    }

    @Override
    public ReplyComment save(ReplyComment replyComment) {
        String sql = "INSERT INTO ReplyComment (CommentID, ReplierID, CommentDetail, DateOfInteract) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, replyComment.getCommentID(), replyComment.getReplierID(), replyComment.getCommentDetail(), replyComment.getDateOfInteract());
        return replyComment;
    }
}
