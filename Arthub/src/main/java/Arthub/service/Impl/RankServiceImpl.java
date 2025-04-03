package Arthub.service.Impl;

import Arthub.dto.ArtistFormDTO;
import Arthub.dto.RankDTO;
import Arthub.entity.TypeOfRank;
import Arthub.repository.RankRepository;
import Arthub.repository.TypeOfRankRepository;
import Arthub.repository.UserRepository;
import Arthub.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    RankRepository rankRepository;
    @Autowired
    TypeOfRankRepository typeOfRankRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public void AddRankToUser(RankDTO rankDTO) throws ParseException, SQLException {
        int typeId = rankRepository.AddTypeRankToListRank(rankDTO);
        rankRepository.AddRankToUserByRankID(typeId, rankDTO.getAccountID(),rankDTO.getPrice());
        int userID = userRepository.getUserByAccountId(rankDTO.getAccountID()).getUserId();
        userRepository.updateLimitByTypeID(rankDTO.getTypeID(), userID);
    }

    @Override
    public void removeRankToExpired(int RankID, int UserID) {
        rankRepository.ChangeRankToExpire(UserID);
        rankRepository.deleteRank(RankID);
        userRepository.updateLimitByExpired(UserID);
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

    @Override
    public List<RankDTO> getListArtistUpgrade() {
        return rankRepository.getAllRanksArtist();
    }

    @Override
    public void AcceptUpgradeArtist(int RankID) {
        rankRepository.AcceptRequestToUpgrade(RankID);
    }

    @Override
    public int getTheNewRankID(ArtistFormDTO dto) throws ParseException {
        RankDTO r = new RankDTO();
        r.setTypeID(1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        r.setDayToRentRankAt(dateFormat.format(r.getDayToRentRankAt()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dto.getDateCreation());
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        r.setDayToEndRank(dateFormat.format(calendar.getTime()));
        int newRankID = rankRepository.AddTypeRankToListRank(r);
        rankRepository.updateNewRankToUser(dto.getUserId(), newRankID);
        return newRankID;
    }


}
