package Arthub.api;

import Arthub.dto.TransactionDTO;
import Arthub.entity.Transaction;
import Arthub.service.TransactionService;
import com.cloudinary.http5.api.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/transaction")
public class TransactionAPI {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/ListAllTransaction")
    public List<Transaction> listAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/ListTransactionBuyer/{BuyerID}")
    public List<Transaction> listTransactionsByBuyerID(@PathVariable("BuyerID") int BuyerID) {
        return transactionService.getListTransactionsByBuyerID(BuyerID);
    }

    @GetMapping("/ListTransactionSeller/{SellerID}")
    public List<Transaction> listTransactionsBySellerID(@PathVariable("SellerID") int SellerID) {
        return transactionService.getListTransactionsBySellerID(SellerID);
    }

    @PostMapping("/SaveTransaction")
    public ResponseEntity<String> saveTransaction(@RequestBody TransactionDTO transactionDTO) {
        if (transactionDTO == null) {
            return ResponseEntity.badRequest().body("Transaction object can't be null");
        }
        transactionService.addTransaction(transactionDTO);
        return ResponseEntity.ok("Transaction saved successfully.");
    }

        @GetMapping("/TransactionsChecking/{BuyerID}/{ArtworkID}")
    public Boolean checkTransactions(@PathVariable("BuyerID") int BuyerID, @PathVariable("ArtworkID") int ArtworkID) {
        if (transactionService.checkTransaction(BuyerID, ArtworkID)){
            return true;
        }
        return false;
    }
}
