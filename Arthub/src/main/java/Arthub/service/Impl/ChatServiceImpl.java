package Arthub.service.Impl;

import Arthub.entity.Chat;
import Arthub.entity.Message;
import Arthub.event.ChatEvent;
import Arthub.entity.User;
import Arthub.event.UserInteractionEvent;
import Arthub.repository.ChatRepository;
import Arthub.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Override
    public List<User> getChatProfileByUserId(int userId) {
        try {
            return chatRepository.getChatProfileByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Chat> getChatByUserId(int userId) {
        try {
            return chatRepository.getChatByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Message> getMessageBySenderId(int senderId) {
        try {
            return chatRepository.getMessageBySenderId(senderId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createChat(Chat chat) {
        try {
            Boolean check =  chatRepository.createChat(chat);

            chat.setStatus((byte) 0);

            ChatEvent event = new ChatEvent(this,chat);
            eventPublisher.publishEvent(event);

            return check;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateStatusChatByUserId(int userId) {
        try {
            chatRepository.updateStatusChatByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
