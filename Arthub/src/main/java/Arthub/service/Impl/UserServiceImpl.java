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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
    public String uploadAvatar(byte[] imgByte, String uniqueFileName) throws IOException {

        Map<?, ?> uploadAvatar = cloudinary.uploader().upload(imgByte, ObjectUtils.asMap(
                "folder", "avatar",
                "public_id", uniqueFileName,
                "resource_type", "image"
        ));

        return uploadAvatar.get("secure_url").toString();
    }

}


