package Arthub.entity;

import java.time.LocalDate;



public class Notification {
    private int notificationId;
    private String message;
    private LocalDate createdAt;
    private Integer  interactId;
    private Integer  profileNoti;
    private Integer  artworkNoti;
    private byte isRead;

    public Notification(int notificationId, String message, LocalDate createdAt, int interactId, int profileNoti, int artworkNoti, byte isRead) {
        this.notificationId = notificationId;
        this.message = message;
        this.createdAt = createdAt;
        this.interactId = interactId;
        this.profileNoti = profileNoti;
        this.artworkNoti = artworkNoti;
        this.isRead = isRead;
    }

    public Notification() {
        this.notificationId = 0;
        this.message = "";
        this.createdAt = LocalDate.now();
        this.interactId = 0;
        this.profileNoti = 0;
        this.artworkNoti = 0;
        this.isRead = 0;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Integer  getInteractId() {
        return interactId;
    }

    public void setInteractId(Integer  interactId) {
        this.interactId = interactId;
    }

    public Integer  getProfileNoti() {
        return profileNoti;
    }

    public void setProfileNoti(Integer  profileNoti) {
        this.profileNoti = profileNoti;
    }

    public Integer  getArtworkNoti() {
        return artworkNoti;
    }

    public void setArtworkNoti(Integer  artworkNoti) {
        this.artworkNoti = artworkNoti;
    }

    public byte getIsRead() {
        return isRead;
    }

    public void setIsRead(byte isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", interactId=" + interactId +
                ", profileNoti=" + profileNoti +
                ", artworkNoti=" + artworkNoti +
                ", isRead=" + isRead +
                '}';
    }
}
