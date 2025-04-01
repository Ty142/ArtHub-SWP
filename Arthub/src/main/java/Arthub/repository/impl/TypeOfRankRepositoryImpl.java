package Arthub.repository.impl;

import Arthub.dto.RankDTO;
import Arthub.entity.TypeOfRank;
import Arthub.repository.TypeOfRankRepository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class TypeOfRankRepositoryImpl implements TypeOfRankRepository {
    @Override
    public ArrayList<TypeOfRank> getAllTypeOfRanks() {
        String sql = "SELECT * FROM ranktype";
        ArrayList<TypeOfRank> typeOfRanks = new ArrayList<>();
        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()){
                TypeOfRank typeOfRank = new TypeOfRank();
                typeOfRank.setTypeId(resultSet.getInt("TypeId"));
                typeOfRank.setTypeRankName(resultSet.getString("RankTypeName"));
                typeOfRank.setPrice(resultSet.getDouble("Price"));
                typeOfRanks.add(typeOfRank);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return typeOfRanks;
    }

    @Override
    public TypeOfRank getTypeOfRankById(int id) {
        String sql = "SELECT * FROM ranktype WHERE TypeID = ?";
        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    TypeOfRank typeOfRank = new TypeOfRank();
                    typeOfRank.setTypeId(resultSet.getInt("TypeID"));
                    typeOfRank.setTypeRankName(resultSet.getString("RankTypeName"));
                    typeOfRank.setPrice(resultSet.getDouble("Price"));
                    return typeOfRank;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public RankDTO getCurrentRankByAccountId(int accountId) {
        String sql = "SELECT u.UserRankID, u.AccountID, r.TypeID, r.RankStartDate, r.DayEndPackage " +
                "FROM User u " +
                "JOIN UserRank r ON u.UserRankID = r.UserRankID " +
                "WHERE u.AccountID = ?";  // Đặt giá trị cho tham số ?

        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,accountId);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    RankDTO rankDTO = new RankDTO();
                    rankDTO.setRankID(resultSet.getInt("UserRankID"));
                    rankDTO.setAccountID(resultSet.getInt("AccountID"));
                    rankDTO.setDayToRentRankAt(resultSet.getString("RankStartDate"));
                    rankDTO.setTypeID(resultSet.getInt("TypeID"));
                    rankDTO.setDayToEndRank(resultSet.getString("DayEndPackage"));
                    return rankDTO;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public int getRankIDByUserID(int UserID) {
        String sql = "SELECT RankID From [User] where UserID = ?";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, UserID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("RankID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public int getTypeOfRankIDByRankID(int rankID) {
        String sql = "SELECT TypeID From UserRank where UserRankID = ? ";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, rankID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("TypeID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
