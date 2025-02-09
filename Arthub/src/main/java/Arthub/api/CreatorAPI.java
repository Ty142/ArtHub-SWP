package Arthub.api;

import Arthub.entity.User;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/Creator")
public class CreatorAPI {

    @Autowired
    private UserService userService;

    /**
     * API để tạo User mới trong database
     * @param user Dữ liệu User từ request body
     * @return ResponseEntity chứa User vừa tạo hoặc lỗi
     */
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.saveUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
