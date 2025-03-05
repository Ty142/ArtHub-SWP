package Arthub.repository;

import Arthub.entity.Thread;

import java.util.List;

public interface ThreadRepository {

    List<Thread> GetThreadsByTopicId(int topicID);


    Thread getTheLastUserIDInThreadByTopicId(int topicID);

    int TotalThreadByTopicID(int topicID);

    void InsertThread(Thread thread);

    List<Thread> GetThreadsByThreadId(int threadID);
}
