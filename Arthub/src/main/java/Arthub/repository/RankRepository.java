package Arthub.repository;

import Arthub.entity.Rank;

public interface RankRepository {

    int AddTypeRankToListRank(Rank rank);

    void AddRankToUserByRankID(int rankID);

}
