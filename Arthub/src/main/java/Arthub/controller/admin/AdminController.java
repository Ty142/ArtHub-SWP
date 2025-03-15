package Arthub.controller.admin;


import Arthub.dto.ActivityDTO;
import Arthub.dto.CreatorDTO;
import Arthub.dto.RankDTO;
import Arthub.entity.Payment;
import Arthub.entity.Report;
import Arthub.entity.Transaction;
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

    @Autowired
    RankService rankService;

    @GetMapping("/numberofuser")
    public Integer getNumberOfUser() {
        return  userService.getTheNumberOfUsers();
    }

    @GetMapping("/numberofartwork")
    public Integer getNumberOfArtwork() {
        return  artworkService.getTheNumberOfArtworks();
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

    //Report
    @GetMapping("/ListReportsFinished")
    public List<Report> getListOfReportsFinish() {
        return reportService.getAllReportsFinish();
    }

    @GetMapping("/ListReportsUnfinished")
    public List<Report> getListOfReportsInProgress() {
        return reportService.getAllReportsInProgress();
    }

    @PutMapping("/updateStatus/{ReportID}")
    public void updateStatusReport(@PathVariable("ReportID") int reportID)  {
        reportService.changeStatusCompletedProcess(reportID);
    }
    //----------------------------------------------------------------



    @DeleteMapping("/Delete/{ArtworkID}")
    public void deleteArtwork(@PathVariable("ArtworkID") int artworkID) throws Exception {
        artworkService.DeleteArtwork(artworkID);
        reportService.changeStatusCompleted(artworkID);
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

    @GetMapping("/GetListArtist")
    public List<RankDTO> getListArtist() throws Exception {
        return rankService.getListArtistUpgrade();
    }

    @PutMapping("/AcceptToUpgrade/{rankID}")
    public void acceptToUpgrade(@PathVariable("rankID") int rankID) throws Exception {
        rankService.AcceptUpgradeArtist(rankID);
    }

}
