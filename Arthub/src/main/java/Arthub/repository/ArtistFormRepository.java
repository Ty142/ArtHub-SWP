package Arthub.repository;

import Arthub.dto.ArtistFormDTO;
import Arthub.entity.ArtistForm;
import java.util.List;

public interface ArtistFormRepository {
    void save(ArtistForm artistForm);
    List<ArtistForm> findAll();

    ArtistForm findById(int id);

    void AcceptArtist(int id);

    void RejectArtist(Long id);

    List<ArtistFormDTO> findByToUpgrade();
}
