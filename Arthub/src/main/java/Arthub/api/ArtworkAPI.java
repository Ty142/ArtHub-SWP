package Arthub.api;

import Arthub.converter.ArtworkConverter;
import Arthub.dto.ArtworkDTO;
import Arthub.converter.ArtworkConverter;
import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;
import Arthub.entity.Tag;
import Arthub.repository.ArtworkRepository;
import Arthub.repository.TagArtRepository;
import Arthub.service.ArtworkService;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artworks")
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
        if (artwork.getImageFile() == null) {
            throw new RuntimeException("Bạn phải upload ảnh cho ảnh sự kiện!");
        }
        try {
            byte[] imgByte = imageUtils.decodeBase64(artwork.getImageFile());
            artwork.setImageFile(userService.uploadAvatar(imgByte, 3, ""));
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
            return ResponseEntity.noContent().build();
        }

        System.out.println("✅ Trả về " + artworks.size() + " artworks.");
        return ResponseEntity.ok(artworks);
    }

    @GetMapping("/GetArtworksWithPaymentStatus/")
    public ResponseEntity<List<Artwork>> getArtworksPurchasable(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        System.out.println("📥 Nhận yêu cầu lấy tất cả artworks với purchasable = 1...");

        List<Artwork> artworks = artworkService.getArtworksByPurchasable(pageNumber, pageSize);

        if (artworks.isEmpty()) {
            System.out.println("⚠️ Không tìm thấy artworks nào với purchasable = 1!");
            return ResponseEntity.noContent().build();
        }

        System.out.println("✅ Trả về " + artworks.size() + " artworks.");
        return ResponseEntity.ok(artworks);
    }


    @GetMapping("/GetArtworksWithPaymentStatus")
    public ResponseEntity<List<Artwork>> getArtworksPurchasable(
            @RequestParam(required = false) Integer userId,
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "8") Integer pageSize) {
        System.out.println("📥 Nhận yêu cầu lấy tất cả artworks với purchasable = 1...");

        List<Artwork> artworks;
        if (userId == null) {
            artworks = artworkService.getArtworksByPurchasable(pageNumber, pageSize);
        } else {
            artworks = artworkService.getArtworksByPurchasableAndNotCreator(userId, pageNumber, pageSize);
        }

        if (artworks.isEmpty()) {
            System.out.println("⚠️ Không tìm thấy artworks nào với purchasable = 1!");
            return ResponseEntity.noContent().build();
        }

        System.out.println("✅ Trả về " + artworks.size() + " artworks.");
        return ResponseEntity.ok(artworks);
    }

    /**
     * API lấy thông tin chi tiết của một artwork theo ID
     *
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
            return ResponseEntity.noContent().build();
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

    @GetMapping("/{id}/tags")
    public ResponseEntity<List<Tag>> getAllTagArtByArtworkId(@PathVariable int id) {
        List<Tag> tags = tagArtRepository.getAllTagArtByArtworkId(id);
        if (tags.isEmpty()) {
            System.out.println("⚠️ Không tìm thấy Tag");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tags);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtworkById(@PathVariable int id) throws Exception {
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

    @PutMapping("/update")
    public ResponseEntity<Artwork> updateArtwork(@RequestBody ArtworkDTO artworkDTO) throws SQLException {
        Artwork updatedArtwork = artworkConverter.convertArtworkDTOToArtworkEntity(artworkDTO);
        tagArtRepository.deleteTagArtByArtId(updatedArtwork.getArtworkID());
        artworkRepository.UpdateArtwork(updatedArtwork);
        tagArtRepository.addTagArtUserIdAndTagId(updatedArtwork.getArtworkTags(), updatedArtwork.getArtworkID());
        return ResponseEntity.ok(updatedArtwork);

    }


    @PutMapping("/update-comments-count")
    public ResponseEntity<String> updateCommentCount() {
        artworkService.updateCommentCountForArtworks();
        return ResponseEntity.ok("Comments count updated successfully.");
    }

    @PutMapping("/increment-views/{artworkId}/{currentUserId}")
    public ResponseEntity<Void> incrementViews(
            @PathVariable int artworkId,
            @PathVariable int currentUserId
    ) {
        Optional<Artwork> optionalArtwork = artworkService.getArtworkById(artworkId);
        if (!optionalArtwork.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Artwork artwork = optionalArtwork.get();
        if (artwork.getCreatorID() != currentUserId) {
            artworkService.incrementViewCount(artworkId);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Tag")
    public ResponseEntity<List<Artwork>> getAllArtworksByTagName(@RequestParam String tagName) throws IOException {
        System.out.println("�� Nhận yêu cầu lấy artwork theo từ khóa: " + tagName);
        List<Artwork> artworks = artworkService.getArtworksByTagName(tagName);
        return ResponseEntity.ok(artworks);
    }

}
