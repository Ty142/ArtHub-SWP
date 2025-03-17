package Arthub.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Message {
    private int messageId;
    private int senderId;
    private int receiverId;
    private String messageContent;
    private LocalDateTime dateSent;
    private byte isRead;

    public Message(int messageId, int senderId, int receiverId, String messageContent, LocalDateTime dateSent, byte isRead) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageContent = messageContent;
        this.dateSent = dateSent;
        this.isRead = isRead;
    }

    public Message(){
        this.messageId = 0;
        this.senderId = 0;
        this.receiverId = 0;
        this.messageContent = "";
        this.dateSent = null;
        this.isRead = 0;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDateTime dateSent) {
        this.dateSent = dateSent;
    }

    public byte getIsRead() {
        return isRead;
    }

    public void setIsRead(byte isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", messageContent='" + messageContent + '\'' +
                ", dateSent=" + dateSent +
                ", isRead=" + isRead +
                '}';
    }
}
