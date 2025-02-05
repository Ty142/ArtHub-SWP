package dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountDTO {
    private int accountId;
    private String userName;
    private String password;
    private String email;
    private int status;
    private String oldPassword;
}
