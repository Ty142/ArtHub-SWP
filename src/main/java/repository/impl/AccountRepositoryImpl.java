package repository.impl;

import converter.UserConverter;
import dto.AccountDTO;
import entity.Account;

import java.sql.*;

import org.springframework.stereotype.Repository;
import repository.AccountRepository;
import utils.ConnectUtils;


@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Account getAccountIdTLogin(String username) {
        String sql = "SELECT a.accountId From Account a where a.UserName like ?";
        Account account = new Account();
        try {
            Connection connection = ConnectUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account.setAccountId(resultSet.getInt("accountId"));
                account.setUserName(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {

        }
        return null;
    }


    @Override
    public String changePassword(String oldPassword, String newPassword) {
        String sql = "UPDATE Account SET Password =? WHERE Password =?";
        try {
            Connection connection = ConnectUtils.getConnection();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addInformationForRegistration(AccountDTO accountDTO) {
        String sql = "INSERT INTO account values(?,?,?,?)";
        Account account = new UserConverter().ConvertAccountDTOtoAccountEntity(accountDTO);
        try{
            Connection connection = ConnectUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getUserName());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            statement.setInt(4, account.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
