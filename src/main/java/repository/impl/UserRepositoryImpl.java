package repository.impl;

import converter.UserConverter;
import dto.UserDTO;
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
                user.setUserId(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setCoins(resultSet.getDouble("coins"));
                user.setAddress(resultSet.getString("address"));

            }

        }catch (SQLException e) {
            // Handle the exception
        }
    return user;
    }

    @Override
    public void addUserAccount(UserDTO userDTO) throws SQLException {
        String sql ="Insert into users(firstname, lastname, address, phonenumber, biography) values (?, ?, ?, ?, ?)";
        User user = new UserConverter().ConvertUserDTOToUserEntity(userDTO);
        try{
            Connection conn = ConnectUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getBiography());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setUserId(generatedKeys.getInt(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
}
