package Arthub.service;

import Arthub.dto.ActivityDTO;
import Arthub.entity.Artwork;

import java.util.List;

public interface InteractService {
    boolean toggleFavourite(int userID, int artworkID);
    boolean isFavourite(int userID, int artworkID);
    List<Artwork> getFavouriteArtworks(int userID);

    boolean toggleLike(int userID, int artworkID);
    boolean isLike(int userID, int artworkID);
    List<Artwork> getLikeArtworks(int userID);
    int getLikeCount(int artworkID);

    void saveInteractions();
    void saveInteractionsOfCommentsForum(int ThreadID);

    List<ActivityDTO> getListOfActivity();
}
