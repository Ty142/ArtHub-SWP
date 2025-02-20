package Arthub.repository;

import Arthub.entity.Comment;
import java.util.List;

public interface CommentRepository {
    List<Comment> getAllComments();
    List<Comment> getCommentsByArtworkID(int artworkID);
    void save(Comment comment);  // Thêm phương thức save
}
