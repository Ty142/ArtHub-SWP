package Arthub.service;

import Arthub.entity.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    List<Comment> getCommentsByArtworkID(int artworkID);
    void saveComment(Comment comment);  // Thêm phương thức lưu bình luận
}
