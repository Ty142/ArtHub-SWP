package Arthub.repository.impl;

import Arthub.entity.Message;
import Arthub.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void saveMessage(Message message) {
        String sql = "INSERT INTO [Arthub].[dbo].[Messages] " +
                "([SenderId], [ReceiverId], [MessageContent], [DateSent]) " +
                "VALUES (?, ?, ?, GETDATE())";
        jdbcTemplate.update(sql,
                message.getSenderId(),
                message.getReceiverId(),
                message.getMessageContent());
    }
}
