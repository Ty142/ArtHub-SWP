package Arthub.service.Impl;

import Arthub.converter.ThreadConverter;
import Arthub.dto.ThreadDTO;
import Arthub.dto.TopicDTO;
import Arthub.entity.Comment;
import Arthub.entity.Thread;
import Arthub.entity.Topic;
import Arthub.entity.User;
import Arthub.repository.*;
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
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    InteractRepository interactRepository;
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
                topicDTO.setDateCreated(thread.getDateCreated());
            }
            else {
                topicDTO.setUserID(0);
            topicDTO.setDateCreated(null); }

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

    @Override
    public Thread getThreadByThreadID(int ThreadID) {
       return threadRepository.GetThreadsByThreadId(ThreadID);
    }

    @Override
    public List<Comment> getCommentByThreadID(int ThreadID) {
        return commentRepository.getAllCommentsByThreadID(ThreadID);
    }

    @Override
    public void addCommentToThread(Comment comment) {
        commentRepository.saveCommentInThread(comment);
    }

    @Override
    public boolean ToggleLikeThread(int userID, int ThreadID) {
       return interactRepository.ToggleLikeThread(userID, ThreadID);

    }

    @Override
    public boolean isThreadLiked(int userID, int threadID) {
        return interactRepository.isThreadLiked(userID, threadID);
    }

    @Override
    public List<Thread> getLikedThreads(int userID) {
        return interactRepository.getLikedThreads(userID);
    }

    @Override
    public int getThreadLikeCount(int threadID) {
        return interactRepository.getThreadLikeCount(threadID);
    }

    @Override
    public void countCommentInThread(int threadID) {
        threadRepository.countCommentInThread(threadID);
    }


}
