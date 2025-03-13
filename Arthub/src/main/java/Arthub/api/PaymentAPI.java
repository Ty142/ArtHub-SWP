package Arthub.api;

import Arthub.entity.Payment;
import Arthub.service.PaymentService;
import Arthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/Payment")
public class PaymentAPI {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Boolean> savePayment(@RequestBody Payment paymentDTO) {
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

    @GetMapping("/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUserId(@PathVariable String userId) {
        List<Payment> payments = paymentService.getPaymentsByUserId(Integer.parseInt(userId));

        if (payments.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());

        } else {
            return ResponseEntity.ok(payments);
        }
    }

    @GetMapping("/checktranscode/{code}")
    public ResponseEntity<Boolean> checkIfPaymentTransCodeExists(@PathVariable String code) {
        boolean checkTrans = paymentService.checkIfPaymentTransCodeExists(code);
        if (checkTrans) {
            return ResponseEntity.ok(true);

        } else {
            return ResponseEntity.ok(false);

        }
    }
}
