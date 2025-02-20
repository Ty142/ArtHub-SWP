package Arthub.service;

import java.util.List;

public interface InteractService {
    boolean toggleFavourite(int userID, int artworkID);
    List<Integer> getFavouriteArtworks(int userID);
}
