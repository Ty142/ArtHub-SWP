package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private int accountId;
    private String userName;
    private String password;
    private String email;
    private int status;

    public Account() {
    }
}
