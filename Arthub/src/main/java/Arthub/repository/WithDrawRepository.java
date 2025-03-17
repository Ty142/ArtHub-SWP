package Arthub.repository;

import Arthub.entity.Notification;
import Arthub.entity.Withdraw;

import java.util.List;

public interface WithDrawRepository {
    List<Withdraw> listOfWithdrawsInProcess();

    List<Withdraw> listOfWithdrawsAccept();

    Notification saveWithdraw(Withdraw withdraw);

    Withdraw findById(int id);

    void acceptWithdraw(int WithDrawID);
}
