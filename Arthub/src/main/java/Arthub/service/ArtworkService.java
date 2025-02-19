package Arthub.service;

import Arthub.entity.Artwork;
import Arthub.entity.TagArt;

public interface ArtworkService {
    void PushArtwork(Artwork artwork, TagArt tagArt);
}
