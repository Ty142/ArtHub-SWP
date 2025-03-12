package Arthub.service.Impl;

import Arthub.converter.TransactionConverter;
import Arthub.dto.TransactionDTO;
import Arthub.entity.Transaction;
import Arthub.repository.TransactionRepository;
import Arthub.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    private TransactionConverter transactionConverter = new TransactionConverter();
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getTransactions();
    }

    @Override
    public List<Transaction> getListTransactionsByBuyerID(int buyerId) {
        return transactionRepository.getTransactionsByBuyerID(buyerId);
    }

    @Override
    public List<Transaction> getListTransactionsBySellerID(int sellerId) {
        return transactionRepository.getTransactionsBySellerID(sellerId);
    }

    @Override
    public void addTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionConverter.ConvertTransactionDTOToTransaction(transactionDTO);
        transactionRepository.saveTransaction(transaction);
    }

    @Override
    public boolean checkTransaction(int buyerId, int artworkId) {
        if (transactionRepository.checkvalidTransaction(buyerId, artworkId)) {
            return true;
        }
        return false;
    }
}
