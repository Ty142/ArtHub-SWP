package repository;

import entity.Role;

public interface RoleRepository {
    public Role findRoleByRoleId(int id);
    public Integer findRoleIdByUserId(int userId);
}
