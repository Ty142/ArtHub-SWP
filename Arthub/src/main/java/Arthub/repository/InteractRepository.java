package Arthub.repository;

import Arthub.entity.Artwork;
import Arthub.entity.Interact;
import java.util.List;

public interface InteractRepository {
    boolean toggleFavourite(int userID, int artworkID);
    boolean isFavourite(int userID, int artworkID);
    List<Artwork> getFavouriteArtworks(int userID);

        void save(Interact interact);
        List<Interact> findByArtworkIDAndUserIDAndActivityID(int artworkID, int userID, int activityID, String date);
        void deleteInteractByArtworkID(int artworkID);
    }
