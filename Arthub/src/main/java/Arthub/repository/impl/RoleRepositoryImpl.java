package Arthub.repository.impl;

import Arthub.entity.Account;
import Arthub.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import Arthub.repository.RoleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Override
    public Role getRoleById(int id) {
        String sql = "SELECT * FROM Role WHERE RoleID = ? ";
        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Role role = new Role();
                    role.setRoleId(resultSet.getInt("RoleID"));
                    role.setRoleName(resultSet.getString("RoleName"));
                    return role;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Integer findRoleIdByUserId(int userId) {
        return 0;
    }

    @Override
    public ArrayList<Role> getAllRoles() {
        return null;
    }
}
