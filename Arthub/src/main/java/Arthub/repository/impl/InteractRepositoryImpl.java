package Arthub.repository.impl;

import Arthub.entity.Interact;
import Arthub.repository.InteractRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class InteractRepositoryImpl implements InteractRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Interact interact) {
        String sql = "INSERT INTO Interact (ArtworkID, UserID, ActivityID, DateOfInteract) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, interact.getArtworkID(), interact.getUserID(), interact.getActivityID(), interact.getDateOfInteract());
    }

    public List<Interact> findByArtworkIDAndUserIDAndActivityID(int artworkID, int userID, int activityID, String date) {
        String sql = "SELECT * FROM Interact WHERE ArtworkID = ? AND UserID = ? AND ActivityID = ? AND DateOfInteract = ?";
        return jdbcTemplate.query(sql, new Object[]{artworkID, userID, activityID, date}, new BeanPropertyRowMapper<>(Interact.class));
    }
}
