package Arthub.service;

import Arthub.entity.User;
import java.util.ArrayList;

public interface UserService {
    boolean checkLogin(String username, String password);
    ArrayList<User> getAllUsers();
    User getUserByIdAccount(int id);
}
