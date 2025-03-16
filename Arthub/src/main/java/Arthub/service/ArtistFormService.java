package Arthub.service;

import Arthub.dto.ArtistFormDTO;
import Arthub.entity.ArtistForm;
import java.util.List;

public interface ArtistFormService {
    void createArtistForm(ArtistForm artistForm);
    List<ArtistForm> getAllArtistForms();

    ArtistForm getArtistFormById(int id);

    void AcceptArtistForm(int id);
    void RejectArtistForm(Long id);

    List<ArtistFormDTO> getArtistFormsUpgrade();
}
