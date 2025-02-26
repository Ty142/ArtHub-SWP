package Arthub.api;

import Arthub.dto.UserDTO;
import Arthub.entity.Account;
import Arthub.entity.User;
import Arthub.repository.AccountRepository;
import Arthub.repository.impl.AccountRepositoryImpl;
import Arthub.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/Creator")
public class CreatorAPI {

    private static final Logger logger = LoggerFactory.getLogger(CreatorAPI.class);

    @Autowired
    private UserService userService;

    /**
     * API để tạo User mới trong database
     * @param userDTO Dữ liệu User từ request body
     * @return ResponseEntity chứa User vừa tạo hoặc lỗi
     */
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        logger.info("🔍 Received request to create a new user with email: {}", userDTO.getEmail());

        AccountRepository accountRepository = new AccountRepositoryImpl();

        // Kiểm tra xem tài khoản có tồn tại không
        Account accToUser = accountRepository.getAccountByEmail(userDTO.getEmail());
        if (accToUser == null) {
            logger.warn("⚠️ Account with email '{}' does not exist. Cannot create user.", userDTO.getEmail());
            return ResponseEntity.badRequest().body(null);
        }

        try {
            // Chuyển DTO thành User Entity
            User newUser = new User();
            newUser.setAccountId(accToUser.getAccountId());
            newUser.setCoins(userDTO.getCoins());
            newUser.setFirstName(userDTO.getFirstName());
            newUser.setLastName(userDTO.getLastName());
            newUser.setAddress(userDTO.getAddress());
            newUser.setPhoneNumber(userDTO.getPhoneNumber());
            newUser.setBiography(userDTO.getBiography());
            newUser.setProfilePicture(userDTO.getProfilePicture());
            newUser.setBackgroundPicture(userDTO.getBackgroundPicture());
            newUser.setFollowCounts(userDTO.getFollowCounts());
            newUser.setFollowerCount(userDTO.getFollowerCount());
            newUser.setRankId(userDTO.getRankId());
            newUser.setRoleId(userDTO.getRoleId());
            LocalDate localDate = userDTO.getDateOfBirth();
            newUser.setDateOfBirth(localDate);
            newUser.setCreatedAt(userDTO.getCreatedAt() != null ? userDTO.getCreatedAt().toString() : null);

            // Log dữ liệu của User trước khi lưu
            logger.info("📝 Creating user: {}", newUser);

            // Gọi service để lưu User
            User createdUser = userService.saveUser(accToUser, newUser);
            logger.info("✅ User created successfully with ID: {}", createdUser.getUserId());
            return ResponseEntity.ok(createdUser);
        } catch (SQLException e) {
            logger.error("❌ Database error while creating user: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            logger.error("❌ Unexpected error while creating user: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<User> updateUSer(@RequestBody UserDTO userDTO) {
        logger.info("🔍 Received request to create a new user with email: {}", userDTO.getEmail());

        AccountRepository accountRepository = new AccountRepositoryImpl();

        // Kiểm tra xem tài khoản có tồn tại không
        Account accToUser = accountRepository.getAccountByEmail(userDTO.getEmail());
        if (accToUser == null) {
            logger.warn("⚠️ Account with email '{}' does not exist. Cannot create user.", userDTO.getEmail());
            return ResponseEntity.badRequest().body(null);
        }

        try {
            // Chuyển DTO thành User Entity
            User newUser = new User();
            newUser.setAccountId(accToUser.getAccountId());
            newUser.setCoins(userDTO.getCoins());
            newUser.setFirstName(userDTO.getFirstName());
            newUser.setLastName(userDTO.getLastName());
            newUser.setAddress(userDTO.getAddress());
            newUser.setPhoneNumber(userDTO.getPhoneNumber());
            newUser.setBiography(userDTO.getBiography());
            newUser.setProfilePicture(userDTO.getProfilePicture());
            newUser.setBackgroundPicture(userDTO.getBackgroundPicture());
            newUser.setFollowCounts(userDTO.getFollowCounts());
            newUser.setFollowerCount(userDTO.getFollowerCount());
            newUser.setRankId(userDTO.getRankId());
            newUser.setRoleId(userDTO.getRoleId());
            LocalDate localDate = userDTO.getDateOfBirth();
            newUser.setDateOfBirth(localDate);


            // Log dữ liệu của User trước khi lưu
            logger.info("📝 Creating user: {}", newUser);

            // Gọi service để lưu User
            User createdUser = userService.saveUser(accToUser, newUser);
            logger.info("✅ User created successfully with ID: {}", createdUser.getUserId());
            return ResponseEntity.ok(createdUser);
        } catch (SQLException e) {
            logger.error("❌ Database error while creating user: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            logger.error("❌ Unexpected error while creating user: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
