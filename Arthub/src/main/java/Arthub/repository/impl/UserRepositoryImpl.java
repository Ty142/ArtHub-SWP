package Arthub.repository.impl;

import Arthub.entity.User;
import Arthub.repository.UserRepository;
import org.springframework.stereotype.Repository;
import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
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
