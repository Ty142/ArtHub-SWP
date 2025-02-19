package Arthub.entity;

public class ArtworkTag {
    private int artworkTagID;
    private int artworkID;
    private int tagID;

    public ArtworkTag(int artworkTagID, int artworkID, int tagID) {
        this.artworkTagID = artworkTagID;
        this.artworkID = artworkID;
        this.tagID = tagID;
    }

    public int getArtworkTagID() { return artworkTagID; }
    public void setArtworkTagID(int artworkTagID) { this.artworkTagID = artworkTagID; }

    public int getArtworkID() { return artworkID; }
    public void setArtworkID(int artworkID) { this.artworkID = artworkID; }

    public int getTagID() { return tagID; }
    public void setTagID(int tagID) { this.tagID = tagID; }
}
