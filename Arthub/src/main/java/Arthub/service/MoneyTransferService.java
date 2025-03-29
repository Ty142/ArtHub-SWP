package Arthub.service;

import Arthub.entity.MoneyTransfer;
import Arthub.entity.Notification;
import Arthub.entity.User;

public interface MoneyTransferService {
    Notification saveNewMoneyTransfer(MoneyTransfer moneyTransfer);
    User getUserByMoneyTransferId(int id);
}
