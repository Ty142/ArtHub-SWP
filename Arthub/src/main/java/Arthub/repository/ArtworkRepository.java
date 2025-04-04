package Arthub.repository;

import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ArtworkRepository {

    void saveArtPicture(int id, String Artwork);

    int addArtwork(Artwork artwork);

    List<Artwork> getArtworks();

    List<Artwork> getArtworksByPurchasable(int pageNumber, int pageSize);

    List<Artwork> getArtworksByPurchasableAndNotCreator(int userId, int pageNumber, int pageSize);

    Optional<Artwork> getArtworkById(int id);

    List<Artwork> getTop10LikedArtworks();

    String findArtworkPictureByArtworkId(int id);

    List<Artwork> getArtworkByAccountId(int id);

    void deleteArtworkByArtworkId(int artworkId);

    void UpdateArtwork(Artwork artwork) throws SQLException;

    void incrementViewCount(int artworkId);

    List<Artwork> GetAllArtworksByTagName(String tagName);

    int getTheNumberOfArtworks();
}
