package Arthub.repository.impl;

import Arthub.dto.WithdrawDTO;
import Arthub.entity.Notification;
import Arthub.entity.Withdraw;
import Arthub.repository.WithDrawRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WithDrawRepositoryImpl implements WithDrawRepository {
    @Override
    public List<Withdraw> listOfWithdrawsInProcess() {
        String sql = "select * From WithdrawCoin where status = 0";
        List<Withdraw> withdraws = new ArrayList<>();
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Withdraw withdraw = new Withdraw();
                withdraw.setWithdrawID(rs.getInt("WithdrawID"));
                withdraw.setUserID(rs.getInt("UserID"));
                withdraw.setCoinWithdraw(rs.getDouble("CoinWithdraw"));
                Timestamp sqlTimestamp = rs.getTimestamp("DateRequest");
                if (sqlTimestamp != null) {
                    LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                    withdraw.setDateRequest(localDateTime);
                }
                withdraw.setBankName(rs.getString("BankName"));
                withdraw.setBankNumber(rs.getString("BankNumber"));
                withdraw.setStatus(rs.getInt("status"));
                withdraws.add(withdraw);
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return withdraws;
    }

    @Override
    public List<Withdraw> listOfWithdrawsAccept() {
        String sql = "select * From WithdrawCoin where status = 1";
        List<Withdraw> withdraws = new ArrayList<>();
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Withdraw withdraw = new Withdraw();
                withdraw.setWithdrawID(rs.getInt("WithdrawID"));
                withdraw.setUserID(rs.getInt("UserID"));
                withdraw.setCoinWithdraw(rs.getDouble("CoinWithdraw"));
                Timestamp sqlTimestamp = rs.getTimestamp("DateRequest");
                if (sqlTimestamp != null) {
                    LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                    withdraw.setDateRequest(localDateTime);
                }
                withdraw.setBankName(rs.getString("BankName"));
                withdraw.setBankNumber(rs.getString("BankNumber"));
                withdraw.setStatus(rs.getInt("status"));
                withdraws.add(withdraw);
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return withdraws;
    }

    @Override
    public Notification saveWithdraw(Withdraw withdraw) {
        String sql = "{CALL sp_InsertWithdrawAndNotification(?,?,?,?)}";

        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            CallableStatement cs = conn.prepareCall(sql);

            cs.setInt(1, withdraw.getUserID());
            cs.setDouble(2, withdraw.getCoinWithdraw());
            cs.setString(3, withdraw.getBankName());
            cs.setString(4, withdraw.getBankNumber());

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                return new Notification(
                        rs.getInt("NotificationID"),
                        rs.getString("Message"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getInt("ProfileNoti"),
                        rs.getDouble("Amount")
                );
            }

            rs.close();
            cs.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Withdraw findById(int id) {
        String sql = "SELECT * FROM WithdrawCoin WHERE WithdrawID =?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Withdraw withdraw = new Withdraw();
                withdraw.setWithdrawID(rs.getInt("WithdrawID"));
                withdraw.setUserID(rs.getInt("UserID"));
                withdraw.setCoinWithdraw(rs.getDouble("CoinWithdraw"));
                Timestamp sqlTimestamp = rs.getTimestamp("DateRequest");
                if (sqlTimestamp != null) {
                    LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                    withdraw.setDateRequest(localDateTime);
                }
                withdraw.setBankName(rs.getString("BankName"));
                withdraw.setBankNumber(rs.getString("BankNumber"));
                withdraw.setStatus(rs.getInt("status"));
                return withdraw;
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void acceptWithdraw(int WithDrawID) {
        String sql = "Update WithdrawCoin set status = 1 where WithdrawID = ?";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, WithDrawID);
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
