package Arthub.api;

import Arthub.dto.FileUploadDTO;
import Arthub.dto.UserDTO;
import Arthub.entity.User;
import Arthub.repository.UserRepository;
import Arthub.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.StringJoiner;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService cloudinaryService;

    @PostMapping("/{userId}/avatar")
    public ResponseEntity<String> uploadAvatar(@PathVariable Integer userId, @RequestBody FileUploadDTO uploadFileUploadDTO ) {
        try {
            String base64 = uploadFileUploadDTO.getBase64Data();
            if (base64 == null || base64.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Dữ liệu ảnh không được để trống");
            }
            if (base64.contains(",")) {
                base64 = base64.split(",")[1];
            }

            byte[] fileUpload = Base64.getDecoder().decode(base64);
            InputStream inputStream = new ByteArrayInputStream(fileUpload);
            String uniqueFile = String.join(inputStream.toString(), ".jpg");
            String avatarUrl = cloudinaryService.uploadAvatar(fileUpload,uniqueFile);
            userRepository.updateAvatar(userId, avatarUrl);
            return ResponseEntity.ok("Upload thành công, URL: " + avatarUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Lỗi khi upload ảnh");
        }
    }


    // API Lấy thông tin User
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId) {
        try {
            User user = userRepository.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
