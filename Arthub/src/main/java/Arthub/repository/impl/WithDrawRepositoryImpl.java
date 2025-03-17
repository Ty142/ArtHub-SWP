package Arthub.repository.impl;

import Arthub.dto.WithdrawDTO;
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
    public int saveWithdraw(Withdraw withdraw) {
        String sql = "INSERT INTO WithdrawCoin (UserID, CoinWithdraw, DateRequest, BankName, BankNumber, Status) VALUES (?,?,?,?,?,?)";
        int generated = -1;
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,withdraw.getUserID());
            ps.setDouble(2, withdraw.getCoinWithdraw()*0.9);
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            ps.setTimestamp(3, timestamp);
            ps.setString(4, withdraw.getBankName());
            ps.setString(5, withdraw.getBankNumber());
            ps.setInt(6, 0);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generated = rs.getInt(1);
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return generated;
    }

    @Override
    public Withdraw findById(Long id) {
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
