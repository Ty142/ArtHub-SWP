package Arthub.service;

import Arthub.entity.Withdraw;

import java.util.List;

public interface WithdrawService {

    List<Withdraw> ListWithdrawInProgress();

    List<Withdraw> ListWithdrawAccept();

    int addWithdraw(Withdraw withdraw);

    void AcceptWithdraw(int withdrawID);


}
