package Arthub.dto;

import java.util.Date;
import java.util.List;

public class ArtworkDTO {
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
    private List<ArtworkTagDTO> artworkTags;
    private int status;

    public ArtworkDTO() {}

    public int getArtworkID() { return artworkID; }
    public void setArtworkID(int artworkID) { this.artworkID = artworkID; }

    public int getCreatorID() { return creatorID; }
    public void setCreatorID(int creatorID) { this.creatorID = creatorID; }

    public String getArtworkName() { return artworkName; }
    public void setArtworkName(String artworkName) { this.artworkName = artworkName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDateCreated() { return dateCreated; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }

    public int getComments() { return comments; }
    public void setComments(int comments) { this.comments = comments; }

    public int getFavorites() { return favorites; }
    public void setFavorites(int favorites) { this.favorites = favorites; }

    public boolean isPurchasable() { return purchasable; }
    public void setPurchasable(boolean purchasable) { this.purchasable = purchasable; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageFile() { return imageFile; }
    public void setImageFile(String imageFile) { this.imageFile = imageFile; }

    public List<ArtworkTagDTO> getArtworkTags() { return artworkTags; }
    public void setArtworkTags(List<ArtworkTagDTO> artworkTags) { this.artworkTags = artworkTags; }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
