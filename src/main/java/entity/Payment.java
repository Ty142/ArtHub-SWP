package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Payment {
    private int paymentId;
    private double amount;
    private Date createdAt;
    private int userId;
    private byte status;


    public Payment(){}
}
