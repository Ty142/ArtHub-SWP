package Arthub.service.Impl;

import Arthub.dto.RankDTO;
import Arthub.repository.RankRepository;
import Arthub.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    RankRepository rankRepository;
    @Override
    public void AddRankToUser(RankDTO rankDTO) {
        int typeId = rankRepository.AddTypeRankToListRank(rankDTO);
        rankRepository.AddRankToUserByRankID(typeId, rankDTO.getAccountID(),rankDTO.getPrice());
    }
}
