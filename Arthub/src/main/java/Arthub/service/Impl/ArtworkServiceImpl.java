package Arthub.service.Impl;

import Arthub.entity.Artwork;
import Arthub.entity.TagArt;
import Arthub.repository.ArtworkRepository;
import Arthub.repository.TagArtRepository;
import Arthub.service.ArtworkService;
import Arthub.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    private Cloudinary cloudinary;

    @Autowired
    public ArtworkServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    @Autowired
     ArtworkRepository artworkRepository;
    @Autowired
    TagArtRepository tagArtRepository;
    @Autowired
    UserService userService;

    utils.ImageUtils imageUtils = new utils.ImageUtils();
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
    public void DeleteArtwork(int id) throws Exception {
        String artworkPath = artworkRepository.findArtworkPictureByArtworkId(id);
        String idArtworks = imageUtils.extractPublicId(artworkPath);
        userService.deleteArtworkAtCloudinary(idArtworks);
        tagArtRepository.deleteTagArtByArtId( id);
        artworkRepository.deleteArtworkByArtworkId(id);
    }
}

