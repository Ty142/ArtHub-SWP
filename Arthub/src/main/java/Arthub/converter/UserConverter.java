package Arthub.converter;

import Arthub.dto.AccountDTO;
import Arthub.dto.UserDTO;
import Arthub.entity.Account;
import Arthub.entity.User;

public class UserConverter {
    public User ConvertUserDTOToUserEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setBiography(userDTO.getBiography());
        user.setCoins(userDTO.getCoins());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        return user;
    }

    public UserDTO ConvertUserEntityToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setBiography(user.getBiography());
        userDTO.setCoins(user.getCoins());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        return userDTO;
    }

    public Account ConvertAccountDTOtoAccountEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountId(accountDTO.getAccountId());
        account.setUserName(accountDTO.getUserName());
        account.setPassword(accountDTO.getPassword());
        account.setEmail(accountDTO.getEmail());
        account.setStatus(1);
        return account;
    }
}
