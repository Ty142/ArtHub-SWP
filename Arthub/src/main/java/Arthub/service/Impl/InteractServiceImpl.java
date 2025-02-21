package Arthub.service.Impl;

import Arthub.entity.Artwork;
import Arthub.repository.InteractRepository;
import Arthub.service.InteractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractServiceImpl implements InteractService {
    private final InteractRepository interactRepository;

    public InteractServiceImpl(InteractRepository interactRepository) {
        this.interactRepository = interactRepository;
    }

    @Override
    public boolean toggleFavourite(int userID, int artworkID) {
        return interactRepository.toggleFavourite(userID, artworkID);
    }

    @Override
    public boolean isFavourite(int userID, int artworkID) {
        return interactRepository.isFavourite(userID, artworkID);
    }

    @Override
    public List<Artwork> getFavouriteArtworks(int userID) {
        return interactRepository.getFavouriteArtworks(userID);
    }
}
