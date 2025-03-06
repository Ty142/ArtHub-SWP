package Arthub.repository.impl;

import Arthub.entity.Commission;
import Arthub.repository.CommissionRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommissionRepositoryImpl implements CommissionRepository {

    @Override
    public List<Commission> findAllCommissions() {
        List<Commission> commissions = new ArrayList<>();
        String sql = "SELECT * FROM Commission";

        try (Connection connection = utils.ConnectUtils.getInstance().openConection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Commission commission = new Commission();
                commission.setCommissionID(resultSet.getInt("CommissionID"));
                commission.setRequestor(resultSet.getInt("Requestor"));
                commission.setReceiver(resultSet.getInt("Receiver"));
                commission.setPhoneNumber(resultSet.getString("PhoneNumber"));
                commission.setEmail(resultSet.getString("Email"));
                commission.setDescription(resultSet.getString("Description"));
                commission.setAccept(resultSet.getObject("Accept", Boolean.class)); // Trả về null nếu NULL
                commission.setCreationDate(resultSet.getDate("CreationDate"));
                commission.setAcceptanceDate(resultSet.getTimestamp("AcceptanceDate")); // Sử dụng Timestamp
                commission.setCompletionDate(resultSet.getTimestamp("CompletionDate")); // Sử dụng Timestamp
                commission.setProgress(resultSet.getInt("progress"));
                commission.setMessage(resultSet.getString("Message")); // Lấy giá trị Message
                commissions.add(commission);
            }

            System.out.println("✅ Lấy thành công " + commissions.size() + " commissions từ database.");
        } catch (SQLException e) {
            System.err.println("❌ Lỗi SQL khi lấy commissions: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error fetching commissions from database: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Lỗi không xác định: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error connecting to database: " + e.getMessage());
        }
        return commissions;
    }

    @Override
    public void updateCommissionAccept(int commissionId, Boolean accept, String message, Timestamp acceptanceDate) {
        String sql = "UPDATE Commission SET Accept = ?, Progress = ?, AcceptanceDate = ?, Message = ? WHERE CommissionID = ?";
        try (Connection connection = utils.ConnectUtils.getInstance().openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (accept != null) {
                statement.setBoolean(1, accept);
                statement.setInt(2, accept ? 1 : 0); // Nếu accept = true, progress = 1; nếu false, progress = 0
            } else {
                statement.setNull(1, Types.BIT);
                statement.setInt(2, 0); // Progress = 0 khi accept = null
            }

            if (acceptanceDate != null) {
                statement.setTimestamp(3, acceptanceDate); // Sử dụng trực tiếp Timestamp
            } else if (accept != null && accept) {
                statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            } else {
                statement.setNull(3, Types.TIMESTAMP);
            }

            statement.setString(4, message);
            statement.setInt(5, commissionId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No commission found with ID: " + commissionId);
            }

            System.out.println("✅ Cập nhật commission thành công với ID: " + commissionId);
        } catch (SQLException e) {
            System.err.println("❌ Lỗi SQL khi cập nhật commission: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error updating commission in database: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Lỗi không xác định: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error connecting to database: " + e.getMessage());
        }
    }

    @Override
    public void updateCommissionProgress(int commissionId, int progress, Timestamp completionDate) {
        String sql = "UPDATE Commission SET Progress = ?, CompletionDate = ? WHERE CommissionID = ?";
        try (Connection connection = utils.ConnectUtils.getInstance().openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            System.out.println("Updating commission progress - commissionId: " + commissionId + ", progress: " + progress + ", completionDate: " + completionDate);
            statement.setInt(1, progress);
            if (completionDate != null) {
                statement.setTimestamp(2, completionDate);
            } else {
                statement.setNull(2, Types.TIMESTAMP);
            }
            statement.setInt(3, commissionId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No commission found with ID: " + commissionId);
            }

            System.out.println("✅ Cập nhật progress thành công với ID: " + commissionId + ", Progress: " + progress + ", CompletionDate: " + completionDate);
        } catch (SQLException e) {
            System.err.println("❌ Lỗi SQL khi cập nhật progress: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error updating commission progress in database: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Lỗi không xác định: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error connecting to database: " + e.getMessage());
        }
    }
}