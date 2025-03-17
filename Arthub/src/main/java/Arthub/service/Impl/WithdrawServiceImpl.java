package Arthub.service.Impl;

import Arthub.entity.Withdraw;
import Arthub.repository.WithDrawRepository;
import Arthub.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    WithDrawRepository repository;
    @Override
    public List<Withdraw> ListWithdrawInProgress() {
        return repository.listOfWithdrawsInProcess();
    }

    @Override
    public List<Withdraw> ListWithdrawAccept() {
        return repository.listOfWithdrawsAccept();
    }

    @Override
    public int addWithdraw(Withdraw withdraw) {
        return repository.saveWithdraw(withdraw);
    }

    @Override
    public void AcceptWithdraw(int withdrawID) {
        repository.acceptWithdraw(withdrawID);
    }
}
