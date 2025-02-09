package Arthub.service;

import Arthub.dto.AccountDTO;
import Arthub.entity.Account;
import java.util.ArrayList;

public interface AccountService {
    ArrayList<Account> getAccounts();
    Account getAccountById(int accountId);
    Account getAccountByEmailAndPassword(String email, String password);
    boolean createAccount(AccountDTO accountDTO);
}