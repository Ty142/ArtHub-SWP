package Arthub.entity;


import Arthub.repository.impl.AccountRepositoryImpl;

public class Account {
    private int accountId;
    private String userName;
    private String password;
    private String email;
    private int status;
    private int RoleID;

    public Account() {
    }

//    public Account(int accountId, String userName, String password, String email, int status, int roleID) {
//        this.accountId = accountId;
//        this.userName = userName;
//        this.password = password;
//        this.email = email;
//        this.status = status;
//    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
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
        return AccountRepositoryImpl.hashPassword(password);
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
    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }

//    static Account account = new Account(1, "minh", "minhdeptrai2805", "minhdcde180378@gmail.com", 1, 1);
//    static Account account2 = new Account(5, "mcdfinh", "minhdeptrai2805", "minhdcsdfdsfgde180378@gmail.com", 1, 1);
//    public static void main(String[] args) {
//        System.out.println(account.getPassword());
//        System.out.println(account2.getPassword());
//    }
}
