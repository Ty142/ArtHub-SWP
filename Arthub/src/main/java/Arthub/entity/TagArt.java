package Arthub.entity;

public class TagArt {
    private int tagArtID;
    private int artworkID;
    private int tagID;


    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public int getArtworkID() {
        return artworkID;
    }

    public void setArtworkID(int artworkID) {
        this.artworkID = artworkID;
    }

    public int getTagArtID() {
        return tagArtID;
    }

    public void setTagArtID(int tagArtID) {
        this.tagArtID = tagArtID;
    }
}
