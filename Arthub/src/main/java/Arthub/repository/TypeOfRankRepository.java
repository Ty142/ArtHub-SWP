package Arthub.repository;

import Arthub.dto.RankDTO;
import Arthub.entity.Tag;
import Arthub.entity.TypeOfRank;
import Arthub.dto.TypeOfRankDTO;
import Arthub.entity.Rank;

import java.util.ArrayList;

public interface TypeOfRankRepository {
    ArrayList<TypeOfRank> getAllTypeOfRanks();
    TypeOfRank getTypeOfRankById(int id);

    RankDTO getCurrentRankByAccountId(int accountId);
}
