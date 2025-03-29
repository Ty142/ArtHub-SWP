package Arthub.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;


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
    private int typeId;



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate dateOfBirth;
    private Date lastLogin;
    private int accountId;
    private String profilePicture;
    private String backgroundPicture;
    private int followCounts;
    private int followerCount;
    private String email;
    private int totalLikes;
    private double popularity;


    public User(int userId, String firstName, String lastName, String phoneNumber, String address, String biography, double coins, String createdAt, int rankId, int roleId, int typeId, LocalDate dateOfBirth, Date lastLogin, int accountId, String profilePicture, String backgroundPicture, int followCounts, int followerCount, String email, int totalLikes, double popularity) {
        this.userId  = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.biography = biography;
        this.coins = coins;
        this.createdAt = createdAt;
        this.rankId = rankId;
        this.roleId = roleId;
        this.typeId = typeId;
        this.dateOfBirth = dateOfBirth;
        this.lastLogin = lastLogin;
        this.accountId = accountId;
        this.profilePicture = profilePicture;
        this.backgroundPicture = backgroundPicture;
        this.followCounts = followCounts;
        this.followerCount = followerCount;
        this.email = email;
        this.totalLikes = totalLikes;
        this.popularity = popularity;
    }


    public User () {
        this.userId = 0;
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.address = "";
        this.biography = "";
        this.coins = 0;
        this.createdAt = "";
        this.rankId = 0;
        this.roleId = 0;
        this.typeId = 0;
        this.dateOfBirth = null;
        this.lastLogin = null;
        this.accountId = 0;
        this.profilePicture = "";
        this.backgroundPicture = "";
        this.followCounts = 0;
        this.followerCount = 0;
        this.email = "";
        this.totalLikes = 0;
        this.popularity = 0;
    }

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

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }

    public int getFollowCounts() {
        return followCounts;
    }

    public void setFollowCounts(int followCounts) {
        this.followCounts = followCounts;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}