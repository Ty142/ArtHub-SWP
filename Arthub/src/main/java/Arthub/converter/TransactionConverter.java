package Arthub.converter;

import Arthub.dto.TransactionDTO;
import Arthub.entity.Transaction;

public class TransactionConverter {

    public Transaction ConvertTransactionDTOToTransaction(TransactionDTO transactionDTO){
        Transaction transaction = new Transaction();
        transaction.setTransactionID(transactionDTO.getTransactionID());
        transaction.setPrice(transactionDTO.getPrice());
        transaction.setArtworkID(transactionDTO.getArtworkID());
        transaction.setSellerID(transactionDTO.getSellerID());
        transaction.setBuyerID(transactionDTO.getBuyerID());
        transaction.setBuyDate(transactionDTO.getBuyDate());
        return transaction;
    }
}
