package Arthub.dto;

public class FollowDTO {
    private Integer followerID;
    private Integer followingID;

    public Integer getFollowerID() {
        return followerID;
    }

    public void setFollowerID(Integer followerID) {
        this.followerID = followerID;
    }

    public Integer getFollowingID() {
        return followingID;
    }

    public void setFollowingID(Integer followingID) {
        this.followingID = followingID;
    }
}