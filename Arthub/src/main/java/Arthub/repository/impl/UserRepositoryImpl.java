package Arthub.repository.impl;

import Arthub.converter.UserConverter;
import Arthub.dto.UserDTO;
import Arthub.entity.User;
import org.springframework.stereotype.Repository;
import Arthub.repository.UserRepository;
import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {


    @Override
    public ArrayList<User> getAllUsers() {
        String sql = "SELECT * FROM [User]";
            List<User> users = new ArrayList<>();
        ConnectUtils db = ConnectUtils.getInstance();
        try {
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setPhoneNumber(resultSet.getString("PhoneNumber"));
                user.setAddress(resultSet.getString("Address"));
                user.setBiography(resultSet.getString("Biography"));
                user.setCoins(resultSet.getDouble("Coins"));
                user.setCreatedAt(resultSet.getString("CreatedAt"));
                user.setRankId(resultSet.getInt("RankID"));
                user.setRoleId(resultSet.getInt("RoleID"));
                user.setDateOfBirth(resultSet.getDate("DateOfBirth"));
                user.setLastLogin(resultSet.getTimestamp("LastLogin"));
                user.setAccountId(resultSet.getInt("AccountID"));
                user.setProfilePicture(resultSet.getString("ProfilePicture"));
                user.setBackgroundPicture(resultSet.getString("BackgroundPicture"));
                user.setFollowCounts(resultSet.getInt("FollowCounts"));
                user.setFollower(resultSet.getInt("FollowerCount"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(users);
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

    public User getUserByAccountId(int accountId) {
        String sql = "SELECT * FROM [User] WHERE AccountID = ?";
        ConnectUtils db = ConnectUtils.getInstance();
        User user = null;

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = mapResultSetToUser(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Hàm ánh xạ `ResultSet` sang đối tượng `User`.
     */
    private User mapResultSetToUser(ResultSet resultSet) throws Exception {
        User user = new User();
        user.setUserId(resultSet.getInt("UserID"));
        user.setFirstName(resultSet.getString("FirstName"));
        user.setLastName(resultSet.getString("LastName"));
        user.setPhoneNumber(resultSet.getString("PhoneNumber"));
        user.setAddress(resultSet.getString("Address"));
        user.setBiography(resultSet.getString("Biography"));
        user.setCoins(resultSet.getDouble("Coins"));
        user.setCreatedAt(resultSet.getString("CreatedAt"));
        user.setRankId(resultSet.getInt("RankID"));
        user.setRoleId(resultSet.getInt("RoleID"));
        user.setDateOfBirth(resultSet.getDate("DateOfBirth"));
        user.setLastLogin(resultSet.getDate("LastLogin"));
        user.setProfilePicture(resultSet.getString("ProfilePicture"));
        user.setBackgroundPicture(resultSet.getString("BackgroundPicture"));
        user.setFollowCounts(resultSet.getInt("FollowCounts"));
        user.setFollower(resultSet.getInt("FollowerCount"));
        user.setAccountId(resultSet.getInt("AccountID"));
        return user;
    }

}
