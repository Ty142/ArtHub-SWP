package Arthub.service.Impl;

import Arthub.entity.Payment;
import Arthub.repository.PaymentRepository;
import Arthub.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.dao.DataAccessException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    public PaymentRepository paymentRepository;

    @Override
    public boolean savePayment(Payment payment) {
        try {
            return paymentRepository.save(payment);
        } catch (DataAccessException e) {
            Throwable rootCause = e.getRootCause();
            if (rootCause instanceof SQLException) {
                SQLException sqlException = (SQLException) rootCause;
                if (sqlException.getMessage().contains("Violation of UNIQUE KEY constraint")) {
                    System.out.println("Lỗi: Duplicate TransCode, không thể thêm bản ghi.");
                    return false;  // ❌ Duplicate key, trả về false
                }
            }
            System.out.println("Lỗi SQL khác: " + e.getMessage());
            return false;  // ❌ Trả về false nếu có lỗi khác
        }
    }

    @Override
    public List<Payment> getPaymentsByUserId(int UserId) {
        return paymentRepository.getPaymentsByUserId(UserId);
    }

    @Override
    public boolean checkIfPaymentTransCodeExists(String transCode) {
        return paymentRepository.checkIfPaymentTransCodeExists(transCode);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }


}
