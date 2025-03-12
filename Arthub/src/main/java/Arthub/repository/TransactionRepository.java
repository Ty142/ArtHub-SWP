package Arthub.repository;

import Arthub.entity.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> getTransactions();

    List<Transaction> getTransactionsByBuyerID(int BuyerID);

    List<Transaction> getTransactionsBySellerID(int SellerID);

    void saveTransaction(Transaction transaction);

    boolean checkvalidTransaction(int BuyerID, int ArtworkID);
}
