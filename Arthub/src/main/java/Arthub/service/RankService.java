package Arthub.service;

import Arthub.dto.RankDTO;
import Arthub.entity.TypeOfRank;

import java.text.ParseException;

public interface RankService {
    void AddRankToUser(RankDTO rankDTO) throws ParseException;

    void removeRankToExpired(int RankID, int UserID);

    int getTypeOfRankIDByUserID(int UserID);

    TypeOfRank getNameOfRankID(int accountID);


}
