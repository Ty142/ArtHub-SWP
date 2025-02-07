package Arthub.api;

import Arthub.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Arthub.service.AccountService;
import java.util.List;

@RestController
@RequestMapping("/api/Creator")
public class UserAPI {

    @Autowired
    AccountService accountService;


    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

}
