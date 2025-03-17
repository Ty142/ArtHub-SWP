package Arthub.repository;

import Arthub.entity.MoneyTransfer;
import Arthub.entity.Notification;
import Arthub.entity.User;

public interface MoneyTransferRepository {
    Notification saveNewMoneyTransfer(MoneyTransfer moneyTransfer);
    User getUserByMoneyTransferId(int id);
}
