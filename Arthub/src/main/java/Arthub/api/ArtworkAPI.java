package Arthub.api;

import Arthub.entity.Artwork;
import Arthub.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artworks") // Base URL cho API
public class ArtworkAPI {

    @Autowired
    private ArtworkService artworkService;

    /**
     * API để lấy tất cả Artwork từ database
     * @return Danh sách Artwork dưới dạng JSON
     */
    @GetMapping("/")
    public ResponseEntity<List<Artwork>> getAllArtworks() {
        System.out.println("📥 Nhận yêu cầu lấy tất cả artworks...");

        List<Artwork> artworks = artworkService.getArtworks();
        if (artworks.isEmpty()) {
            System.out.println("⚠️ Không tìm thấy artworks!");
            return ResponseEntity.noContent().build(); // Trả về HTTP 204 nếu rỗng
        }

        System.out.println("✅ Trả về " + artworks.size() + " artworks.");
        return ResponseEntity.ok(artworks);
    }

    /**
     * API lấy thông tin chi tiết của một artwork theo ID
     * @param id ID của artwork cần lấy thông tin
     * @return Thông tin artwork hoặc HTTP 404 nếu không tìm thấy
     */
    @GetMapping("/{id}")
    public ResponseEntity<Artwork> getArtworkById(@PathVariable int id) {
        System.out.println("📥 Nhận yêu cầu lấy artwork với ID: " + id);

        Optional<Artwork> artwork = artworkService.getArtworkById(id);
        if (artwork.isPresent()) {
            System.out.println("✅ Tìm thấy artwork: " + artwork.get().getArtworkName());
            return ResponseEntity.ok(artwork.get());
        } else {
            System.out.println("❌ Không tìm thấy artwork với ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }
}
