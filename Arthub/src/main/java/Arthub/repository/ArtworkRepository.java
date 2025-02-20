package Arthub.repository;

import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;

import java.util.List;
import java.util.Optional;

public interface ArtworkRepository {

    void saveArtPicture(int id, String Artwork);

    int addArtwork(Artwork artwork);

    List<Artwork> getArtworks();

    Optional<Artwork> getArtworkById(int id);

    List<Artwork> getTop10LikedArtworks();

    String findArtworkPictureByArtworkId(int id);
}
