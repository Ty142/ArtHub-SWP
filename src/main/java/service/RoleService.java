package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RoleRepository;

@Service
public class  RoleService {
    @Autowired
    private RoleRepository repository;

    public void printRoleName(int id) {
        String roleName = repository.findRoleNameByRoleId(id);
        System.out.println("Role name: " + roleName);
    }
}

