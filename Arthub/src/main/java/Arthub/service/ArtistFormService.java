package Arthub.service;

import Arthub.entity.ArtistForm;
import java.util.List;

public interface ArtistFormService {
    void createArtistForm(ArtistForm artistForm);
    List<ArtistForm> getAllArtistForms();
}
