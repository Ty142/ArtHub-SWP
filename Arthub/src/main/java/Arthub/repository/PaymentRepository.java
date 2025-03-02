package Arthub.repository;

import Arthub.entity.Payment;

public interface PaymentRepository {
    boolean save(Payment payment);
}
