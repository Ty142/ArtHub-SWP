package Arthub.api;

import Arthub.entity.Artwork;
import Arthub.service.ArtworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Artworks")
public class ArtworkAPI {

    private final ArtworkService artworkService;

    public ArtworkAPI(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping("/Top10Liked")
    public ResponseEntity<List<Artwork>> getTop10LikedArtworks() {
        List<Artwork> artworks = artworkService.getTop10LikedArtworks();

        if (artworks.isEmpty()) {
            System.out.println("⚠ API /api/Artworks/Top10Liked: None artwork.");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(artworks);
    }
}
