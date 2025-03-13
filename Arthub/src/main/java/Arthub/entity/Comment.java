package Arthub.entity;

import java.util.Date;

public class Comment {

    private int commentID;
    private String commentDetail;
    private int artworkID;
    private int userID;
    private Date createdDate;
    private int threadID;

    public Comment() {
    }

    // Constructor
    public Comment(int commentID, String commentDetail, int artworkID, int userID, Date createdDate) {
        this.commentID = commentID;
        this.commentDetail = commentDetail;
        this.artworkID = artworkID;
        this.userID = userID;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public int getArtworkID() {
        return artworkID;
    }

    public void setArtworkID(int artworkID) {
        this.artworkID = artworkID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getThreadID() {
        return threadID;
    }

    public void setThreadID(int threadID) {
        this.threadID = threadID;
    }
}
