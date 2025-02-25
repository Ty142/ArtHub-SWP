package Arthub.service.Impl;

import Arthub.entity.ReplyComment;
import Arthub.repository.ReplyCommentRepository;
import Arthub.service.ReplyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReplyCommentServiceImpl implements ReplyCommentService {

    @Autowired
    private ReplyCommentRepository replyCommentRepository;

    @Override
    public List<ReplyComment> getAllReplyComments() {
        return replyCommentRepository.getAllReplyComments();
    }

    @Override
    public List<ReplyComment> getReplyCommentsByCommentID(int commentID) {
        return replyCommentRepository.getReplyCommentsByCommentID(commentID);
    }

    @Override
    public ReplyComment saveReplyComment(ReplyComment replyComment) {
        return replyCommentRepository.save(replyComment);  // Lưu reply comment vào database
    }
}
