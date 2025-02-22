package Arthub.service.Impl;

import Arthub.dto.UserDTO;
import Arthub.entity.Account;
import Arthub.entity.User;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Arthub.repository.UserRepository;
import Arthub.service.UserService;

import java.sql.SQLException;
import java.io.IOException;
import java.util.*;

@Service
    public class UserServiceImpl implements UserService {

    private final Cloudinary cloudinary;

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
    public String uploadAvatar(byte[] imgByte, int type, String oldPublicId) throws IOException {
        Map<Integer,String> attributes = new HashMap<Integer,String>();
        attributes.put(1, "avatar");
        attributes.put(2, "background");
        attributes.put(3, "Artwork");
        attributes.put(4, "test");
        String folderName = attributes.get(type);

        if (oldPublicId != null && !oldPublicId.trim().isEmpty()) {
            Map<String, Object> options = ObjectUtils.asMap("invalidate", true);
            Map<?, ?> deleteResult = cloudinary.uploader().destroy(oldPublicId, options);
            System.out.println("Delete result: " + deleteResult);
        }

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
    public User saveUser(Account account, User user) throws SQLException {
        return userRepository.saveUser(account, user);
    }

    @Override
    public List<User> getTop10PopularUsers() {
        return userRepository.getTop10PopularUsers();
    }

    public void deleteArtworkAtCloudinary(String idPicture) throws IOException {
        if (idPicture != null && !idPicture.trim().isEmpty()) {
            Map<String, Object> options = ObjectUtils.asMap("invalidate", true);
            Map<?, ?> deleteResult = cloudinary.uploader().destroy(idPicture, options);
            System.out.println("Delete result: " + deleteResult);
        }
    }
    @Override
    public boolean updateUser(User user){
        return userRepository.updateUser(user);
    }

}



