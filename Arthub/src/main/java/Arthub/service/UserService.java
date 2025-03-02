package Arthub.service;

import Arthub.dto.UserDTO;
import Arthub.entity.Account;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import Arthub.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface UserService {
    boolean checkLogin(String username, String password);
    ArrayList<User> getAllUsers();
    User getUserByAccountId(int accountId);
    // Định nghĩa phương thức để lấy User theo AccountID

    String uploadAvatar(byte[] imgByte, int type, String ole) throws IOException;

    User saveUser(Account account, User user) throws SQLException;
     void deleteArtworkAtCloudinary(String idPicture) throws IOException;

    List<User> getTop10PopularUsers();
    boolean updateUser(User user) throws SQLException;
    void updateCoinsAmount(int accountId, double amount);

}
