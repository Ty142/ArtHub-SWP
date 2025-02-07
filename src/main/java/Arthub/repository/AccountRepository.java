package Arthub.repository;

import Arthub.dto.AccountDTO;
import Arthub.entity.Account;

import java.util.ArrayList;

public interface AccountRepository {
     Account getAccountIdTLogin(String username);
     String changePassword(String oldPassword,String newPassword);
     void addInformationForRegistration(AccountDTO accountDTO);
     ArrayList<Account> getAllAccounts();
}
