package Arthub.repository.impl;

import Arthub.entity.ArtistForm;
import Arthub.repository.ArtistFormRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArtistFormRepositoryImpl implements ArtistFormRepository {

    private static final Logger logger = LoggerFactory.getLogger(ArtistFormRepositoryImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public ArtistFormRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ArtistForm artistForm) {
        try {
            String sql = "INSERT INTO ArtistForm (Descriptions, Status, DateCreation, UserID) VALUES (?, ?, ?, ?)";
            logger.info("Thực thi query: {}", sql);
            jdbcTemplate.update(sql, artistForm.getDescriptions(), artistForm.getStatus(), artistForm.getDateCreation(), artistForm.getUserId());
            logger.info("Lưu thành công ArtistForm vào database.");
        } catch (Exception e) {
            logger.error("Lỗi khi lưu ArtistForm", e);
            throw new RuntimeException("Không thể lưu ArtistForm: " + e.getMessage());
        }
    }

    @Override
    public List<ArtistForm> findAll() {
        try {
            String sql = "SELECT * FROM ArtistForm";
            logger.info("Thực thi query: {}", sql);
            return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToArtistForm(rs));
        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách ArtistForm", e);
            throw new RuntimeException("Không thể lấy danh sách ArtistForm: " + e.getMessage());
        }
    }

    private ArtistForm mapRowToArtistForm(ResultSet rs) throws SQLException {
        return new ArtistForm(
                rs.getInt("FormID"),
                rs.getString("Descriptions"),
                rs.getInt("Status"),
                rs.getTimestamp("DateCreation"),
                rs.getInt("UserID")
        );
    }
}
