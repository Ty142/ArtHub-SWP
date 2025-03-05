package Arthub.converter;

import Arthub.dto.ThreadDTO;
import Arthub.entity.Thread;

public class ThreadConverter {
    public Thread convertFromThreadDTOToEntity(ThreadDTO threadDTO) {
        Thread thread = new Thread();
        thread.setThreadID(threadDTO.getThreadID());
        thread.setTitleThread(threadDTO.getTitleThread());
        thread.setThreadDescription(threadDTO.getThreadDescription());
        thread.setDateCreated(threadDTO.getDateCreated());
        thread.setComments(threadDTO.getComments());
        thread.setLikes(threadDTO.getLikes());
        thread.setTopicID(threadDTO.getTopicID());
        thread.setUserID(threadDTO.getUserID());
        return thread;
    }

    public ThreadDTO convertFromThreadToThreadDTO(Thread thread) {
        ThreadDTO threadDTO = new ThreadDTO();
        threadDTO.setThreadID(thread.getThreadID());
        threadDTO.setTitleThread(thread.getTitleThread());
        threadDTO.setThreadDescription(thread.getThreadDescription());
        threadDTO.setDateCreated(thread.getDateCreated());
        threadDTO.setComments(thread.getComments());
        threadDTO.setLikes(thread.getLikes());
        threadDTO.setTopicID(thread.getTopicID());
        threadDTO.setUserID(thread.getUserID());
        return threadDTO;
    }
}
