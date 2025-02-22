package Arthub.dto;

public class RankDTO {
    private int rankID;
    private int accountID;
    private int typeID;
    private String dayToRentRankAt;

    public RankDTO(int rankID, int accountID, int typeID, String dayToRentRankAt) {
        this.rankID = rankID;
        this.accountID = accountID;
        this.typeID = typeID;
        this.dayToRentRankAt = dayToRentRankAt;
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
}
