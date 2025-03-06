package Arthub.service;

import Arthub.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    List<Comment> getAllComments() throws SQLException;
    List<Comment> getCommentsByArtworkID(int artworkID);
    void saveComment(Comment comment) throws Exception;  // Thêm phương thức lưu bình luận
}
