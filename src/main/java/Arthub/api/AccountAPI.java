package Arthub.api;

import Arthub.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Arthub.service.AccountService;
import java.util.List;

@RestController
@RequestMapping
public class AccountAPI {

    @Autowired
    AccountService accountService;


    @GetMapping(value = "/api/Account")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

}
