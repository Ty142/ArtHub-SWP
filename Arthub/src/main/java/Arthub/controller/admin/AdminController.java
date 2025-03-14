package Arthub.controller.admin;


import Arthub.dto.ActivityDTO;
import Arthub.entity.Payment;
import Arthub.entity.Transaction;
import Arthub.repository.ArtworkRepository;
import Arthub.repository.UserRepository;
import Arthub.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
