package Arthub.repository;

import Arthub.entity.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> getTransactions();

    List<Transaction> getTransactionsByBuyerID(int BuyerID);

    List<Transaction> getTransactionsBySellerID(int SellerID);

    Integer saveTransaction(Transaction transaction);

    boolean checkvalidTransaction(int BuyerID, int ArtworkID);

    void addCoinsToSeller(int SellerID,int TransactionID, int TypeRankID);

    void removeCoinsFromBuyer(int BuyerID,int TransactionID);

    double getCoinsByTransactionID(int TransactionID);
}
