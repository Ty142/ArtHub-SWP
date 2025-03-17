package Arthub.service.Impl;

import Arthub.entity.Notification;
import Arthub.entity.Withdraw;
import Arthub.event.UserInteractionEvent;
import Arthub.repository.WithDrawRepository;
import Arthub.service.UserService;
import Arthub.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    WithDrawRepository repository;

    @Autowired
    UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<Withdraw> ListWithdrawInProgress() {
        return repository.listOfWithdrawsInProcess();
    }

    @Override
    public List<Withdraw> ListWithdrawAccept() {
        return repository.listOfWithdrawsAccept();
    }

    @Override
    public Notification addWithdraw(Withdraw withdraw) {
        userService.updateCoinsAmount(withdraw.getUserID(), withdraw.getCoinWithdraw()*(-1));

        Notification notification = repository.saveWithdraw(withdraw);

        UserInteractionEvent event = new UserInteractionEvent(this, notification);
        eventPublisher.publishEvent(event);

        return notification;
    }

    @Override
    public void AcceptWithdraw(int withdrawID) {


        repository.acceptWithdraw(withdrawID);
    }

    @Override
    public Withdraw FindWithdrawByID(int withdrawID) {
        return repository.findById(withdrawID);
    }
}
