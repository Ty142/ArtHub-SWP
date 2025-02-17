package Arthub.repository;

import Arthub.dto.ArtworkDTO;

public interface ArtworkRepository {

    void saveArtPicture(int id, String Artwork);

    void addArtwork(ArtworkDTO artworkDTO);
}
