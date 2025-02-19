package Arthub.repository;

import Arthub.entity.TypeOfRank;
import Arthub.dto.TypeOfRankDTO;

import java.util.ArrayList;

public interface TypeOfRankRepository {
    ArrayList<TypeOfRank> getAllTypeOfRanks();
    TypeOfRank getTypeOfRankById(int id);
}
