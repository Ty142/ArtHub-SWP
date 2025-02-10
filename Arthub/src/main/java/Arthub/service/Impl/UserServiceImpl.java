package Arthub.service.Impl;

import Arthub.entity.Account;
import Arthub.entity.User;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Arthub.repository.AccountRepository;
import Arthub.repository.UserRepository;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private Cloudinary cloudinary;

    public UserServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkLogin(String username, String password) {
        return false;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return userRepository.getAllUsers();

    }
    public User getUserByAccountId(int accountId) {
        // Gọi UserRepository để lấy thông tin User
        return userRepository.getUserByAccountId(accountId);
    }

    @Override
    public String uploadAvatar(byte[] imgByte,int type) throws IOException {
        Map<Integer,String> attributes = new HashMap<Integer,String>();
        attributes.put(1, "avatar");
        attributes.put(2, "background");
        attributes.put(3, "Artwork");
        String folderName = attributes.get(type);
        String uniqueFileName = UUID.randomUUID().toString();
        Map<?, ?> uploadAvatar = cloudinary.uploader().upload(imgByte, ObjectUtils.asMap(
                "folder", folderName,
                "public_id", uniqueFileName,
                "overwrite", true,
                "resource_type", "image"
        ));

        return uploadAvatar.get("secure_url").toString();
    }
    @Override
    public User saveUser(User user) throws SQLException {
        return userRepository.saveUser(user);
    }

}



