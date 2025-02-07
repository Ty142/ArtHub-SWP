package Arthub.repository;

import Arthub.dto.UserDTO;
import Arthub.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserRepository {
    public User getUserByIdAccount(int id);
    ArrayList<User> getAllUsers();
    void addUserAccount(UserDTO userDTO) throws SQLException;
}
