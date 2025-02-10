package Arthub.api;

import Arthub.dto.AccountDTO;
import Arthub.entity.Account;
import Arthub.repository.AccountRepository;
import Arthub.service.EmailTokenService;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Arthub.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Account")  // Đặt lại request mapping chuẩn
public class AccountAPI {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    private AccountRepository accountRepository;

    private final EmailTokenService emailTokenService;
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

//    @GetMapping("/{id}")
//    public ResponseEntity<Account> getAccountById(@PathVariable("id") int accountId) {
//        System.out.println("🔍 Received request for Account ID: " + accountId);
//        Account account = accountService.
//
//
//        if (account == null) {
//            System.out.println("⚠️ Account not found for ID: " + accountId);
//            return ResponseEntity.notFound().build(); // Trả về HTTP 404 nếu không tìm thấy
//        }
//
//        System.out.println("✅ Found account: " + account);
//        return ResponseEntity.ok(account); // HTTP 200
//    }
    }


