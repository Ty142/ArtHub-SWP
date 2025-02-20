package Arthub.repository;

import Arthub.entity.ReplyComment;
import java.util.List;

public interface ReplyCommentRepository {
    List<ReplyComment> getAllReplyComments();  // Lấy tất cả reply comment
    List<ReplyComment> getReplyCommentsByCommentID(int commentID);  // Lấy reply comment theo commentID
    ReplyComment save(ReplyComment replyComment);
}
