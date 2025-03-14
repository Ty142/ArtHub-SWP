package Arthub.repository.impl;

import Arthub.entity.Transaction;
import Arthub.repository.TransactionRepository;
import Arthub.repository.UserRepository;
import Arthub.service.Impl.RankServiceImpl;
import Arthub.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RankService rankService;
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
        int TypeRankID = rankService.getTypeOfRankIDByUserID(SellerID);
        Map<Integer,Double> coinMap = new HashMap<Integer,Double>();
        coinMap.put(1, 0.8);
        coinMap.put(2, 0.85);
        coinMap.put(3, 0.90);
        coinMap.put(4, 0.95);
        coinMap.put(5, 0.95);
        double coinType = coinMap.get(TypeRankID);
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
                transaction.setPrice(rs.getDouble("Price")*coinType);
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
    public Integer saveTransaction(Transaction transaction) {
        String sql = "INSERT INTO [Transaction] VALUES (?,?,?,?,?)";
        int generated = -1;
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (transaction.getBuyDate()!= null) {
                Timestamp timestamp = Timestamp.valueOf(transaction.getBuyDate());
                ps.setTimestamp(1, timestamp);
            }
            ps.setInt(2, transaction.getBuyerID());
            ps.setInt(3, transaction.getSellerID());
            ps.setDouble(4, transaction.getPrice());
            ps.setInt(5, transaction.getArtworkID());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generated = rs.getInt(1);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return generated;
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

    @Override
    public void addCoinsToSeller(int SellerID,int TransactionID,int TypeRankID) {
        String sql = "Update [User] set Coins = ? where UserID = ?";
        double coin = userRepository.getCoinsAmountByUserID(SellerID);
        double getCoin = getCoinsByTransactionID(TransactionID);
        Map<Integer,Double> coinMap = new HashMap<Integer,Double>();
        coinMap.put(1, 0.8);
        coinMap.put(2, 0.85);
        coinMap.put(3, 0.90);
        coinMap.put(4, 0.95);
        coinMap.put(5, 0.95);
        double coinType = coinMap.get(TypeRankID);
        double coinToSellerReceived = getCoin*coinType;
        coinToSellerReceived = Math.round(coinToSellerReceived * 100.0) / 100.0;
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, coinToSellerReceived+coin);
            ps.setInt(2, SellerID);
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
    public void removeCoinsFromBuyer(int BuyerID, int TransactionID) {
        String sql = "Update [User] set Coins = ? where UserID = ?";
        double coin = userRepository.getCoinsAmountByUserID(BuyerID);
        coin -= getCoinsByTransactionID(TransactionID);
        coin = Math.round(coin * 100.0) / 100.0;
        try {
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, coin);
            ps.setInt(2,BuyerID);
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
    public double getCoinsByTransactionID(int TransactionID) {
        String sql = "SELECT Price FROM [Transaction] WHERE TransactionID =?";
        try{
            utils.ConnectUtils db = utils.ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, TransactionID);

            ResultSet rs = ps.executeQuery();
            rs.next();
            double price = rs.getDouble(1);
            return price;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
