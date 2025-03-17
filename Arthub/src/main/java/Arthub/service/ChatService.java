package Arthub.service;

import Arthub.entity.Chat;
import Arthub.entity.Message;
import Arthub.entity.User;

import java.util.List;

public interface ChatService {
    List<User> getChatProfileByUserId(int userId);
    List<Chat> getChatByUserId(int userId);
    List<Message> getMessageBySenderId(int senderId);
    boolean createChat(Chat chat);
    void updateStatusChatByUserId(int userId);

}
