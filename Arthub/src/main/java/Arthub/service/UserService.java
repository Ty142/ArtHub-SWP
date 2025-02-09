package Arthub.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface UserService {
    boolean checkLogin(String username, String password);
    String uploadAvatar(byte[] imgByte,String uniqueFile) throws IOException;
}
