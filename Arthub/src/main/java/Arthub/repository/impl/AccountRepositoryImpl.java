package Arthub.repository.impl;

import Arthub.converter.UserConverter;
import Arthub.dto.AccountDTO;
import Arthub.dto.CreatorDTO;
import Arthub.entity.Account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import Arthub.entity.TypeOfRank;
import Arthub.entity.User;
import Arthub.repository.UserRepository;
import Arthub.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import Arthub.repository.AccountRepository;
import utils.ConnectUtils;


@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    RankService rankService;

    @Autowired
    UserRepository userRepository;

    @Override
    public ArrayList<Account> getAllAccounts() {
        String sql = "SELECT * FROM Account";
        ArrayList<Account> accounts = new ArrayList<>();
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getInt("AccountID"));
                account.setUserName(resultSet.getString("UserName"));
                account.setPassword(resultSet.getString("Password"));
                account.setEmail(resultSet.getString("Email"));
                account.setStatus(resultSet.getInt("Status"));
                account.setRoleID(resultSet.getInt("RoleID"));
                accounts.add(account);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM Account WHERE AccountID = ?";
        ConnectUtils db = ConnectUtils.getInstance();
        Account account = null;

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setAccountId(resultSet.getInt("AccountID"));
                account.setUserName(resultSet.getString("UserName"));
                account.setPassword(resultSet.getString("Password"));
                account.setEmail(resultSet.getString("Email"));
                account.setStatus(resultSet.getInt("Status"));
                account.setRoleID(resultSet.getInt("RoleID"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }


    @Override
    public Account getAccountByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM Account WHERE Email = ? AND Password = ?";
        ConnectUtils db = ConnectUtils.getInstance();
        Account account = null;

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, hashPassword(password));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setAccountId(resultSet.getInt("AccountID"));
                account.setUserName(resultSet.getString("UserName"));
                account.setPassword(resultSet.getString("Password"));
                account.setEmail(resultSet.getString("Email"));
                account.setStatus(resultSet.getInt("Status"));
                account.setRoleID(resultSet.getInt("RoleID"));
                // ✅ Nếu đăng nhập thành công -> Cập nhật LastLogin
                updateLastLogin(account.getAccountId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Lỗi khi kiểm tra tài khoản: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    private void updateLastLogin(int accountId) {
        String sql = "UPDATE [User] SET LastLogin = CURRENT_TIMESTAMP WHERE AccountID = ?";
        try (Connection connection = ConnectUtils.getInstance().openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, accountId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ LastLogin đã cập nhật thành công cho AccountID: " + accountId);
            } else {
                System.out.println("⚠️ Không tìm thấy User với AccountID: " + accountId);
            }

        } catch (SQLException e) {
            throw new RuntimeException("❌ Lỗi khi cập nhật LastLogin: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Hàm ánh xạ `ResultSet` sang đối tượng `Account`.
     */
    private Account mapResultSetToAccount(ResultSet resultSet) throws Exception {
        Account account = new Account();
        account.setAccountId(resultSet.getInt("AccountID"));
        account.setUserName(resultSet.getString("UserName"));
        account.setEmail(resultSet.getString("Email"));
        account.setPassword(resultSet.getString("Password"));
        account.setStatus(resultSet.getInt("Status"));
        account.setRoleID(resultSet.getInt("RoleID"));
        return account;
    }


    public static String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    @Override
    public boolean createAccount(AccountDTO accountDTO) {
        try {
            insertAccount(accountDTO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Account getAccountByEmail(String email) {
        String sql = "SELECT * FROM Account WHERE Email = ?";
        ConnectUtils db = ConnectUtils.getInstance();
        Account account = null;
        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                account = mapResultSetToAccount(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public void insertAccount(AccountDTO accountDTO) {
        if (accountDTO.getEmail() == null || accountDTO.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        String userName = accountDTO.getEmail().split("@")[0];

        String sql = "INSERT INTO Account (UserName, RoleID, Email, Password, Status) VALUES (?, ?, ?, ?, ?)";
        ConnectUtils db = ConnectUtils.getInstance();
        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userName);
            statement.setInt(2, 2);
            statement.setString(3, accountDTO.getEmail());
            statement.setString(4, hashPassword(accountDTO.getPassword()));
            statement.setInt(5, 1);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean changePasswordByEmail(String email,String oldPassword,String newPassword) throws SQLException {
        String sql = "UPDATE Account SET Password = ? WHERE (Email = ? and Password = ?)";
        ConnectUtils db = ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, hashPassword(newPassword));
            statement.setString(2, email);
            statement.setString(3, hashPassword(oldPassword));

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {
        if (email == null || newPassword == null || email.isEmpty() || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Email và mật khẩu không được để trống!");
        }
        String sql = "UPDATE Account SET Password = ? WHERE Email = ?";
        ConnectUtils db = ConnectUtils.getInstance();
        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hashPassword(newPassword));
            statement.setString(2, email);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi đặt lại mật khẩu: " + e.getMessage()); // Log lỗi thay vì in stack trace
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean isEmailExist(String email) {
        String sql = "SELECT COUNT(*) FROM Account WHERE Email = ?";
        ConnectUtils db = ConnectUtils.getInstance();
        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CreatorDTO> getUsersForAdmin() {
        String sql = "SELECT * FROM Account";
        List<CreatorDTO> creators = new ArrayList<>();
            ConnectUtils db = ConnectUtils.getInstance();
            try (Connection connection = db.openConection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    CreatorDTO creator = new CreatorDTO();
                    creator.setAccountID(resultSet.getInt("Accountid"));
                    creator.setUserName(resultSet.getString("UserName"));
                    creator.setEmail(resultSet.getString("Email"));
                    User user = userRepository.getUserByAccountId(creator.getAccountID());
                    String phoneNumber = (user != null) ? user.getPhoneNumber() : null;
                    creator.setPhoneNumber(phoneNumber);
                    creator.setStatus(resultSet.getByte("Status"));
                    TypeOfRank rank = rankService.getNameOfRankID(creator.getAccountID());
                    String rankName = (rank != null) ? rank.getTypeRankName() : "Unranked";
                    creator.setNameOfRank(rankName);
                    creators.add(creator);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return creators;
        }

}
