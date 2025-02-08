package Arthub.api;

import Arthub.dto.AccountDTO;
import Arthub.entity.Account;
import Arthub.service.EmailTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Arthub.service.AccountService;
import java.util.List;

@RestController
@RequestMapping
public class AccountAPI {

    private final EmailTokenService emailTokenService;

    @Autowired
    AccountService accountService;
    public AccountAPI(EmailTokenService emailTokenService) {
        this.emailTokenService = emailTokenService;
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
        @GetMapping
        public List<Account> getAccounts() {
            return accountService.getAccounts();
        }
}
