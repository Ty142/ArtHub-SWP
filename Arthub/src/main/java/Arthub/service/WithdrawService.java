package Arthub.service;

import Arthub.entity.Notification;
import Arthub.entity.Withdraw;

import java.util.List;

public interface WithdrawService {

    List<Withdraw> ListWithdrawInProgress();

    List<Withdraw> ListWithdrawAccept();

    Notification addWithdraw(Withdraw withdraw);

    void AcceptWithdraw(int withdrawID);

    Withdraw FindWithdrawByID(int withdrawID);


}
