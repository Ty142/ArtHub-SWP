package Arthub.api;

import Arthub.dto.AccountDTO;
import Arthub.service.EmailTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Arthub.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Arthub.service.AccountService;
import java.util.List;

@RestController
@RequestMapping("/api/Account")

public class AccountAPI {

    private final EmailTokenService emailTokenService;

    @Autowired
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
}