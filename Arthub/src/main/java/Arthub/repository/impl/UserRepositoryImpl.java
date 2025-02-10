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

    @Override
    public User saveUser(User user) throws SQLException {
        ConnectUtils db = ConnectUtils.getInstance();

        // 1️⃣ Kiểm tra xem AccountID có tồn tại trong bảng Account không
        String checkAccountSql = "SELECT COUNT(*) FROM [Account] WHERE AccountID = ?";
        try (Connection connection = db.openConection();
             PreparedStatement checkAccountStmt = connection.prepareStatement(checkAccountSql)) {

            checkAccountStmt.setInt(1, user.getAccountId());
            ResultSet accountResult = checkAccountStmt.executeQuery();

            if (accountResult.next() && accountResult.getInt(1) == 0) {
                // 🔴 AccountID không tồn tại -> Trả về lỗi
                throw new SQLException("AccountID không hợp lệ: " + user.getAccountId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi kiểm tra AccountID: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 2️⃣ Kiểm tra xem User đã tồn tại với AccountID này chưa
        User existingUser = getUserByAccountId(user.getAccountId());
        if (existingUser != null) {
            System.out.println("User đã tồn tại với AccountID: " + user.getAccountId());
            return existingUser; // Nếu đã tồn tại, trả về thông tin User cũ
        }

        // 3️⃣ Nếu AccountID hợp lệ và User chưa tồn tại, tiến hành thêm mới vào database
        String sql = "INSERT INTO [User] (FirstName, LastName, PhoneNumber, Address, Biography, Coins, CreatedAt, RankID, RoleID, " +
                "DateOfBirth, LastLogin, AccountID, ProfilePicture, BackgroundPicture, FollowCounts, FollowerCount) " +
                "VALUES (?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        User createdUser = null;
        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set các giá trị theo đúng thứ tự của bảng [User]
            statement.setString(1, user.getFirstName());                   // FirstName
            statement.setString(2, user.getLastName());                    // LastName
            statement.setString(3, user.getPhoneNumber());                 // PhoneNumber
            statement.setString(4, user.getAddress());                     // Address
            statement.setString(5, user.getBiography());                   // Biography
            statement.setDouble(6, user.getCoins());                       // Coins
            statement.setInt(7, user.getRankId());                         // RankID
            statement.setInt(8, user.getRoleId());                         // RoleID
            statement.setDate(9, user.getDateOfBirth() != null ? new java.sql.Date(user.getDateOfBirth().getTime()) : null);  // DateOfBirth
            statement.setTimestamp(10, user.getLastLogin() != null ? new java.sql.Timestamp(user.getLastLogin().getTime()) : null);  // LastLogin
            statement.setInt(11, user.getAccountId());                     // AccountID
            statement.setString(12, user.getProfilePicture());             // ProfilePicture
            statement.setString(13, user.getBackgroundPicture());          // BackgroundPicture
            statement.setInt(14, user.getFollowCounts());                  // FollowCounts
            statement.setInt(15, user.getFollower());                      // FollowerCount

            // Thực thi câu lệnh SQL
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            // Lấy UserID vừa được tạo
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                    createdUser = user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi lưu User vào database: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi không xác định khi lưu User: " + e.getMessage());
        }

        return createdUser;
    }

    @Override
    public void updateBackground(int id, String background) {
        String sql = "UPDATE [dbo].[User] set BackgroundPicture = ?  WHERE UserId = ? ";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, background);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public void updateAvatar(int id, String avatar) {
        String sql = "UPDATE [dbo].[User] set ProfilePicture = ?  WHERE UserId = ?";
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
}



