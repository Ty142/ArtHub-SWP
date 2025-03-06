package Arthub.repository;

import Arthub.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentRepository {
    List<Comment> getAllComments() throws SQLException;
    List<Comment> getCommentsByArtworkID(int artworkID);
    void save(Comment comment) throws Exception;  // Thêm phương thức save
    List<Comment> findAll();
    void deleteCommentByArtworkID(int artworkID);

    void saveCommentInThread(Comment comment);

    List<Comment> getAllCommentsByThreadID(int ThreadID);
}
