package Arthub.component;

import Arthub.entity.Notification;
import Arthub.event.UserInteractionEvent;
import Arthub.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Giả sử bạn có NotificationRepository để lưu notification vào DB (có thể dùng JDBC hay JPA)


    @EventListener
    public void handleUserInteractionEvent(UserInteractionEvent event) {

        messagingTemplate.convertAndSend("/topic/notifications/"+
                event.getNotification().getProfileNoti(), event.getNotification());
    }
}
