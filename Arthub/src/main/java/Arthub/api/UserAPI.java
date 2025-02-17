package Arthub.api;

import Arthub.dto.FileUploadDTO;
import Arthub.entity.User;
import Arthub.repository.UserRepository;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Arthub.service.AccountService;
import java.util.ArrayList;
import java.io.IOException;
import utils.ImageUtils;

@RestController
@RequestMapping("/api/Creator") // Đặt route chính cho API User
public class UserAPI {
    @Autowired
    AccountService accountService;
    @Autowired
     UserService userService;

    @Autowired
    UserRepository userRepository;


    ImageUtils imageUtils = new ImageUtils();






    public ResponseEntity<User> getUserByAccountId(@PathVariable("accountId") int accountId) {
        System.out.println("🔍 Received request for User with Account ID: " + accountId);

        // Gọi UserService để lấy thông tin User
        User user = userService.getUserByAccountId(accountId);

        if (user == null) {
            System.out.println("⚠️ No user found for Account ID: " + accountId);
            return ResponseEntity.notFound().build(); // Trả về HTTP 404 nếu không tìm thấy
        }

        System.out.println("✅ User found: " + user);
        return ResponseEntity.ok(user); // Trả về HTTP 200 nếu tìm thấy
    }
    @GetMapping
    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserByAccountId(id);
    }

    @PutMapping("/{accountId}/avatar")
    public ResponseEntity<String> uploadAvatar(@PathVariable Integer accountId,@RequestBody FileUploadDTO uploadFileAvatar) throws IOException {

        try {
            byte[] imgByte = imageUtils.decodeBase64(uploadFileAvatar.getImageFile());
            String avatarUrl = userService.uploadAvatar(imgByte, 1);
            userRepository.updateAvatar(accountId, avatarUrl);
            return ResponseEntity.ok("Upload thành công, Avatar: " + avatarUrl);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi upload ảnh: " + e.getMessage());
        }
    }

    @PutMapping("/{accountId}/background")
    public ResponseEntity<String> uploadBackground(@PathVariable("accountId") Integer accountId,
                                                   @RequestBody FileUploadDTO uploadFileBackground) throws IOException {

        try {
            byte[] imgByte = imageUtils.decodeBase64(uploadFileBackground.getImageFile());
            String Background = userService.uploadAvatar(imgByte, 2);
            userRepository.updateBackground(accountId, Background);
            return ResponseEntity.ok("Upload thành công, Background: " + Background);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi upload ảnh: " + e.getMessage());
        }
    }


    // API Lấy thông tin User
    @GetMapping("/userID/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId) {
        try {
            User user = userRepository.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
