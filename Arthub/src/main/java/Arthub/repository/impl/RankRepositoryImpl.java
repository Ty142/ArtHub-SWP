package Arthub.repository.impl;

import Arthub.entity.Rank;
import Arthub.repository.RankRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class RankRepositoryImpl implements RankRepository {
    @Override
    public int AddTypeRankToListRank(Rank rank) {
        String sql = "INSERT INTO Rank values(?,?)";
        int generated = -1;
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, rank.getDayToRentRankAt());
            ps.setInt(2, rank.getTypeID());
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
    public void AddRankToUserByRankID(int rankID) {
        String sql = "Update [User] set RankID = ?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rankID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
