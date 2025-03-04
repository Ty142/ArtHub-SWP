package Arthub.repository.impl;


import Arthub.entity.TypeOfTopic;
import Arthub.repository.TopicRepository;
import Arthub.repository.TypeOfTopicRepository;
import org.springframework.stereotype.Repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TypeOfTopicRepositoryImpl implements TypeOfTopicRepository {

    @Override
    public List<TypeOfTopic> getAllType() {
        String sql = "SELECT * FROM TypeOfTopic";
        List<TypeOfTopic> typeList = new ArrayList<>();
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TypeOfTopic topicRepository = new TypeOfTopic();

                topicRepository.setTypeID(rs.getInt("TypeID"));
                topicRepository.setTypeName(rs.getString("NameOfType"));
                typeList.add(topicRepository);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return typeList;
    }
}

