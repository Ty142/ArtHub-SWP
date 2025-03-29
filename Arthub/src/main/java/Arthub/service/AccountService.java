package Arthub.service;

import Arthub.dto.AccountDTO;
import Arthub.dto.CreatorDTO;
import Arthub.entity.Account;
import java.util.ArrayList;
import java.util.List;

public interface AccountService {
    ArrayList<Account> getAccounts();
    Account getAccountById(int accountId);
    Account getAccountByEmailAndPassword(String email, String password);
    boolean createAccount(AccountDTO accountDTO);
    public boolean isEmailExist(String email);
    List<CreatorDTO> getAccountToAdmin();
}