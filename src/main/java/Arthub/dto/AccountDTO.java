package Arthub.dto;

public class AccountDTO {
    private int accountId;
    private String userName;
    private String password;
    private String email;
    private int status;
    private String oldPassword;

    public AccountDTO(int accountId, String userName, String password, String email, int status, String oldPassword) {
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.status = status;
        this.oldPassword = oldPassword;
    }

    public AccountDTO(){

    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
