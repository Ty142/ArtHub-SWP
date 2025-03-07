package Arthub.service.Impl;

import Arthub.entity.Artwork;
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


    public InteractServiceImpl(InteractRepository interactRepository) {
        this.interactRepository = interactRepository;
    }

    //-------- Start Favourites ----------
    @Override
    public boolean toggleFavourite(int userID, int artworkID) {
        return interactRepository.toggleFavourite(userID, artworkID);
    }

    @Override
    public boolean isFavourite(int userID, int artworkID) {
        return interactRepository.isFavourite(userID, artworkID);
    }

    @Override
    public List<Artwork> getFavouriteArtworks(int userID) {
        return interactRepository.getFavouriteArtworks(userID);
    }
    //-------- End Favourites ----------

    //-------- Start Like ----------
    @Override
    public boolean toggleLike(int userID, int artworkID) {
        return interactRepository.toggleLike(userID, artworkID);
    }

    @Override
    public boolean isLike(int userID, int artworkID) {
        return interactRepository.isLike(userID, artworkID);
    }

    @Override
    public List<Artwork> getLikeArtworks(int userID) {
        return interactRepository.getLikeArtworks(userID);
    }

    @Override
    public int getLikeCount(int artworkID) {
        return interactRepository.getLikeCount(artworkID);
    }
    //-------- End Like ----------

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

    @Override
    public void saveInteractionsOfCommentsForum(int ThreadID) {
        List<Comment> comments = commentRepository.getAllCommentsByThreadID(ThreadID);
        List<ReplyComment> replyComments = replyCommentRepository.findAll();

        for (Comment comment : comments) {
            if (!comments.contains(comment)) {
                Interact interact = new Interact();
                interact.setUserID(comment.getUserID());
                interact.setActivityID(3);
                interact.setDateOfInteract(comment.getCreatedDate());
                interact.setThreadID(comment.getThreadID());
            }
        }

        // Lưu các reply comment vào bảng Interact
        for (ReplyComment reply : replyComments) {
            Integer threadID = GetThreadIDFromComment(reply.getCommentID());
            if (threadID != null) {
                Interact interact = new Interact();
                interact.setUserID(reply.getReplierID());
                interact.setActivityID(4);
                interact.setDateOfInteract(reply.getDateOfInteract());
                interact.setThreadID(threadID);
                interactRepository.saveInteractCommentOfForum(interact);

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

    private Integer GetThreadIDFromComment(int commentID) {
        String sql = "SELECT ThreadID FROM Comment WHERE CommentID = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, commentID);
    }
}