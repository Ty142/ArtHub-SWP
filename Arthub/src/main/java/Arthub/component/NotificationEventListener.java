package Arthub.component;

import Arthub.event.UserInteractionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;




    @EventListener
    public void handleUserInteractionEvent(UserInteractionEvent event) {

        messagingTemplate.convertAndSend("/topic/notifications/"+
                event.getNotification().getProfileNoti(), event.getNotification());
    }
}
