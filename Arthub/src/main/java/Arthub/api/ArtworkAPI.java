package Arthub.api;

import Arthub.converter.ArtworkConverter;
import Arthub.dto.ArtworkDTO;
import Arthub.converter.ArtworkConverter;
import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;
import Arthub.repository.ArtworkRepository;
import Arthub.repository.TagArtRepository;
import Arthub.service.ArtworkService;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artworks") // Base URL cho API
public class ArtworkAPI {

    @Autowired
     ArtworkService artworkService;
    @Autowired
    UserService userService;
    @Autowired
    ArtworkRepository artworkRepository;
    @Autowired
    ArtworkConverter artworkConverter;
    @Autowired
    TagArtRepository tagArtRepository;

    utils.ImageUtils imageUtils = new utils.ImageUtils();

    @PostMapping("/")
    public Artwork createArtImg(@RequestBody ArtworkDTO artworkDTO) {
        Artwork artwork = artworkConverter.convertArtworkDTOToArtworkEntity(artworkDTO);
        try {
            byte[] imgByte = imageUtils.decodeBase64(artwork.getImageFile());
            artwork.setImageFile(userService.uploadAvatar(imgByte, 3,""));
            int id = artworkRepository.addArtwork(artwork);
            tagArtRepository.addTagArtUserIdAndTagId(artwork.getArtworkTags(), id);
            Optional<Artwork> artworkOpt = artworkRepository.getArtworkById(id);
            if (artworkOpt.isPresent()) {
                return artworkOpt.get();
            } else {
                throw new RuntimeException("Artwork không được tìm thấy sau khi tạo");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload ảnh: " + e.getMessage(), e);
        }
    }
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

    @GetMapping("/accountID/{id}")
    public ResponseEntity<List<Artwork>> getArtworkByAccountId(@PathVariable int id) {
        System.out.println("📥 Nhận yêu cầu lấy artwork với ID: " + id);

        List<Artwork> artworks = artworkService.getArtworkByAccountId(id);
        if (artworks.isEmpty()) {
            System.out.println("⚠️ Không tìm thấy artworks!");
            return ResponseEntity.noContent().build(); // Trả về HTTP 204 nếu không có dữ liệu
        }

        System.out.println("✅ Trả về " + artworks.size() + " artworks.");
        return ResponseEntity.ok(artworks);
    }


    @GetMapping("/Top10Liked")
    public ResponseEntity<List<Artwork>> getTop10LikedArtworks() {
        List<Artwork> artworks = artworkService.getTop10LikedArtworks();
        if (artworks.isEmpty()) {
            System.out.println("⚠ API /api/Artworks/Top10Liked: None artwork.");
            return ResponseEntity.noContent().build();
        }
        System.out.println("✅ Trả về " + artworks.size() + " artworks.");
        return ResponseEntity.ok(artworks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtworkById(@PathVariable int id) {
        System.out.println("�� Nhận yêu cầu xóa artwork với ID: " + id);

        Optional<Artwork> artwork = artworkService.getArtworkById(id);
        if (artwork.isPresent()) {
            artworkService.DeleteArtwork(id);
            System.out.println("�� Xóa artwork với ID: " + id + " thành công.");
            return ResponseEntity.noContent().build();
        } else {
            System.out.println("�� Không tìm thấy artwork với ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

}
