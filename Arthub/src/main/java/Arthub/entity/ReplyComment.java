package Arthub.entity;

import java.util.Date;

public class ReplyComment {

    private int replyCommentID;
    private int commentID;
    private int replierID;
    private String commentDetail;
    private Date dateOfInteract;

    public ReplyComment() {
    }

    public ReplyComment(int replyCommentID, int commentID, int replierID, String commentDetail, Date dateOfInteract) {
        this.replyCommentID = replyCommentID;
        this.commentID = commentID;
        this.replierID = replierID;
        this.commentDetail = commentDetail;
        this.dateOfInteract = dateOfInteract;
    }


    public int getReplyCommentID() {
        return replyCommentID;
    }

    public void setReplyCommentID(int replyCommentID) {
        this.replyCommentID = replyCommentID;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getReplierID() {
        return replierID;
    }

    public void setReplierID(int replierID) {
        this.replierID = replierID;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public Date getDateOfInteract() {
        return dateOfInteract;
    }

    public void setDateOfInteract(Date dateOfInteract) {
        this.dateOfInteract = dateOfInteract;
    }

}
