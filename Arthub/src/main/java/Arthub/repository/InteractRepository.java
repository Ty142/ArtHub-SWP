package Arthub.repository;

import Arthub.entity.Artwork;
import Arthub.entity.Interact;
import Arthub.entity.Thread;

import java.util.List;

public interface InteractRepository {
    boolean toggleFavourite(int userID, int artworkID);
    boolean isFavourite(int userID, int artworkID);
    List<Artwork> getFavouriteArtworks(int userID);

    boolean toggleLike(int userID, int artworkID);
    boolean isLike(int userID, int artworkID);
    List<Artwork> getLikeArtworks(int userID);
    int getLikeCount(int artworkID);


    boolean ToggleLikeThread(int userID, int ThreadID);
    boolean isThreadLiked(int userID, int threadID);
    public List<Thread> getLikedThreads(int userID);
    public int getThreadLikeCount(int threadID);

    void save(Interact interact);
    void saveInteractCommentOfForum(Interact interact);
        List<Interact> findByArtworkIDAndUserIDAndActivityID(int artworkID, int userID, int activityID, String date);
        List<Interact> findByThreadIDAndUserIDAndActivityID(int ThreadID, int userID, int activityID, String s);
        void deleteInteractByArtworkID(int artworkID);
    }
