package Arthub.dto;

public class AccountDTO {
    private int accountId;
    private String userName;
    private String password;
    private String email;
    private boolean status;
    private int roleID;
    private String newpass;


    public AccountDTO(int accountId, String userName, String password, String email, boolean status, int roleID) {
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.status = status;
        this.roleID = roleID;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}