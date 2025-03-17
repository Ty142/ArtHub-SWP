package Arthub.service.Impl;

import Arthub.entity.Message;
import Arthub.event.MessageEvent;
import Arthub.repository.MessageRepository;
import Arthub.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void saveMessage(Message message) {
        try {
            messageRepository.saveMessage(message);

            MessageEvent event = new MessageEvent(this, message);
            eventPublisher.publishEvent(event);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
