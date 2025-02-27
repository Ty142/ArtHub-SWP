package Arthub.repository.impl;


import Arthub.entity.Report;
import Arthub.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addReport(Report report) {
        String sql = "INSERT INTO Report (reporterId, reportedId, artworkId, description, createdDate, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                report.getReporterId(),
                report.getReportedId(),
                report.getArtworkId() != null ? report.getArtworkId() : null, // Xử lý null
                report.getDescription(),
                Timestamp.valueOf(report.getCreatedDate()),
                report.getStatus());
    }

    @Override
    public ArrayList<Report> getAllReports() {
        String sql = "SELECT * FROM Report";
        ArrayList<Report> reports = new ArrayList<>();
        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Report report = new Report();
                report.setReportId(resultSet.getInt("ReportID"));
                report.setReporterId(resultSet.getInt("ReporterID"));
                report.setReportedId(resultSet.getInt("ReportedID"));
                report.setArtworkId(resultSet.getInt("ArtworkID"));
                report.setDescription(resultSet.getString("Description"));

                // Fix lỗi chuyển đổi Date -> LocalDateTime
                Timestamp timestamp = resultSet.getTimestamp("CreatedDate");
                if (timestamp != null) {
                    report.setCreatedDate(timestamp.toLocalDateTime());
                }

                report.setStatus(resultSet.getInt("Status"));
                reports.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }

}

