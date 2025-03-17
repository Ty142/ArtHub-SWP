package Arthub.converter;

import Arthub.dto.RankDTO;
import Arthub.entity.Rank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RankConverter {

    public Rank ConvertRankDTOToRankEntity(RankDTO rankDTO) throws ParseException {
        Rank rank = new Rank();
        rank.setRankID(rankDTO.getRankID());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(rankDTO.getDayToRentRankAt());
        rank.setDayToRentRankAt(parsedDate);
        rank.setTypeID(rankDTO.getTypeID());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parsedDate);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        rank.setDayToEndRank(calendar.getTime());
        rank.setFormID(rankDTO.getFormID());
        return rank;
    }

    public RankDTO ConvertRankEntityToRankDTO(Rank rank) {
        RankDTO rankDTO = new RankDTO();
        rankDTO.setRankID(rank.getRankID());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        rankDTO.setDayToRentRankAt(dateFormat.format(rank.getDayToRentRankAt()));
        rankDTO.setTypeID(rank.getTypeID());
        rankDTO.setDayToEndRank(dateFormat.format(rank.getDayToEndRank()));
        rankDTO.setFormID(rank.getFormID());
        return rankDTO;
    }
}
