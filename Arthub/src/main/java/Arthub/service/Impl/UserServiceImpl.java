package Arthub.service.Impl;

import Arthub.entity.Account;
import Arthub.entity.User;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Arthub.repository.AccountRepository;
import Arthub.repository.UserRepository;
import Arthub.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private Cloudinary cloudinary;

    public UserServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public boolean checkLogin(String username, String password) {
        return false;
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
