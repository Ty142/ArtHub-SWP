package Arthub.dto;

import java.time.LocalDate;

public class TopicDTO {
    private int TopicID;
    private String Title;
    private int TypeID;
    private int UserID;
    private String Description;
    private String UserName;
    private String AvatarURL;
    private int TotalThread;
    private LocalDate DateCreated;


    public int getTopicID() {
        return TopicID;
    }

    public void setTopicID(int topicID) {
        TopicID = topicID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getAvatarURL() {
        return AvatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        AvatarURL = avatarURL;
    }

    public int getTotalThread() {
        return TotalThread;
    }

    public void setTotalThread(int totalThread) {
        TotalThread = totalThread;
    }
    public LocalDate getDateCreated() {
        return DateCreated;
    }
    public void setDateCreated(LocalDate dateCreated) {
        DateCreated = dateCreated;
    }
}
