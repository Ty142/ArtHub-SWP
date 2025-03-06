package Arthub.entity;

import java.util.Date;

public class Interact {
    private int interactID;
    private int artworkID;
    private int userID;
    private int activityID;
    private Date dateOfInteract;
    private int ThreadID;

    // Constructor mặc định
    public Interact() {}

    // Constructor với tham số
    public Interact(int artworkID, int userID, int activityID, Date dateOfInteract) {
        this.artworkID = artworkID;
        this.userID = userID;
        this.activityID = activityID;
        this.dateOfInteract = dateOfInteract;
    }

    // Getters và Setters
    public int getInteractID() { return interactID; }
    public void setInteractID(int interactID) { this.interactID = interactID; }

    public int getArtworkID() { return artworkID; }
    public void setArtworkID(int artworkID) { this.artworkID = artworkID; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public int getActivityID() { return activityID; }
    public void setActivityID(int activityID) { this.activityID = activityID; }

    public Date getDateOfInteract() { return dateOfInteract; }
    public void setDateOfInteract(Date dateOfInteract) { this.dateOfInteract = dateOfInteract; }

    public int getThreadID() {
        return ThreadID;
    }

    public void setThreadID(int threadID) {
        ThreadID = threadID;
    }
}
