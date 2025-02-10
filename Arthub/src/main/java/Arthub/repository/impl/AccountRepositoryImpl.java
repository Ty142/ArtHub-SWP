package Arthub.repository.impl;

import Arthub.converter.UserConverter;
import Arthub.dto.AccountDTO;
import Arthub.entity.Account;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import Arthub.repository.AccountRepository;
import utils.ConnectUtils;


@Repository
public class AccountRepositoryImpl implements AccountRepository {


    @Override
    public Account getAccountIdTLogin(String username) {
        String sql = "SELECT a.accountId From Account a where a.UserName like ?";
        ConnectUtils db = ConnectUtils.getInstance();
        Account account = new Account();
        try {
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account.setAccountId(resultSet.getInt("accountId"));
                account.setUserName(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setEmail(resultSet.getString("email"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public String changePassword(String oldPassword, String newPassword) {
        String sql = "UPDATE Account SET Password =? WHERE Password =?";
        ConnectUtils db = ConnectUtils.getInstance();
        try {
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, oldPassword);
            int rowAffected = statement.executeUpdate();
            if (rowAffected > 0) {
                return "Password changed successfully";
            }
            else {
                return "Old password is incorrect";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void addInformationForRegistration(AccountDTO accountDTO) {
        String sql = "INSERT INTO account values(?,?,?,?)";
        Account account = new UserConverter().ConvertAccountDTOtoAccountEntity(accountDTO);
        ConnectUtils db = ConnectUtils.getInstance();
        try {
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getUserName());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            statement.setInt(4, account.getStatus());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

//    @Override
//    public Account getAccountById(int accountId) {
//        String sql = "SELECT * FROM Account WHERE AccountID = ?";
//        ConnectUtils db = ConnectUtils.getInstance();
//        Account account = null;
//
//        try (Connection connection = db.openConection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setInt(1, accountId);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                account = new Account();
//                account.setAccountId(resultSet.getInt("AccountID"));
//                account.setUserName(resultSet.getString("UserName"));
//                account.setPassword(resultSet.getString("Password"));
//                account.setEmail(resultSet.getString("Email"));
//                account.setStatus(resultSet.getInt("Status"));
//                account.setRoleID(resultSet.getInt("RoleID"));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return account;
//    }
//

}
