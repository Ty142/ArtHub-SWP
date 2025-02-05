package repository;

import dto.UserDTO;
import entity.User;

import java.sql.SQLException;

public interface UserRepository {
    public User getUserByIdAccount(int id);

    void addUserAccount(UserDTO userDTO) throws SQLException;
}
