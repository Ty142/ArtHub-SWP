package Arthub.repository;

import Arthub.entity.User;

public interface UserRepository {
    User getUserByAccountId(int accountId); // Định nghĩa phương thức lấy User theo AccountID
}
