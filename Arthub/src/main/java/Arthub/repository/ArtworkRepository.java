package Arthub.repository;

import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;

import java.util.List;

public interface ArtworkRepository {

    void saveArtPicture(int id, String Artwork);

    void addArtwork(Artwork artwork);

    List<Artwork> getArtworks();
}
