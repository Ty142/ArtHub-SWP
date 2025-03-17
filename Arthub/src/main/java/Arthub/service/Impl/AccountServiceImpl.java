package Arthub.service.Impl;

import Arthub.dto.AccountDTO;
import Arthub.dto.CreatorDTO;
import Arthub.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Arthub.repository.AccountRepository;
import Arthub.repository.impl.AccountRepositoryImpl;
import Arthub.service.AccountService;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepositoryImpl accountRepositoryImpl;

    @Autowired
    private AccountRepository accountRepository;

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

    @Override
    public boolean createAccount(AccountDTO accountDTO) {
        try {
            return accountRepository.createAccount(accountDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isEmailExist(String email) {
        return accountRepository.isEmailExist(email);
    }

    @Override
    public List<CreatorDTO> getAccountToAdmin() {
        return accountRepository.getUsersForAdmin();
    }


}