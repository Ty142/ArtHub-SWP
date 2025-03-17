package Arthub.repository.impl;

import Arthub.entity.Chat;
import Arthub.entity.Message;
import Arthub.entity.User;
import Arthub.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


@Repository
public class ChatRepositoryImpl implements ChatRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public boolean createChat(Chat chat) {
        // Đăng ký tham số trả về ở vị trí 1
        // Thiết lập tham số User1Id và User2Id
        return Boolean.TRUE.equals(jdbcTemplate.execute(
                new CallableStatementCreator() {
                    @Override
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        CallableStatement cs = con.prepareCall("{? = call dbo.sp_InsertOrUpdateChat(?, ?)}");
                        // Đăng ký tham số trả về ở vị trí 1
                        cs.registerOutParameter(1, Types.INTEGER);
                        // Thiết lập tham số User1Id và User2Id
                        cs.setInt(2, chat.getUser1Id());
                        cs.setInt(3, chat.getUser2Id());
                        return cs;
                    }
                },
                new CallableStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                        cs.execute();
                        int result = cs.getInt(1);
                        return result == 1;
                    }
                }
        ));
    }


    @Override
    public List<User> getChatProfileByUserId(int userId) {
        String sql = "SELECT u.*\n" +
                "FROM Chats c\n" +
                "JOIN [User] u\n" +
                "   ON (c.User1Id = ? AND u.UserID = c.User2Id)\n" +
                "   OR (c.User2Id = ? AND u.UserID = c.User1Id)\n" +
                "WHERE c.User1Id = ? OR c.User2Id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId,userId,userId,userId}, new BeanPropertyRowMapper<>(User.class));

    }

    @Override
    public List<Chat> getChatByUserId(int userId) {
        String sql = "SELECT c.*\n" +
                "FROM Chats c\n" +
                "WHERE c.User1Id = ? OR c.User2Id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId,userId}, new BeanPropertyRowMapper<>(Chat.class));

    }

    @Override
    public List<Message> getMessageBySenderId(int senderId) {
        String sql = "SELECT * FROM Messages where SenderId = ? or ReceiverId =?;";
        return jdbcTemplate.query(sql, new Object[]{senderId,senderId}, new BeanPropertyRowMapper<>(Message.class));
    }

    @Override
    public void updateStatusChatByUserId(int userId) {
        String sql = "UPDATE [Arthub].[dbo].[Chats]\n" +
                "SET Status = 1\n" +
                "WHERE User1Id = ?\n" +
                "   OR User2Id = ?";
        jdbcTemplate.update(sql, userId,userId);
    }
}
