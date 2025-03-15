package Arthub.repository;

import Arthub.dto.RankDTO;
import Arthub.entity.Rank;

import java.text.ParseException;
import java.util.List;

public interface RankRepository {

    int AddTypeRankToListRank(RankDTO rankDTO) throws ParseException;

    void AddRankToUserByRankID(int rankID, int AccountID, double price);

    void ChangeRankToExpire(int UserID);

    void deleteRank(int RankID);

    List<RankDTO> getAllRanksArtist();

    void AcceptRequestToUpgrade(int RankID);


}

