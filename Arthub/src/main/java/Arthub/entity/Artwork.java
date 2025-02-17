package Arthub.entity;

public class Artwork {
        private int artworkID;
        private String artworkName;
        private String description;
        private int likes;
        private boolean purchasable;
        private double price;
        private String imageFile;
        private int userID;
        private int libraryID;
        private int status;

        // Constructor
        public Artwork() {
        }


        public int getArtworkID() {
                return artworkID;
        }

        public void setArtworkID(int artworkID) {
                this.artworkID = artworkID;
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

        public int getLikes() {
                return likes;
        }

        public void setLikes(int likes) {
                this.likes = likes;
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

        public int getUserID() {
                return userID;
        }

        public void setUserID(int userID) {
                this.userID = userID;
        }

        public int getLibraryID() {
                return libraryID;
        }

        public void setLibraryID(int libraryID) {
                this.libraryID = libraryID;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }
}
