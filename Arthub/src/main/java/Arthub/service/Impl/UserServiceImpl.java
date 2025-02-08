package Arthub.service.Impl;

import Arthub.entity.Account;
import Arthub.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Arthub.repository.AccountRepository;
import Arthub.repository.UserRepository;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkLogin(String username, String password) {
        return false;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return userRepository.getAllUsers();

    }
    public User getUserByAccountId(int accountId) {
        // Gọi UserRepository để lấy thông tin User
        return userRepository.getUserByAccountId(accountId);
    }

}
