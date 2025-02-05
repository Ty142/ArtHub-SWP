package service.Impl;

import entity.Account;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import repository.UserRepository;
import service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public boolean checkLogin(String username, String password) {
        Optional<Account> optionAccount = Optional.ofNullable(accountRepository.getAccountIdTLogin(username));
        if(optionAccount.isPresent() && optionAccount.get().equals(password)) {
            return true;
        }
        return false;
    }
}
