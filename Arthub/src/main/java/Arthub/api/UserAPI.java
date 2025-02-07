package Arthub.api;

import Arthub.entity.User;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Creator") // Đặt route chính cho API User
public class UserAPI {

    @Autowired
    private UserService userService;

    /**
     * API trả về thông tin user theo `accountId`.
     *
     * @param accountId ID của Account
     * @return Thông tin của User hoặc HTTP 404 nếu không tìm thấy
     */
    @GetMapping("/{accountId}")
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
}
