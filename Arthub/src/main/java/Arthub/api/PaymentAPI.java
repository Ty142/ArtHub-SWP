package Arthub.api;

import Arthub.entity.Payment;
import Arthub.service.PaymentService;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Payment")
public class PaymentAPI {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Boolean> toggleFavourite(@RequestBody Payment paymentDTO) {
        paymentDTO.setAmount(paymentDTO.getAmount()/25000);
        boolean checkTrans = paymentService.savePayment(paymentDTO);
        if (checkTrans) {
            userService.updateCoinsAmount(paymentDTO.getUserId(), paymentDTO.getAmount());
            System.out.println("Ổn");
            return ResponseEntity.ok(true);
        } else {
            System.out.println("Có vấn đề với Payment");
            return ResponseEntity.ok(false);
        }
    }
}
