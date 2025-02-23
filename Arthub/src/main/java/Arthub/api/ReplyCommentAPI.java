package Arthub.api;

import Arthub.entity.ReplyComment;
import Arthub.service.ReplyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ReplyCommentAPI {

    @Autowired
    private ReplyCommentService replyCommentService;

    // Lấy tất cả reply comments
    @GetMapping("/api/replycomment")
    public List<ReplyComment> getAllReplyComments() {
        return replyCommentService.getAllReplyComments();
    }

    // Lấy reply comments theo CommentID
    @GetMapping("/api/replycomment/{commentID}")
    public List<ReplyComment> getReplyCommentsByCommentID(@PathVariable int commentID) {
        return replyCommentService.getReplyCommentsByCommentID(commentID);
    }

    @PostMapping("/api/replycomment/add")
    public ResponseEntity<ReplyComment> addReplyComment(@RequestBody ReplyComment replyComment) {
        try {
            // Giả sử replyCommentService đã xử lý và lưu vào database
            ReplyComment savedReply = replyCommentService.saveReplyComment(replyComment);
            return ResponseEntity.ok(savedReply);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
