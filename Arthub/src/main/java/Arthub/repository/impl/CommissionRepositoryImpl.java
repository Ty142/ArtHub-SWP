package Arthub.repository.impl;

import Arthub.entity.Commission;
import Arthub.repository.CommissionRepository;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

@Repository
public class CommissionRepositoryImpl implements CommissionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Commission commission) {
        String sql = "INSERT INTO [Arthub].[dbo].[Commission] ([Requestor], [Receiver], [PhoneNumber], [Email], [Description], [Accept], [CreationDate], [AcceptanceDate], [CompletionDate], [Progress], [Message]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, commission.getRequestor());
            statement.setInt(2, commission.getReceiver());
            statement.setString(3, commission.getPhoneNumber());
            statement.setString(4, commission.getEmail());
            statement.setString(5, commission.getDescription());
            statement.setBoolean(6, false);
            statement.setTimestamp(7, new Timestamp(new Date().getTime()));
            statement.setNull(8, java.sql.Types.TIMESTAMP);
            statement.setNull(9, java.sql.Types.TIMESTAMP);
            statement.setInt(10, 0);
            statement.setNull(11, java.sql.Types.VARCHAR);

            statement.executeUpdate();
            return true;
        } catch (SQLServerException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
