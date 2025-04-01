package Arthub.entity;

import java.util.Date;

public class MoneyTransfer {
    private int transferId;
    private int senderUserId;
    private int receiverUserId;
    private double amount;
    private Date transferDate;


    public MoneyTransfer(int transferId, int senderUserId, int receiverUserId, double amount, Date transferDate) {
        this.transferId = transferId;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.amount = amount;
        this.transferDate = transferDate;
    }


    public MoneyTransfer() {
        this.transferId = 0;
        this.senderUserId = 0;
        this.receiverUserId = 0;
        this.amount = 0;
        transferDate = null;
    }
    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    public int getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(int receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }
}
