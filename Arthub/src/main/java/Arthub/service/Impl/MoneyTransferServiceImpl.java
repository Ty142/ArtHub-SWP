package Arthub.service.Impl;

import Arthub.entity.MoneyTransfer;
import Arthub.entity.Notification;
import Arthub.event.UserInteractionEvent;
import Arthub.repository.MoneyTransferRepository;
import Arthub.service.MoneyTransferService;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

    @Autowired
    MoneyTransferRepository moneyTransferRepository;
    @Autowired
    UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public Notification saveNewMoneyTransfer(MoneyTransfer moneyTransfer) {
        try {
            userService.updateCoinsAmount(moneyTransfer.getSenderUserId(), moneyTransfer.getAmount()*(-1));
            userService.updateCoinsAmount(moneyTransfer.getReceiverUserId(), moneyTransfer.getAmount());
            Notification notification = moneyTransferRepository.saveNewMoneyTransfer(moneyTransfer);

            UserInteractionEvent event = new UserInteractionEvent(this,notification);
            eventPublisher.publishEvent(event);

            return notification;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
