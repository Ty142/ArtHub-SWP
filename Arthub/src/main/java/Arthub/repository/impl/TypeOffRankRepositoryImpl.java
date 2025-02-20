package Arthub.repository.impl;

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
}
