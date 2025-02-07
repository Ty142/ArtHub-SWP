package Arthub.service.Impl;

import Arthub.entity.Account;
import Arthub.entity.Role;
import Arthub.repository.impl.AccountRepositoryImpl;
import Arthub.repository.impl.RoleRepositoryImpl;
import Arthub.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class RoleServiceImpl implements RoleService {


    private final RoleRepositoryImpl roleRepositoryImpl;

    public RoleServiceImpl() {
        this.roleRepositoryImpl = new RoleRepositoryImpl();
    }


    @Override
    public Role getRoleById(int id) {
        return roleRepositoryImpl.getRoleById(id);
    }
}
