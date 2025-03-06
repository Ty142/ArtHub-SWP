package Arthub.service.Impl;

import Arthub.entity.TypeOfTopic;
import Arthub.repository.TypeOfTopicRepository;
import Arthub.service.TypeOfTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfTopicServiceImpl implements TypeOfTopicService {

    @Autowired
    TypeOfTopicRepository topicRepository;
    @Override
    public List<TypeOfTopic> GetAllTypeOfTopics() {
            return topicRepository.getAllType();
    }
}
