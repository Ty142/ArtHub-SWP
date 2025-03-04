package Arthub.service.Impl;

import Arthub.converter.ThreadConverter;
import Arthub.dto.ThreadDTO;
import Arthub.dto.TopicDTO;
import Arthub.entity.Thread;
import Arthub.entity.Topic;
import Arthub.entity.User;
import Arthub.repository.ThreadRepository;
import Arthub.repository.TopicRepository;
import Arthub.repository.UserRepository;
import Arthub.service.TopicService;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;
    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    UserRepository userRepository;
    ThreadConverter threadConverter = new ThreadConverter();
    @Override
    public List<TopicDTO> getAllTopics(int TypeID) {
        List<TopicDTO> topicsDTO = new ArrayList<>();
        List<Topic> topics = topicRepository.getAllTopicsPostByTypeTopicID(TypeID);
        for (Topic topic : topics) {
            Thread thread = threadRepository.getTheLastUserIDInThreadByTopicId(topic.getTopicId());
            TopicDTO topicDTO = new TopicDTO();
            topicDTO.setTitle(topic.getTitle());
            topicDTO.setDescription(topic.getDescription());
            topicDTO.setTypeID(topic.getTypeId());
            topicDTO.setTopicID(topic.getTopicId());
            topicDTO.setTotalThread(threadRepository.TotalThreadByTopicID(topic.getTopicId()));
            if (thread != null) {
                topicDTO.setUserID(thread.getUserID());
            } else {
                topicDTO.setUserID(0);
            }
            topicsDTO.add(topicDTO);
        }
        return topicsDTO;
    }

    @Override
    public List<Thread> getAllThreads(int TypeID) {
        return threadRepository.GetThreadsByTopicId(TypeID);
    }

    @Override
    public void addTheThread(ThreadDTO threadDTO) {
        Thread thread = threadConverter.convertFromThreadDTOToEntity(threadDTO);
        threadRepository.InsertThread(thread);
    }

}
