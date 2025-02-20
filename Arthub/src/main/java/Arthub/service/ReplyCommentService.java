package Arthub.service;

import Arthub.entity.ReplyComment;
import java.util.List;

public interface ReplyCommentService {
    List<ReplyComment> getAllReplyComments();
    List<ReplyComment> getReplyCommentsByCommentID(int commentID);
    ReplyComment saveReplyComment(ReplyComment replyComment);
}
