package Arthub.service.Impl;

import Arthub.entity.User;
import Arthub.repository.UserRepository;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByAccountId(int accountId) {
        // Gọi UserRepository để lấy thông tin User
        return userRepository.getUserByAccountId(accountId);
    }
}
