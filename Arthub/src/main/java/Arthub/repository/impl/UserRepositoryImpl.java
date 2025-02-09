package Arthub.repository.impl;

import Arthub.converter.UserConverter;
import Arthub.dto.UserDTO;
import Arthub.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import Arthub.repository.UserRepository;
import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class UserRepositoryImpl implements UserRepository {
    public User getUserByIdAccount(int id) {
        String sql = "SELECT u.* FROM User u where u.AccountId = ?";
        User user = new User();
        ConnectUtils db = ConnectUtils.getInstance();
        try {
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setCoins(resultSet.getDouble("coins"));
                user.setAddress(resultSet.getString("address"));

            }

        }catch (Exception e) {
            // Handle the exception
        }
    return user;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return null;
    }

    @Override
    public void addUserAccount(UserDTO userDTO) throws SQLException {
        String sql ="Insert into users(firstname, lastname, address, phonenumber, biography) values (?, ?, ?, ?, ?)";
        User user = new UserConverter().ConvertUserDTOToUserEntity(userDTO);
        ConnectUtils db = ConnectUtils.getInstance();
        try {
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
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
        }

    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM [dbo].[User] WHERE UserID = ?";
        User user = null;
        ConnectUtils db = ConnectUtils.getInstance();
        try {
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setUserId(resultSet.getInt("UserId"));
                    user.setFirstName(resultSet.getString("FirstName"));
                    user.setLastName(resultSet.getString("LastName"));
                    user.setPhoneNumber(resultSet.getString("PhoneNumber"));
                    user.setAddress(resultSet.getString("Address"));
                    user.setBiography(resultSet.getString("Biography"));
                    user.setCoins(resultSet.getDouble("Coins"));
                    user.setCreatedAt(resultSet.getString("CreatedAt"));
                    user.setRankId(resultSet.getInt("RankId"));
                    user.setRoleId(resultSet.getInt("RoleId"));
                    user.setDateOfBirth(resultSet.getDate("DateOfBirth"));
                    user.setLastLogin(resultSet.getDate("LastLogin"));
                    user.setAccountId(resultSet.getInt("AccountId"));
                    user.setProfilePicture(resultSet.getString("ProfilePicture"));
                    user.setBackgroundPicture(resultSet.getString("BackgroundPicture"));
                    user.setFollowCounts(resultSet.getInt("FollowCounts"));
                    user.setFollower(resultSet.getInt("FollowerCount"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching user by id", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return user;
    }


    @Override
    public void updateAvatar(int id, String avatar) {
        String sql = "UPDATE [dbo].[User] set ProfilePicture = ? WHERE UserId = ?";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, avatar);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
