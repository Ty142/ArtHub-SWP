package Arthub.repository.impl;


import Arthub.entity.Topic;
import Arthub.entity.TypeOfTopic;
import Arthub.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicRepositoryImpl implements TopicRepository {



    @Override
    public List<Topic> getAllTopicsPostByTypeTopicID(int TypeID) {
        String sql = "SELECT * FROM Topic WHERE TypeID =? ORDER BY TopicID DESC";
        List<Topic> topics = new ArrayList<>();
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, TypeID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setTopicId(rs.getInt("TopicID"));
                topic.setTitle(rs.getString("TitleTopic"));
                topic.setDescription(rs.getString("Description"));
                topic.setTypeId(rs.getInt("TypeID"));
                topics.add(topic);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return topics;

    }
}
