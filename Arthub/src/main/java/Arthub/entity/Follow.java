package Arthub.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Setter
@Getter
public class Follow {
    private int followerId;
    private int followingId;
    private LocalDate dateFollow;

    public Follow(){}


    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    public LocalDate getDateFollow() {
        return dateFollow;
    }

    public void setDateFollow(LocalDate dateFollow) {
        this.dateFollow = dateFollow;
    }
}
