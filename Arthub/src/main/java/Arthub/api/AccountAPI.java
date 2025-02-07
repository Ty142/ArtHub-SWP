package Arthub.api;

import Arthub.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Arthub.service.AccountService;
import java.util.List;

@RestController
@RequestMapping("/api/Account")
public class AccountAPI {

    @Autowired
    AccountService accountService;


    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
}
