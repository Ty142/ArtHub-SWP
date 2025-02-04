package repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import repository.RoleRepository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public String findRoleNameByRoleId(int id) {
        String sql = "SELECT role_name FROM role WHERE role_id =1";

        String roleName = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);

        return roleName;
    }
}
