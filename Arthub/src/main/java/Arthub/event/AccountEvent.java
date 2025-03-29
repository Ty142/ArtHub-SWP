package Arthub.event;

import Arthub.entity.Account;
import org.springframework.context.ApplicationEvent;

public class AccountEvent extends ApplicationEvent {

    private int account;

    public AccountEvent(Object source , int account) {
        super(source);
        this.account = account;
    }

    public int getAccount() {
        return account;
    }
    public void setAccount(int account) {
        this.account = account;
    }
}
