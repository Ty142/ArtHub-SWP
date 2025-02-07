package Arthub.service.Impl;

import Arthub.entity.Account;
import Arthub.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Arthub.repository.AccountRepository;
import Arthub.repository.UserRepository;
import Arthub.service.UserService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public boolean checkLogin(String username, String password) {
        return false;
    }

}
