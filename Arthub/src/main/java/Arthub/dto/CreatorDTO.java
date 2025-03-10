package Arthub.dto;

import java.util.Date;

public class CreatorDTO {
    private String email; // Thông tin Account
    private int accountId;
    private double coins;
    private String userName;
    private String profilePicture;
    private String backgroundPicture;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Date lastLogin;
    private Date createdAt;
    private Date dateOfBirth;
    private String biography;
    private int followCounts;
    private int followerCount;
    private int rankId;
    private int roleId;

    // Getters & Setters (Bắt buộc phải có)
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public double getCoins() { return coins; }
    public void setCoins(double coins) { this.coins = coins; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    public String getBackgroundPicture() { return backgroundPicture; }
    public void setBackgroundPicture(String backgroundPicture) { this.backgroundPicture = backgroundPicture; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Date getLastLogin() { return lastLogin; }
    public void setLastLogin(Date lastLogin) { this.lastLogin = lastLogin; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    public int getFollowCounts() { return followCounts; }
    public void setFollowCounts(int followCounts) { this.followCounts = followCounts; }

    public int getFollowerCount() { return followerCount; }
    public void setFollowerCount(int followerCount) { this.followerCount = followerCount; }

    public int getRankId() { return rankId; }
    public void setRankId(int rankId) { this.rankId = rankId; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }
}