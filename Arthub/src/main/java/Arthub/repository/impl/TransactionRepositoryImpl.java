package Arthub.repository.impl;

import Arthub.entity.Transaction;
import Arthub.repository.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    @Override
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        String sql = "SELECT * FROM [Transaction]";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionID(rs.getInt("TransactionID"));
                Timestamp sqlTimestamp = rs.getTimestamp("BuyDate");
                if (sqlTimestamp != null) {
                    LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                    transaction.setBuyDate(localDateTime);
                }
                transaction.setPrice(rs.getDouble("Price"));
                transaction.setArtworkID(rs.getInt("ArtworkID"));
                transaction.setSellerID(rs.getInt("SellerID"));
                transaction.setBuyerID(rs.getInt("BuyerID"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByBuyerID(int BuyerID) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        String sql = "SELECT * FROM [Transaction] WHERE BuyerID =?";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, BuyerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionID(rs.getInt("TransactionID"));
                Timestamp sqlTimestamp = rs.getTimestamp("BuyDate");
                if (sqlTimestamp != null) {
                    LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                    transaction.setBuyDate(localDateTime);
                }
                transaction.setPrice(rs.getDouble("Price"));
                transaction.setArtworkID(rs.getInt("ArtworkID"));
                transaction.setSellerID(rs.getInt("SellerID"));
                transaction.setBuyerID(rs.getInt("BuyerID"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsBySellerID(int SellerID) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        String sql = "SELECT * FROM [Transaction] WHERE SellerID =?";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, SellerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionID(rs.getInt("TransactionID"));
                Timestamp sqlTimestamp = rs.getTimestamp("BuyDate");
                if (sqlTimestamp != null) {
                    LocalDateTime localDateTime = sqlTimestamp.toLocalDateTime();
                    transaction.setBuyDate(localDateTime);
                }
                transaction.setPrice(rs.getDouble("Price"));
                transaction.setArtworkID(rs.getInt("ArtworkID"));
                transaction.setSellerID(rs.getInt("SellerID"));
                transaction.setBuyerID(rs.getInt("BuyerID"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        String sql = "INSERT INTO [Transaction] VALUES (?,?,?,?,?)";
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (transaction.getBuyDate()!= null) {
                Timestamp timestamp = Timestamp.valueOf(transaction.getBuyDate());
                ps.setTimestamp(1, timestamp);
            }
            ps.setInt(2, transaction.getBuyerID());
            ps.setInt(3, transaction.getSellerID());
            ps.setDouble(4, transaction.getPrice());
            ps.setInt(5, transaction.getArtworkID());
            ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkvalidTransaction(int BuyerID, int ArtworkID) {
        String sql = "SELECT COUNT(*) FROM [Transaction] WHERE BuyerID = ? AND ArtworkID =?";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, BuyerID);
            ps.setInt(2, ArtworkID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count == 0) return false;
            else return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
