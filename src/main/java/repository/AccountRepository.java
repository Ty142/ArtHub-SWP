package repository;

import entity.Account;

public interface AccountRepository {
    public Integer getAccountToLogin(String username, String password);
}
