package Arthub.repository;

import Arthub.entity.Topic;

import java.util.List;

public interface TopicRepository {

    List<Topic> getAllTopicsPostByTypeTopicID(int TypeID);

    int getIDTopicByNameTopic(String Name);

    Topic getTopicByTopicID(int TopicID);


}
