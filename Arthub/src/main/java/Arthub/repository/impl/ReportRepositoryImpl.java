package Arthub.repository.impl;


import Arthub.entity.Report;
import Arthub.repository.AccountRepository;
import Arthub.repository.ReportRepository;
import Arthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

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
                int AccountID = userRepository.getUserById(report.getReportedId()).getAccountId();
                int status = accountRepository.getAccountById(AccountID).getStatus();
                report.setStatus(status);
                reports.add(report);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public void LockAccount(int AccountID) {
        String sql = "UPDATE Account set Status =? WHERE AccountID = ?";
         try{
             utils.ConnectUtils dc = utils.ConnectUtils.getInstance();
             Connection connection = dc.openConection();
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, 0);
             statement.setInt(2, AccountID);
             statement.executeUpdate();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    @Override
    public void UnlockAccount(int AccountID) {
        String sql = "UPDATE Account set Status =? WHERE AccountID = ?";
        try {
            utils.ConnectUtils dc = utils.ConnectUtils.getInstance();
            Connection connection = dc.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1);
            statement.setInt(2, AccountID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

