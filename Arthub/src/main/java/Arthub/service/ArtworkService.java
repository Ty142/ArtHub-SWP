package Arthub.service;

import Arthub.entity.Artwork;
import Arthub.entity.TagArt;

import java.util.List;
import java.util.Optional;

public interface ArtworkService {
    void PushArtwork(Artwork artwork, TagArt tagArt);

    void PushArtwork(Artwork artwork);

    List<Artwork> getArtworks();
    Optional<Artwork> getArtworkById(int id);
    List<Artwork> getArtworkByAccountId(int id);
    List<Artwork> getTop10LikedArtworks();
    void DeleteArtwork(int id);
}
