package Arthub.repository.impl;

import Arthub.repository.ActivityRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ActivityRepositoryImpl implements ActivityRepository {

    @Override
    public String getActivityNameByActivityID(int id) {
        String sql = "SELECT ActivityName from Activity where ActivityID = ?";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn =  db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("ActivityName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
