package Arthub.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Setter
@Getter
public class Follow {

    @JsonProperty("followerID")
    private int followerID;

    @JsonProperty("followingID")
    private int followingID;
    private LocalDate dateFollow;

    public Follow(){}


    public int getFollowerId() {
        return followerID;
    }

    public void setFollowerId(int followerId) {
        this.followerID = followerId;
    }

    public int getFollowingId() {
        return followingID;
    }

    public void setFollowingId(int followingId) {
        this.followingID = followingId;
    }

    public LocalDate getDateFollow() {
        return dateFollow;
    }

    public void setDateFollow(LocalDate dateFollow) {
        this.dateFollow = dateFollow;
    }
}
