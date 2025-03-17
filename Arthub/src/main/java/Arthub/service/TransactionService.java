package Arthub.service;

import Arthub.dto.TransactionDTO;
import Arthub.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();
    List<Transaction> getListTransactionsByBuyerID(int buyerId);

    List<Transaction> getListTransactionsBySellerID(int sellerId);

    void addTransaction(TransactionDTO transactionDTO);

    boolean checkTransaction(int buyerId, int artworkId);



}
