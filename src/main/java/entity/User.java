package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String biography;
    private double coins;
    private String createdAt;
    private int rankId;
    private int roleId;
    private Date dateOfBirth;
    private Date lastLogin;
    private int accountId;
    private String profilePicture;
    private String backgroundPicture;
    private int followCounts;
    private int follower;

    public User() {
    }
}
