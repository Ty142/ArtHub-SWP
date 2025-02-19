package Arthub.service;

import Arthub.entity.Artwork;
import java.util.List;

public interface ArtworkService {
    List<Artwork> getTop10LikedArtworks();
}
