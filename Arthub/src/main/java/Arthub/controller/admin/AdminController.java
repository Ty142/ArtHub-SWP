package Arthub.controller.admin;


import Arthub.dto.ActivityDTO;
import Arthub.dto.CreatorDTO;
import Arthub.entity.Payment;
import Arthub.entity.Report;
import Arthub.entity.Transaction;
import Arthub.repository.ArtworkRepository;
import Arthub.repository.UserRepository;
import Arthub.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    ArtworkService artworkService;
    @Autowired
    UserService userService;

    @Autowired
    InteractService interactService;

    @Autowired
    TransactionService transactionService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    ReportService reportService;

    @Autowired
    AccountService accountService;

    @GetMapping("/numberofuser")
    public Integer getNumberOfUser() {
        return artworkService.getTheNumberOfArtworks();
    }

    @GetMapping("/numberofartwork")
    public Integer getNumberOfArtwork() {
        return userService.getTheNumberOfUsers();
    }

    @GetMapping("/ListActivity")
    public List<ActivityDTO> getListOfActivity() {
        return interactService.getListOfActivity();
    }

    @GetMapping("/ListAllTransactions")
    public List<Transaction> getListOfAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/ListPayment")
    public List<Payment> getListOfPayment() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/ListReports")
    public List<Report> getListOfReports() {
        return reportService.getAllReports();
    }

    @DeleteMapping("/Delete/{ArtworkID}")
    public void deleteArtwork(@PathVariable("ArtworkID") int artworkID) throws Exception {
        artworkService.DeleteArtwork(artworkID);
    }

    @PutMapping("/LockAccount/{AccountID}")
    public void lockAccount(@PathVariable("AccountID") int accountID) throws Exception {
        reportService.LockAccount(accountID);
    }

    @PutMapping("/UnlockAccount/{AccountID}")
    public void unlockAccount(@PathVariable("AccountID") int accountID) throws Exception {
        reportService.UnlockAccount(accountID);
    }

    @GetMapping("/GetListUserForAdmin")
    public List<CreatorDTO> getListUserForAdmin() throws Exception {
        return accountService.getAccountToAdmin();
    }


}
