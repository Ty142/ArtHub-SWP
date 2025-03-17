package Arthub.repository;

import Arthub.entity.Withdraw;

import java.util.List;

public interface WithDrawRepository {
    List<Withdraw> listOfWithdrawsInProcess();

    List<Withdraw> listOfWithdrawsAccept();

    int saveWithdraw(Withdraw withdraw);

    Withdraw findById(Long id);

    void acceptWithdraw(int WithDrawID);
}
