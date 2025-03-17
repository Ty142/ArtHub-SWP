package Arthub.api;

import Arthub.dto.AccountDTO;
import Arthub.entity.Account;
import Arthub.repository.AccountRepository;
import Arthub.service.EmailTokenService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Arthub.service.AccountService;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/Account")

public class AccountAPI {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    private final EmailTokenService emailTokenService;

    @Autowired
    public AccountAPI(EmailTokenService emailTokenService) {
        this.emailTokenService = emailTokenService;
    }

    /**
     * API lấy tất cả tài khoản
     */
    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = accountService.getAccounts();
        if (accounts.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về HTTP 204 nếu danh sách trống
        }
        return ResponseEntity.ok(accounts); // HTTP 200
    }

    @PostMapping("/send-token")
    public ResponseEntity<String> sendTokenToEmail(@RequestBody AccountDTO accountDTO) {
        String email = accountDTO.getEmail();

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email cannot be null or empty.");
        }
        String token = emailTokenService.generateAndSendToken(email);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") int accountId) {
        System.out.println("🔍 Received request for Account ID: " + accountId);
        Account account = accountService.getAccountById(accountId);

        if (account == null) {
            System.out.println("⚠️ Account not found for ID: " + accountId);
            return ResponseEntity.notFound().build();
        }

        System.out.println("✅ Found account: " + account);
        return ResponseEntity.ok(account); // HTTP 200
    }

    @PostMapping("/CreateAccount")
    public ResponseEntity<String> createAccount(@RequestBody AccountDTO accountDTO) {
        if (accountService.isEmailExist(accountDTO.getEmail())) {
            return ResponseEntity.ok("");
        }
        boolean isCreated = accountService.createAccount(accountDTO);
        if (isCreated) {
            return ResponseEntity.ok("Account created successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to create account");
        }
    }



    private static final Logger logger = LoggerFactory.getLogger(AccountAPI.class);





    /**
     * API để thay đổi mật khẩu dựa trên email
     * @param requestBody JSON chứa email và mật khẩu mới
     * @return ResponseEntity chứa thông báo kết quả
     */
    @PostMapping("/changepassword")
    public ResponseEntity<String> resetPassword(@RequestBody ChangePasswordRequest requestBody) {
        logger.info("🔍 Received request to change password for email: {}", requestBody.getEmail());

        try {
            // Kiểm tra xem email có tồn tại không
            Account account = accountRepository.getAccountByEmail(requestBody.getEmail());
            if (account == null) {
                logger.warn("⚠️ Email '{}' không tồn tại trong hệ thống.", requestBody.getEmail());
                return ResponseEntity.badRequest().body("2");
            }

            // Cập nhật mật khẩu mới vào database
            boolean isUpdated = accountRepository.resetPassword(requestBody.getEmail(), requestBody.getNewPassword());
            if (isUpdated) {
                logger.info("✅ Mật khẩu đã được thay đổi thành công cho email: {}", requestBody.getEmail());
                return ResponseEntity.ok("1");
            } else {
                logger.error("❌ Lỗi khi cập nhật mật khẩu cho email: {}", requestBody.getEmail());
                return ResponseEntity.internalServerError().body("Lỗi khi cập nhật mật khẩu.");
            }
        } catch (Exception e) {
            logger.error("❌ Unexpected error: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Lỗi hệ thống.");
        }
    }


    @PutMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest requestBody) {
        logger.info("🔍 Received request to change password for email: {}", requestBody.getEmail());

        try {
            // Kiểm tra xem email va password có tồn tại không
            Account account = accountRepository.getAccountByEmailAndPassword(requestBody.getEmail(), requestBody.getOldPassword());
            if (account == null) {
                logger.warn("⚠️ Email '{}' gửi thông tin sai khi đổi mật khẩu.", requestBody.getEmail());
                return ResponseEntity.badRequest().body("2");
            }

            // Cập nhật mật khẩu mới vào database
            boolean isUpdated = accountRepository.changePasswordByEmail(requestBody.getEmail(), requestBody.getNewPassword(),requestBody.getOldPassword());
            if (isUpdated) {
                logger.info("✅ Mật khẩu đã được thay đổi thành công cho email: {} | old {} | new {} ", requestBody.getEmail()
                , requestBody.getOldPassword(), requestBody.getNewPassword());

                return ResponseEntity.ok("1");
            } else {
                logger.error("❌ Lỗi khi cập nhật mật khẩu cho email: {}", requestBody.getEmail());
                return ResponseEntity.internalServerError().body("Lỗi khi cập nhật mật khẩu.");
            }
        } catch (SQLException e) {
            logger.error("❌ SQL Exception: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Lỗi hệ thống khi cập nhật mật khẩu.");
        } catch (Exception e) {
            logger.error("❌ Unexpected error: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Lỗi hệ thống.");
        }
    }
    // DTO chứa request body của API
    public static class ChangePasswordRequest {
        private String newPassword;
        private String email;
        private String oldPassword;

        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getOldPassword() { return oldPassword; }
        public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }


    }

}
