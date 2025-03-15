package Arthub.repository.impl;

import Arthub.converter.RankConverter;
import Arthub.dto.RankDTO;
import Arthub.entity.Rank;
import Arthub.repository.AccountRepository;
import Arthub.repository.RankRepository;
import Arthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RankRepositoryImpl implements RankRepository {
    @Autowired
    UserRepository userRepository;

    @Override
    public int AddTypeRankToListRank(RankDTO rankDTO) throws ParseException {
        RankConverter rankConverter = new RankConverter();
        Rank rank = rankConverter.ConvertRankDTOToRankEntity(rankDTO);
        String sql = "INSERT INTO Rank values(?,?,?)";
        int generated = -1;
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(rank.getDayToRentRankAt().getTime()));
            ps.setInt(2, rank.getTypeID());
            ps.setDate(3, new java.sql.Date(rank.getDayToEndRank().getTime()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generated = rs.getInt(1);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return generated;
    }

    @Override
    public void     AddRankToUserByRankID(int rankID,int AccountID, double price) {
        String sql = "Update [User] set RankID = ?, Coins =? where AccountID = ?";
        double amountCoins = userRepository.getCoinsAmount(AccountID);
        double newAmountCoins = amountCoins - price;
         try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rankID);
            ps.setDouble(2, newAmountCoins);
            ps.setInt(3, AccountID);
            ps.executeUpdate();
             conn.close();
             ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void ChangeRankToExpire(int UserID) {
        String sql = "update [User] set RankID = ? where UserID = ?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, UserID);
            ps.executeUpdate();
             conn.close();
             ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRank(int RankID) {
        String sql = "DELETE FROM Rank WHERE RankID =?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, RankID);
            ps.executeUpdate();
             conn.close();
             ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RankDTO> GetRankList() {
        String sql = "SELECT * FROM Rank";
        List<RankDTO> rankList = new ArrayList<>();
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                RankDTO rank = new RankDTO();
                rank.setRankID(rs.getInt("RankID"));
                rank.setPrice(rs.getInt("Price"));
                rank.setDayToRentRankAt(rs.getString("DayToRentRankAt"));
                rank.setDayToEndRank(rs.getString("DayToEndRank"));
                rank.setTypeID(rs.getInt("TypeID"));
                rankList.add(rank);
            }
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rankList;
    }



}
