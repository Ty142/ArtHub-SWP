package Arthub.service;

import Arthub.dto.RankDTO;
import Arthub.entity.TypeOfRank;

import java.text.ParseException;
import java.util.List;

public interface RankService {
    void AddRankToUser(RankDTO rankDTO) throws ParseException;

    void removeRankToExpired(int RankID, int UserID);

    int getTypeOfRankIDByUserID(int UserID);

    TypeOfRank getNameOfRankID(int accountID);

    List<RankDTO> getListArtistUpgrade();

    void AcceptUpgradeArtist(int RankID);
}
