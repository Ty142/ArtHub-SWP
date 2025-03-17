package Arthub.event;

import Arthub.entity.Message;
import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {

    private final Message message;
    public MessageEvent(Object source, Message message) {
        super(source);
        this.message = message;
    }
    public Message getMessage() {
        return message;
    }
}
