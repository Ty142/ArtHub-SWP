package Arthub.component;

import Arthub.event.AccountEvent;
import Arthub.event.ChatEvent;
import Arthub.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountEventListener {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleLockAccount(AccountEvent AccountEvent) {
        messagingTemplate.convertAndSend("/topic/lockUser/" + AccountEvent.getAccount()
                , AccountEvent);
    }

}
