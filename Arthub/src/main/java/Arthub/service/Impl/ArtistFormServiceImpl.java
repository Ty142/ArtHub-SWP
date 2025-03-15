package Arthub.service.Impl;

import Arthub.entity.ArtistForm;
import Arthub.repository.ArtistFormRepository;
import Arthub.service.ArtistFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistFormServiceImpl implements ArtistFormService {

    private static final Logger logger = LoggerFactory.getLogger(ArtistFormServiceImpl.class);
    private final ArtistFormRepository artistFormRepository;

    public ArtistFormServiceImpl(ArtistFormRepository artistFormRepository) {
        this.artistFormRepository = artistFormRepository;
    }

    @Override
    public void createArtistForm(ArtistForm artistForm) {
        try {
            logger.info("Lưu ArtistForm vào database: {}", artistForm);
            artistFormRepository.save(artistForm);
            logger.info("Lưu thành công!");
        } catch (Exception e) {
            logger.error("Lỗi khi lưu ArtistForm", e);
            throw new RuntimeException("Không thể lưu ArtistForm: " + e.getMessage());
        }
    }

    @Override
    public List<ArtistForm> getAllArtistForms() {
        try {
            logger.info("Lấy tất cả ArtistForm từ database");
            return artistFormRepository.findAll();
        } catch (Exception e) {
            logger.error("Lỗi khi lấy ArtistForm", e);
            throw new RuntimeException("Không thể lấy danh sách ArtistForm: " + e.getMessage());
        }
    }
}
