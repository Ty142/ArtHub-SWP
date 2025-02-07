package Arthub.repository;

import Arthub.entity.Role;

import java.util.ArrayList;

public interface RoleRepository {
    public Role getRoleById(int id);
    public Integer findRoleIdByUserId(int userId);
    ArrayList<Role> getAllRoles();
}
