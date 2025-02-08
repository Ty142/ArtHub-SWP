package Arthub.service.Impl;

import Arthub.entity.Account;
import org.springframework.stereotype.Service;
import Arthub.repository.AccountRepository;
import Arthub.repository.impl.AccountRepositoryImpl;
import Arthub.service.AccountService;
import java.util.ArrayList;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepositoryImpl accountRepositoryImpl;

    public AccountServiceImpl() {
        this.accountRepositoryImpl = new AccountRepositoryImpl();
    }

    @Override
    public ArrayList<Account> getAccounts() {
        return accountRepositoryImpl.getAllAccounts();
    }

    @Override
    public Account getAccountById(int accountId) {
        return accountRepositoryImpl.getAccountById(accountId);
    }

    @Override
    public Account getAccountByEmailAndPassword(String email, String password) {
        return accountRepositoryImpl.getAccountByEmailAndPassword(email, password);
    }
}
