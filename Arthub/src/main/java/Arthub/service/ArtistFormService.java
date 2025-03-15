package Arthub.service;

import Arthub.entity.ArtistForm;
import java.util.List;

public interface ArtistFormService {
    void createArtistForm(ArtistForm artistForm);
    List<ArtistForm> getAllArtistForms();

    ArtistForm getArtistFormById(Long id);

    void AcceptArtistForm(Long id);
    void RejectArtistForm(Long id);
}
