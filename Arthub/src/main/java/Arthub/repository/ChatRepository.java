package Arthub.repository;

import Arthub.entity.Chat;
import Arthub.entity.Message;
import Arthub.entity.User;

import java.util.List;

public interface ChatRepository {
    boolean createChat(Chat chat);
    List<User> getChatProfileByUserId(int userId);
    List<Chat> getChatByUserId(int userId);

    List<Message> getMessageBySenderId(int senderId);

    void updateStatusChatByUserId(int userId);
}
