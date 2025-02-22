package Arthub.entity;

import java.util.Date;

public class Interact {
    private int interactID;
    private int artworkID;
    private int userID;
    private int activityID; // 1 = Favourite
    private Date dateOfInteract;

    public Interact() {}

    public Interact(int artworkID, int userID, int activityID, Date dateOfInteract) {
        this.artworkID = artworkID;
        this.userID = userID;
        this.activityID = activityID;
        this.dateOfInteract = dateOfInteract;
    }

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
}
