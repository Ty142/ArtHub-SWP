package Arthub.repository;

import Arthub.dto.AccountDTO;
import Arthub.dto.CreatorDTO;
import Arthub.entity.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AccountRepository {
     ArrayList<Account> getAllAccounts();
     Account getAccountById(int id);
     Account getAccountByEmailAndPassword(String email, String password);
     boolean createAccount(AccountDTO accountDTO);
     Account getAccountByEmail(String email);
     boolean changePasswordByEmail(String email,String oldPassword ,String newPassword) throws SQLException;
     boolean resetPassword(String email,String newPassword);
     boolean isEmailExist(String email);

     List<CreatorDTO> geUsersForAdmin();
}
