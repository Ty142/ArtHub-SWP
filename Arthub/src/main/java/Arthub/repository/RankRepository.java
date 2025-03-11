package Arthub.repository;

import Arthub.dto.RankDTO;

import java.text.ParseException;

public interface RankRepository {

    int AddTypeRankToListRank(RankDTO rankDTO) throws ParseException;

    void AddRankToUserByRankID(int rankID, int AccountID, double price);


}
