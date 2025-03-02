package Arthub.repository.impl;

import Arthub.entity.Payment;
import Arthub.repository.PaymentRepository;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public boolean save(Payment payment) {
        String sql = "INSERT INTO [Arthub].[dbo].[Payment] ([Amount], [CreatedAt], [UserID], [Status], [TransCode]) VALUES (?,?,?,?,?)";
        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, payment.getAmount());
            statement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            statement.setInt(3, payment.getUserId());
            statement.setByte(4, (byte)1);
            statement.setString(5, payment.getTransCode());
            statement.executeUpdate();
            return true;
        } catch (SQLServerException e) {
            if (e.getMessage().contains("UQ__Payment")) { // Kiểm tra lỗi vi phạm UNIQUE KEY
                System.out.println("Lỗi: Mã giao dịch đã tồn tại!");
            } else {
                e.printStackTrace();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
