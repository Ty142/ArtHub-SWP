package Arthub.api;

import Arthub.dto.FileUploadDTO;
import Arthub.dto.UserDTO;
import Arthub.entity.Account;
import Arthub.entity.User;
import Arthub.repository.UserRepository;
import Arthub.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import Arthub.service.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.StringJoiner;

@RestController
@RequestMapping("/api/Creator") // Đặt route chính cho API User
public class UserAPI {
    @Autowired
    AccountService accountService;
    @Autowired
     UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
    /**
     * API trả về thông tin user theo `accountId`.
     *
     * @param accountId ID của Account
     * @return Thông tin của User hoặc HTTP 404 nếu không tìm thấy
     */
    UserService cloudinaryService;

        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
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
            String avatarUrl = userService.uploadAvatar(fileUpload,uniqueFile);
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
