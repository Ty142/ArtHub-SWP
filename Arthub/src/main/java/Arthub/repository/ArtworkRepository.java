package Arthub.repository;

import Arthub.entity.Artwork;
import java.util.List;

public interface ArtworkRepository {
    List<Artwork> getTop10LikedArtworks();
}
