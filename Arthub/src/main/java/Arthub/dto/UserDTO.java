package Arthub.dto;


import java.util.Date;
public class UserDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String biography;
    private double coins;
    private String createdAt;
    private Date dateOfBirth;
    private Date lastLogin;
    private String profilePicture;
    private String backgroundPicture;
    private int followCounts;
    private int follower;
    private String base64data;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

//    public Date getLastLogin() {
//        return lastLogin;
//    }
//
//    public void setLastLogin(Date lastLogin) {
//        this.lastLogin = lastLogin;
//    }
//
//    public String getProfilePicture() {
//        return profilePicture;
//    }
//
//    public void setProfilePicture(String profilePicture) {
//        this.profilePicture = profilePicture;
//    }
//
//    public String getBackgroundPicture() {
//        return backgroundPicture;
//    }
//
//    public void setBackgroundPicture(String backgroundPicture) {
//        this.backgroundPicture = backgroundPicture;
//    }
//
//    public int getFollowCounts() {
//        return followCounts;
//    }
//
//    public void setFollowCounts(int followCounts) {
//        this.followCounts = followCounts;
//    }
//
//    public int getFollower() {
//        return follower;
//    }
//
//    public void setFollower(int follower) {
//        this.follower = follower;
//    }


    public String getBase64data() {
        return base64data;
    }

    public void setBase64data(String base64data) {
        this.base64data = base64data;
    }
}
