package Arthub.service;

import Arthub.entity.Payment;

import java.util.List;

public interface PaymentService {
    boolean savePayment(Payment payment);
    List<Payment> getPaymentsByUserId(int UserId);
    boolean checkIfPaymentTransCodeExists(String transCode);

    List<Payment> getAllPayments();
}
