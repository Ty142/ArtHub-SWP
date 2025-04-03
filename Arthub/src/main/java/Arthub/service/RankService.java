package Arthub.service;

import Arthub.dto.ArtistFormDTO;
import Arthub.dto.RankDTO;
import Arthub.entity.TypeOfRank;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface RankService {
    void AddRankToUser(RankDTO rankDTO) throws ParseException, SQLException;

    void removeRankToExpired(int RankID, int UserID);

    int getTypeOfRankIDByUserID(int UserID);

    TypeOfRank getNameOfRankID(int accountID);

    List<RankDTO> getListArtistUpgrade();

    void AcceptUpgradeArtist(int RankID);

    int getTheNewRankID(ArtistFormDTO dto) throws ParseException;
}
