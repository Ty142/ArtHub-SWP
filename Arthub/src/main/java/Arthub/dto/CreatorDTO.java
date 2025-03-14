package Arthub.dto;

import java.util.Date;

public class CreatorDTO {
   private int AccountID;
   private String UserName;
   private String Email;
   private String PhoneNumber;
   private String NameOfRank;
   private byte Status;

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getNameOfRank() {
        return NameOfRank;
    }

    public void setNameOfRank(String nameOfRank) {
        NameOfRank = nameOfRank;
    }

    public byte getStatus() {
        return Status;
    }

    public void setStatus(byte status) {
        Status = status;
    }
}