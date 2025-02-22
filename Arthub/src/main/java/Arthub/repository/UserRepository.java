package Arthub.repository;

import Arthub.dto.UserDTO;
import Arthub.entity.Account;
import Arthub.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserRepository {
    ArrayList<User> getAllUsers();
    void addUserAccount(UserDTO userDTO) throws SQLException;
    User getUserById(int id);
    void updateAvatar(int id, String avatar);

    User getUserByAccountId(int accountId); // Định nghĩa phương thức lấy User theo AccountID
    User saveUser(Account account, User user) throws SQLException;
    void updateBackground(int accountId, String background);
    String findAvatarByAccountId(int AccountId);
    String findBackgroundByAccountId(int AccountId);
    List<User> getTop10PopularUsers();
    boolean updateUser(User user);
}
