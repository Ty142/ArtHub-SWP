package Arthub.service.Impl;

import Arthub.dto.RankDTO;
import Arthub.repository.RankRepository;
import Arthub.repository.TypeOfRankRepository;
import Arthub.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    RankRepository rankRepository;
    @Autowired
    TypeOfRankRepository typeOfRankRepository;
    @Override
    public void AddRankToUser(RankDTO rankDTO) throws ParseException {
        int typeId = rankRepository.AddTypeRankToListRank(rankDTO);
        rankRepository.AddRankToUserByRankID(typeId, rankDTO.getAccountID(),rankDTO.getPrice());
    }

    @Override
    public void removeRankToExpired(int RankID, int UserID) {
        rankRepository.ChangeRankToExpire(UserID);
        rankRepository.deleteRank(RankID);
    }

    @Override
    public int getTypeOfRankIDByUserID(int UserID) {
        int rankID = typeOfRankRepository.getRankIDByUserID(UserID);
        return typeOfRankRepository.getTypeOfRankIDByRankID(rankID);
    }
}
