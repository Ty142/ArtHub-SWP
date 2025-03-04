package Arthub.repository;

import Arthub.dto.RankDTO;

public interface RankRepository {

    int AddTypeRankToListRank(RankDTO rankDTO);

    void AddRankToUserByRankID(int rankID, int AccountID, double price);


}
