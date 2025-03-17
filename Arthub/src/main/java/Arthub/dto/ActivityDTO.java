package Arthub.dto;

import java.time.LocalDateTime;

public class ActivityDTO {
    private String ownerName;
    private String activityName;
    private String userInteract;
    private LocalDateTime activityDate;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getUserInteract() {
        return userInteract;
    }

    public void setUserInteract(String userInteract) {
        this.userInteract = userInteract;
    }

    public LocalDateTime getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDateTime activityDate) {
        this.activityDate = activityDate;
    }
}
