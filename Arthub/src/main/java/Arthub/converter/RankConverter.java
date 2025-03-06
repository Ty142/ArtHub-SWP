package Arthub.converter;

import Arthub.dto.RankDTO;
import Arthub.entity.Rank;

public class RankConverter {

    public Rank ConvertRankDTOToRankEntity(RankDTO rankDTO){
        Rank rank = new Rank();
        rank.setRankID(rankDTO.getRankID());
        rank.setDayToRentRankAt(rankDTO.getDayToRentRankAt());
        rank.setTypeID(rankDTO.getTypeID());
        return rank;
    }
}
