package Arthub.service.Impl;

import Arthub.entity.Comment;
import Arthub.entity.ReplyComment;
import Arthub.entity.Interact;
import Arthub.repository.CommentRepository;
import Arthub.repository.ReplyCommentRepository;
import Arthub.repository.InteractRepository;
import Arthub.service.InteractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InteractServiceImpl implements InteractService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReplyCommentRepository replyCommentRepository;

    @Autowired
    private InteractRepository interactRepository;

    public void saveInteractions() {
        List<Comment> comments = commentRepository.findAll();
        List<ReplyComment> replyComments = replyCommentRepository.findAll();

        for (Comment comment : comments) {
            if (!interactExists(comment.getArtworkID(), comment.getUserID(), 3, comment.getCreatedDate())) {
                Interact interact = new Interact(comment.getArtworkID(), comment.getUserID(), 3, comment.getCreatedDate());
                interactRepository.save(interact);
            }
        }

        // Lưu các reply comment vào bảng Interact
        for (ReplyComment reply : replyComments) {
            Integer artworkID = getArtworkIDFromComment(reply.getCommentID());

            if (artworkID != null) {
                if (!interactExists(artworkID, reply.getReplierID(), 4, reply.getDateOfInteract())) {
                    Interact interact = new Interact(artworkID, reply.getReplierID(), 4, reply.getDateOfInteract());
                    interactRepository.save(interact);
                }
            }
        }
    }

    private boolean interactExists(int artworkID, int userID, int activityID, Date date) {
        List<Interact> interacts = interactRepository.findByArtworkIDAndUserIDAndActivityID(artworkID, userID, activityID, String.valueOf(date));
        return !interacts.isEmpty();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Integer getArtworkIDFromComment(int commentID) {
        String sql = "SELECT ArtworkID FROM Comment WHERE CommentID = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, commentID);
    }

}
