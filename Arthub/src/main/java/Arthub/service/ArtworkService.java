package Arthub.service;

import Arthub.entity.Artwork;
import Arthub.entity.TagArt;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ArtworkService {
    void PushArtwork(Artwork artwork, TagArt tagArt);

    void PushArtwork(Artwork artwork);

    List<Artwork> getArtworks();

    List<Artwork> getArtworksByPurchasable(int pageNumber, int pageSize);

    List<Artwork> getArtworksByPurchasableAndNotCreator(int userId, int pageNumber, int pageSize);

    Optional<Artwork> getArtworkById(int id);

    List<Artwork> getArtworkByAccountId(int id);

    List<Artwork> getTop10LikedArtworks();
    void DeleteArtwork(int id) throws Exception;

    void updateCommentCountForArtworks();

    void incrementViewCount(int artworkId);
    List<Artwork> getArtworksByTagName(String tagName) throws IOException;

    int getTheNumberOfArtworks();
}
