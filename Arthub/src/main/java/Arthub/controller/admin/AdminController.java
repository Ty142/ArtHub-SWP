package Arthub.controller.admin;


import Arthub.dto.ActivityDTO;
import Arthub.dto.ArtistFormDTO;
import Arthub.dto.CreatorDTO;
import Arthub.dto.RankDTO;
import Arthub.entity.*;
import Arthub.repository.RankRepository;
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

    @Autowired
    RankService rankService;

    @Autowired
    ArtistFormService artistFormService;

    @Autowired
    RankRepository rankRepository;

    @Autowired
    WithdrawService withdrawService;

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
    //----------------------------------------------------------------

    @GetMapping("/GetListUserForAdmin")
    public List<CreatorDTO> getListUserForAdmin() throws Exception {
        return accountService.getAccountToAdmin();
    }

    @PutMapping("/AcceptToUpgrade/")
    public void acceptToUpgrade(@RequestBody ArtistFormDTO dto) throws Exception {
        if (dto.getRankID() == 1) {
            int rankID = rankService.getTheNewRankID(dto);
            rankService.AcceptUpgradeArtist(rankID);
        }
        else {
            rankService.AcceptUpgradeArtist(dto.getRankID());
        }
        artistFormService.AcceptArtistForm(dto.getFormId());
    }

    @DeleteMapping("/RefuseToUpgrade/{ArtistFormID}")
    public void refuseToUpgrade(@PathVariable("ArtistFormID") Long ID) throws Exception {
        artistFormService.RejectArtistForm(ID);
    }

    @GetMapping("/GetListArtistForm")
    public List<ArtistFormDTO> getAllArtistForms() throws Exception{
        return artistFormService.getArtistFormsUpgrade();
    }

    @GetMapping("/GetListWithdrawInProgress")
    public List<Withdraw> getAllWithdrawInProgress() throws Exception {
        return withdrawService.ListWithdrawInProgress();
    }

    @GetMapping("/GetListWithdrawBeAccept")
    public List<Withdraw> getAllWithdrawAccept() throws Exception {
        return withdrawService.ListWithdrawAccept();
    }

    @PutMapping("/AcceptWithdraw/{id}")
    public void setAcceptWithdraw(@PathVariable("id") int id){
        withdrawService.AcceptWithdraw(id);
    }
}
