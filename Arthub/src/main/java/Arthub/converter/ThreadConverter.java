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
}
