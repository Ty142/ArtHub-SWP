package Arthub.component;


import Arthub.event.ChatEvent;
import Arthub.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatEventListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleChatEvent(ChatEvent chatEvent) {
        messagingTemplate.convertAndSend("/topic/chat/" + chatEvent.getChat().getUser2Id(), chatEvent.getChat());
    }

    @EventListener
    public void handleMessageEvent(MessageEvent messageEvent) {
        messagingTemplate.convertAndSend("/topic/message/" + messageEvent.getMessage().getReceiverId()
                , messageEvent.getMessage());
    }


}
