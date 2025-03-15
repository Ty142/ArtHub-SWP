package Arthub.repository;

import Arthub.entity.Payment;

import java.util.List;

public interface PaymentRepository {
    boolean save(Payment payment);
    List<Payment> getPaymentsByUserId(int userId);
    boolean checkIfPaymentTransCodeExists(String transCode);

    List<Payment> getAllPayments();
}
