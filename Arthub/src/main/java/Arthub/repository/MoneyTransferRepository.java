package Arthub.repository;

import Arthub.entity.MoneyTransfer;
import Arthub.entity.Notification;

public interface MoneyTransferRepository {
    Notification saveNewMoneyTransfer(MoneyTransfer moneyTransfer);
}
