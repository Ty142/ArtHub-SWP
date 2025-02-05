package repository;

import dto.AccountDTO;
import entity.Account;

public interface AccountRepository {
     Account getAccountIdTLogin(String username);
     String changePassword(String oldPassword,String newPassword);
     void addInformationForRegistration(AccountDTO accountDTO);

}
