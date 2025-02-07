package Arthub.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class Follow {
    private int followId;
    private int followerId;
    private int followingId;
    private Date dateFollow;

    public Follow(){}
}
