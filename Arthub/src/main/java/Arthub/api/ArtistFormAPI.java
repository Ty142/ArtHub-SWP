package Arthub.api;

import Arthub.entity.ArtistForm;
import Arthub.service.ArtistFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artist-form")
@CrossOrigin(origins = "http://localhost:3000") // Đảm bảo CORS cho React
public class ArtistFormAPI {

    private static final Logger logger = LoggerFactory.getLogger(ArtistFormAPI.class);
    private final ArtistFormService artistFormService;

    public ArtistFormAPI(ArtistFormService artistFormService) {
        this.artistFormService = artistFormService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createArtistForm(@RequestBody ArtistForm artistForm) {
        try {
            logger.info("📩 Nhận yêu cầu tạo mới ArtistForm: {}", artistForm);

            if (artistForm.getUserId() == 0) {
                return ResponseEntity.badRequest().body("❌ UserID không hợp lệ.");
            }

            artistFormService.createArtistForm(artistForm);
            logger.info("✅ ArtistForm đã được lưu vào database thành công!");

            return ResponseEntity.ok("ArtistForm đã được tạo thành công!");
        } catch (Exception e) {
            logger.error("❌ Lỗi khi tạo ArtistForm", e);
            return ResponseEntity.internalServerError().body("Đã xảy ra lỗi: " + e.getMessage());
        }
    }
}
