package Arthub.service;

import Arthub.entity.User;

public interface UserService {
    User getUserByAccountId(int accountId); // Định nghĩa phương thức để lấy User theo AccountID
}
