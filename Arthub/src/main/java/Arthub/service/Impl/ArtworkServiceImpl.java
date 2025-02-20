package Arthub.service.Impl;

import Arthub.entity.Artwork;
import Arthub.entity.TagArt;
import Arthub.repository.ArtworkRepository;
import Arthub.repository.TagArtRepository;
import Arthub.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
     ArtworkRepository artworkRepository;
    @Autowired
    TagArtRepository tagArtRepository;
    @Override
    public void PushArtwork(Artwork artwork, TagArt tagArt) {

    }

    @Override
    public void PushArtwork(Artwork artwork) {
        // Chưa triển khai
    }

    @Override
    public List<Artwork> getArtworks() {
        return artworkRepository.getArtworks();
    }

    @Override
    public Optional<Artwork> getArtworkById(int id) {
        return artworkRepository.getArtworkById(id);
    }

    @Override
    public List<Artwork> getArtworkByAccountId(int id) {
        return artworkRepository.getArtworkByAccountId(id);
    }

    public ArtworkServiceImpl(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @Override
    public List<Artwork> getTop10LikedArtworks() {
        return artworkRepository.getTop10LikedArtworks();
    }

    @Override
    public void DeleteArtwork(int id) {
        tagArtRepository.deleteTagArtByArtId( id);
        artworkRepository.deleteArtworkByArtworkId(id);
    }
}

