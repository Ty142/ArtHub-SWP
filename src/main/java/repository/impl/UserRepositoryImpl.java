package repository.impl;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import repository.UserRepository;
import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryImpl implements UserRepository {
    public User getUserByIdAccount(int id) {
        String sql = "SELECT u.* FROM User u where u.AccountId = ?";
        User user = new User();
        try {
            Connection conn = ConnectUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("UserId"));
            }

        }catch (SQLException e) {
            // Handle the exception
        }
    return user;
    }
}
