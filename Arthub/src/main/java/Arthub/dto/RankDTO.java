package Arthub.dto;

public class RankDTO {
    private int rankID;
    private int accountID;
    private int typeID;
    private String dayToRentRankAt;
    private double price;
    private String dayToEndRank;
    private int FormID;
    private int UserID;

    public RankDTO(int rankID, int accountID, int typeID, String dayToRentRankAt, double price) {
        this.rankID = rankID;
        this.accountID = accountID;
        this.typeID = typeID;
        this.dayToRentRankAt = dayToRentRankAt;
        this.price = price;
    }

    public RankDTO(){
    }

    public int getRankID() {
        return rankID;
    }

    public void setRankID(int rankID) {
        this.rankID = rankID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getDayToRentRankAt() {
        return dayToRentRankAt;
    }

    public void setDayToRentRankAt(String dayToRentRankAt) {
        this.dayToRentRankAt = dayToRentRankAt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDayToEndRank() {
        return dayToEndRank;
    }
    public void setDayToEndRank(String dayToEndRank) {
        this.dayToEndRank = dayToEndRank;
    }

    public int getFormID() {
        return FormID;
    }

    public void setFormID(int formID) {
        FormID = formID;
    }

    public int getUserID() {
        return UserID;
    }
    public void setUserID(int userID) {
        UserID = userID;
    }
}
