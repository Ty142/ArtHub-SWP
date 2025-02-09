package Arthub.repository;

import Arthub.dto.UserDTO;
import Arthub.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserRepository {
    ArrayList<User> getAllUsers();
    void addUserAccount(UserDTO userDTO) throws SQLException;
    User getUserById(int id);
    void updateAvatar(int id, String avatar);

    User getUserByAccountId(int accountId); // Định nghĩa phương thức lấy User theo AccountID
}
