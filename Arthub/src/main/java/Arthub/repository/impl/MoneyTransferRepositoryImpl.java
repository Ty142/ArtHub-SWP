package Arthub.repository.impl;

import Arthub.entity.MoneyTransfer;
import Arthub.entity.Notification;
import Arthub.repository.MoneyTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MoneyTransferRepositoryImpl implements MoneyTransferRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Notification saveNewMoneyTransfer(MoneyTransfer moneyTransfer) {
        String sql = "{call sp_InsertMoneyTransferAndNotification(?, ?, ?)}";
        return jdbcTemplate.queryForObject(sql,new Object[]{moneyTransfer.getSenderUserId(),
                moneyTransfer.getReceiverUserId(), moneyTransfer.getAmount()},
                new BeanPropertyRowMapper<>(Notification.class) );
    }
}
