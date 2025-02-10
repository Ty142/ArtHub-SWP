package Arthub.service;

import Arthub.entity.Account;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import Arthub.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;


public interface UserService {
    boolean checkLogin(String username, String password);
    ArrayList<User> getAllUsers();
    User getUserByAccountId(int accountId); // Định nghĩa phương thức để lấy User theo AccountID
    User saveUser(Account account, User user) throws SQLException;
    String uploadAvatar(byte[] imgByte,String uniqueFile) throws IOException;
}
