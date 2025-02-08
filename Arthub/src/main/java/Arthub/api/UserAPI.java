package Arthub.api;

import Arthub.entity.User;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class UserAPI {

    @Autowired
    private UserService userService;

    @GetMapping("/Creator")
    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/Creator/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserByIdAccount(id);
    }
}
