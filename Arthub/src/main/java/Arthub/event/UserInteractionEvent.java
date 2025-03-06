package Arthub.event;

import Arthub.entity.Follow;
import Arthub.entity.Interact;
import Arthub.entity.Notification;
import org.springframework.context.ApplicationEvent;

public class UserInteractionEvent extends ApplicationEvent {

    private final Notification notification;

    public UserInteractionEvent(Object source, Notification notification) {
        super(source);

        this.notification = notification;
    }

    public Notification getNotification() {
        return notification;
    }
}
