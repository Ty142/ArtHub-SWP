package Arthub.repository;

import Arthub.entity.ArtistForm;
import java.util.List;

public interface ArtistFormRepository {
    void save(ArtistForm artistForm);
    List<ArtistForm> findAll();

    ArtistForm findById(Long id);

    void AcceptArtist(Long id);

    void RejectArtist(Long id);
}
