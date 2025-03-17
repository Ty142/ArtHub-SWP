package Arthub.service;

import Arthub.entity.MoneyTransfer;
import Arthub.entity.Notification;

public interface MoneyTransferService {
    Notification saveNewMoneyTransfer(MoneyTransfer moneyTransfer);
}
