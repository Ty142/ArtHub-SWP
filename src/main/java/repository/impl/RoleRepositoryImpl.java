package repository.impl;

import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import repository.RoleRepository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Role findRoleByRoleId(int id) {
        String sql = "SELECT * FROM Roles WHERE roleid =?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, Role.class);
    }

    @Override
    public Integer findRoleIdByUserId(int userId) {
        String sql = "SELECT r.roleid, r.rolename FROM Roles r WHERE user_id =?";
        return  jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
    }
}
