package Arthub.entity;

public class Rank {
 private int rankID;
 private String DayToRentRankAt;
 private int typeID;

    public Rank(){

    }

    public Rank(int rankID, String dayToRentRankAt, int typeID) {
        this.rankID = rankID;
        DayToRentRankAt = dayToRentRankAt;
        this.typeID = typeID;
    }

    public int getRankID() {
        return rankID;
    }

    public void setRankID(int rankID) {
        this.rankID = rankID;
    }

    public String getDayToRentRankAt() {
        return DayToRentRankAt;
    }

    public void setDayToRentRankAt(String dayToRentRankAt) {
        DayToRentRankAt = dayToRentRankAt;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }


}
