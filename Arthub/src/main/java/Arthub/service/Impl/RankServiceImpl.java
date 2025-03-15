package Arthub.service.Impl;

import Arthub.dto.RankDTO;
import Arthub.entity.TypeOfRank;
import Arthub.repository.RankRepository;
import Arthub.repository.TypeOfRankRepository;
import Arthub.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;
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

    @Override
    public TypeOfRank getNameOfRankID(int accountID) {
        int userID = userRepository.getUserByAccountId(accountID).getUserId();
        int typeID = getTypeOfRankIDByUserID(userID);
        return typeOfRankRepository.getTypeOfRankById(typeID);
    }

}
