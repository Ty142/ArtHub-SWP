package Arthub.entity;

public class Chat {
    private int chatId;
    private int user1Id;
    private int user2Id;
    private byte status;

    public Chat(int chatId, int userId1, int userId2, byte status) {
        this.chatId = chatId;
        this.user1Id = userId1;
        this.user2Id = userId2;
        this.status = status;
    }

    public Chat() {
        this.chatId = 0;
        this.user1Id = 0;
        this.user2Id = 0;
        this.status = 0;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", userId1=" + user1Id +
                ", userId2=" + user2Id +
                ", status=" + status +
                '}';
    }
}
