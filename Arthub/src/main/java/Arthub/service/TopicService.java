package Arthub.service;

import Arthub.dto.ThreadDTO;
import Arthub.dto.TopicDTO;
import Arthub.entity.Comment;
import Arthub.entity.Thread;
import Arthub.entity.Topic;
import Arthub.entity.User;

import java.util.List;

public interface    TopicService {
    List<TopicDTO> getAllTopics(int Type);

    List<Thread> getAllThreads(int TypeID);

    void addTheThread(ThreadDTO threadDTO);

    Thread getThreadByThreadID(int ThreadID);

    List<Comment> getCommentByThreadID(int ThreadID);

    void addCommentToThread(Comment comment);

    boolean ToggleLikeThread(int userID, int ThreadID);
    boolean isThreadLiked(int userID, int threadID);
    List<Thread> getLikedThreads(int userID);
    int getThreadLikeCount(int threadID);
}
