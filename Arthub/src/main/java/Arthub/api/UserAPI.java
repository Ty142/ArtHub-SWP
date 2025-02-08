package Arthub.api;

import Arthub.entity.Account;
import Arthub.entity.User;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Arthub.service.AccountService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Creator")
public class UserAPI {

    @Autowired
    AccountService accountService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {

        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
    @GetMapping
    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserByIdAccount(id);
    }

}
