package Arthub.service.Impl;

import Arthub.entity.Comment;
import Arthub.repository.CommentRepository;
import Arthub.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // Lưu bình luận vào database
    @Override
    public void saveComment(Comment comment) throws Exception {
        commentRepository.save(comment);
    }

    // Các phương thức khác
    @Override
    public List<Comment> getAllComments() throws SQLException {
        return commentRepository.getAllComments();
    }

    @Override
    public List<Comment> getCommentsByArtworkID(int artworkID) {
        return commentRepository.getCommentsByArtworkID(artworkID);
    }
}
