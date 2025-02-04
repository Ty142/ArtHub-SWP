package repository.impl;

import entity.Account;

import java.sql.*;

import org.springframework.stereotype.Repository;
import repository.AccountRepository;
import utils.ConnectUtils;


@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Integer getAccountToLogin(String username, String password) {
        String sql = "SELECT a.accountId From Account a where a.UserName like ?";
        try {
            Connection connection = ConnectUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("accountId");
                return id;
            }
        } catch (SQLException e) {

        }
        return null;
    }
}
