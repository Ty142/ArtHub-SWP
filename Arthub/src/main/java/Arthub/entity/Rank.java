package Arthub.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rank {
    private int rankId;
    private String rankName;
    private String dayToRentRankAt;
    private double price;

    public Rank(){}
}
