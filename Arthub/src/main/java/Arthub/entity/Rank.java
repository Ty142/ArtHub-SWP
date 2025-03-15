package Arthub.entity;

import java.time.LocalDate;
import java.util.Date;

public class Rank {
 private int rankID;
 private Date DayToRentRankAt;
 private int typeID;
 private Date dayToEndRank;

    public Rank(){

    }

    public Rank(int rankID, Date dayToRentRankAt, int typeID) {
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


    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public Date getDayToRentRankAt() {
        return DayToRentRankAt;
    }

    public void setDayToRentRankAt(Date dayToRentRankAt) {
        DayToRentRankAt = dayToRentRankAt;
    }

    public Date getDayToEndRank() {
        return dayToEndRank;
    }

    public void setDayToEndRank(Date dayToEndRank) {
        this.dayToEndRank = dayToEndRank;
    }
}
