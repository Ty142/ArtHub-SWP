package Arthub.repository;

import Arthub.entity.Interact;
import java.util.List;

public interface InteractRepository {
    boolean toggleFavourite(int userID, int artworkID);
    List<Integer> getFavouriteArtworks(int userID);
}
