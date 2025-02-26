package Arthub.repository;

import Arthub.entity.Artwork;
import Arthub.entity.Interact;
import java.util.List;

public interface InteractRepository {
    boolean toggleFavourite(int userID, int artworkID);
    boolean isFavourite(int userID, int artworkID);
    List<Artwork> getFavouriteArtworks(int userID);

    boolean toggleLike(int userID, int artworkID);
    boolean isLike(int userID, int artworkID);
    List<Artwork> getLikeArtworks(int userID);
    int getLikeCount(int artworkID);

        void save(Interact interact);
        List<Interact> findByArtworkIDAndUserIDAndActivityID(int artworkID, int userID, int activityID, String date);
        void deleteInteractByArtworkID(int artworkID);
    }
