package Arthub.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class Artwork {
    private int artworkID;
    private int creatorID;
    private String artworkName;
    private String description;
    private String dateCreated;
    private int likes;
    private int views;
    private int comments;
    private int favorites;
    private boolean purchasable;
    private double price;
    private String imageFile;
    private List<TagArt> artworkTags;
    private int status;

    // Constructor không tham số
    public Artwork() {
    }

    // Constructor đầy đủ
    public Artwork(int artworkID, int creatorID, String artworkName, String description,
                   String dateCreated, int likes, int views, int comments, int favorites,
                   boolean purchasable, double price, String imageFile, List<TagArt> artworkTags) {
        this.artworkID = artworkID;
        this.creatorID = creatorID;
        this.artworkName = artworkName;
        this.description = description;
        this.dateCreated = dateCreated;
        this.likes = likes;
        this.views = views;
        this.comments = comments;
        this.favorites = favorites;
        this.purchasable = purchasable;
        this.price = price;
        this.imageFile = imageFile;
        this.artworkTags = artworkTags;
    }

    // Getters & Setters
    public int getArtworkID() {
        return artworkID;
    }

    public void setArtworkID(int artworkID) {
        this.artworkID = artworkID;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public String getArtworkName() {
        return artworkName;
    }

    public void setArtworkName(String artworkName) {
        this.artworkName = artworkName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public boolean isPurchasable() {
        return purchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public List<TagArt> getArtworkTags() {
        return artworkTags;
    }

    public void setArtworkTags(List<TagArt> artworkTags) {
        this.artworkTags = artworkTags;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
