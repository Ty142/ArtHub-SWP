package Arthub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


public class ThreadDTO {
    @JsonProperty("ThreadID")
    private int ThreadID;

    @JsonProperty("TitleThread")
    private String TitleThread;

    @JsonProperty("ThreadDescription")
    private String ThreadDescription;

    @JsonProperty("Likes")
    private int Likes;

    @JsonProperty("Comments")
    private int Comments;

    @JsonProperty("DateCreated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate DateCreated;

    @JsonProperty("TopicID")
    private int TopicID;

    @JsonProperty("UserID")
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
