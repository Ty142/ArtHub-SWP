package repository;

import entity.User;

public interface UserRepository {
    public User getUserByIdAccount(int id);
}
