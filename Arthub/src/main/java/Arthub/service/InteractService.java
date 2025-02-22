package Arthub.service;

import Arthub.entity.Artwork;

import java.util.List;

public interface InteractService {
    boolean toggleFavourite(int userID, int artworkID);
    boolean isFavourite(int userID, int artworkID);
    List<Artwork> getFavouriteArtworks(int userID);

}
