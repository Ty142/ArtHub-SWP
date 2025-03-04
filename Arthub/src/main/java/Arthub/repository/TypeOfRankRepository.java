package Arthub.repository;

import Arthub.dto.RankDTO;
import Arthub.entity.TypeOfRank;

import java.util.ArrayList;

public interface TypeOfRankRepository {
    ArrayList<TypeOfRank> getAllTypeOfRanks();
    TypeOfRank getTypeOfRankById(int id);

    RankDTO getCurrentRankByAccountId(int accountId);
}
