package Arthub.api;

import Arthub.entity.Comment;
import Arthub.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentAPI {

    @Autowired
    private CommentService commentService;

    // API để lấy tất cả các bình luận
    @GetMapping("/")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // API để lấy các bình luận theo ArtworkID
    @GetMapping("/artwork/{artworkID}")
    public List<Comment> getCommentsByArtworkID(@PathVariable int artworkID) {
        return commentService.getCommentsByArtworkID(artworkID);
    }

    // API để gửi bình luận
    @PostMapping("/")
    public String postComment(@RequestBody Comment comment) {
        // Lưu bình luận vào database
        comment.setCreatedDate(new Date());
        commentService.saveComment(comment);

        return "Comment saved successfully";
    }
}
