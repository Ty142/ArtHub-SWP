package Arthub.repository.impl;

import Arthub.dto.ArtistFormDTO;
import Arthub.entity.ArtistForm;
import Arthub.repository.ArtistFormRepository;
import Arthub.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArtistFormRepositoryImpl implements ArtistFormRepository {

    @Autowired
    UserRepository userRepository;

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

    @Override
    public ArtistForm findById(int id) {
        String sql = "SELECT * FROM ArtistForm where [FormID] = ?";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ArtistForm artistForm = new ArtistForm();
                artistForm.setFormId(rs.getInt("FormID"));
                artistForm.setDescriptions(rs.getString("Descriptions"));
                artistForm.setStatus(rs.getInt("Status"));
                artistForm.setDateCreation(rs.getTimestamp("DateCreation"));
                artistForm.setUserId(rs.getInt("UserID"));
                return artistForm;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }return null;

    }

    @Override
    public void AcceptArtist(int id) {
        String sql = "Update ArtistForm set status = 1 where FormID =?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }   

    @Override
    public void RejectArtist(Long id) {
            String sql = "Delete from ArtistForm? where FormID = ?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ArtistFormDTO> findByToUpgrade() {
        String sql = "SELECT * FROM ArtistForm";
        List<ArtistFormDTO> artistFormDTOList = new ArrayList<>();

        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArtistFormDTO artistFormDTO = new ArtistFormDTO();
                artistFormDTO.setFormId(rs.getInt("FormID"));
                artistFormDTO.setDescriptions(rs.getString("Descriptions"));
                artistFormDTO.setStatus(rs.getInt("Status"));
                artistFormDTO.setDateCreation(rs.getTimestamp("DateCreation"));
                artistFormDTO.setUserId(rs.getInt("UserID"));
                int RankID = userRepository.getUserById(artistFormDTO.getUserId()).getRankId();
                artistFormDTO.setRankID(RankID);
                artistFormDTOList.add(artistFormDTO);
            }
            return artistFormDTOList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
