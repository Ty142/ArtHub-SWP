package Arthub.repository.impl;

import Arthub.dto.RankDTO;
import Arthub.entity.TypeOfRank;
import Arthub.repository.TypeOfRankRepository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class TypeOffRankRepositoryImpl implements TypeOfRankRepository {
    @Override
    public ArrayList<TypeOfRank> getAllTypeOfRanks() {
        String sql = "SELECT * FROM TypeOfRank";
        ArrayList<TypeOfRank> typeOfRanks = new ArrayList<>();
        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()){
                TypeOfRank typeOfRank = new TypeOfRank();
                typeOfRank.setTypeId(resultSet.getInt("TypeId"));
                typeOfRank.setTypeRankName(resultSet.getString("TypeRankName"));
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
        String sql = "SELECT * FROM TypeOfRank WHERE TypeID = ?";
        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    TypeOfRank typeOfRank = new TypeOfRank();
                    typeOfRank.setTypeId(resultSet.getInt("TypeID"));
                    typeOfRank.setTypeRankName(resultSet.getString("TypeRankName"));
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
        String sql = "SELECT u.RankID, u.AccountID, r.TypeID, r.DayToRentRankAt, r.DayEndPackage " +
                "FROM [Arthub].[dbo].[User] u " +
                "JOIN [Arthub].[dbo].[Rank] r ON u.RankID = r.RankID " +
                "WHERE u.AccountID = ?";  // Đặt giá trị cho tham số ?

        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,accountId);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    RankDTO rankDTO = new RankDTO();
                    rankDTO.setRankID(resultSet.getInt("RankID"));
                    rankDTO.setAccountID(resultSet.getInt("AccountID"));
                    rankDTO.setDayToRentRankAt(resultSet.getString("DayToRentRankAt"));
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
}
