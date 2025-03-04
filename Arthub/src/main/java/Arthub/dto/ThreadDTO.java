package Arthub.dto;

import java.time.LocalDate;

public class ThreadDTO {
    private int ThreadID;
    private String TitleThread;
    private String ThreadDescription;
    private int Likes;
    private int Comments;
    private LocalDate DateCreated;
    private int TopicID;
    private int UserID;

    public int getThreadID() {
        return ThreadID;
    }

    public void setThreadID(int threadID) {
        ThreadID = threadID;
    }

    public String getTitleThread() {
        return TitleThread;
    }

    public void setTitleThread(String titleThread) {
        TitleThread = titleThread;
    }

    public String getThreadDescription() {
        return ThreadDescription;
    }

    public void setThreadDescription(String threadDescription) {
        ThreadDescription = threadDescription;
    }

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int likes) {
        Likes = likes;
    }

    public int getComments() {
        return Comments;
    }

    public void setComments(int comments) {
        Comments = comments;
    }

    public LocalDate getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        DateCreated = dateCreated;
    }

    public int getTopicID() {
        return TopicID;
    }

    public void setTopicID(int topicID) {
        TopicID = topicID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
