package Arthub.service.Impl;

import Arthub.entity.Payment;
import Arthub.repository.PaymentRepository;
import Arthub.service.PaymentService;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
            if (rootCause instanceof SQLServerException) {
                SQLServerException sqlException = (SQLServerException) rootCause;
                if (sqlException.getMessage().contains("Violation of UNIQUE KEY constraint")) {
                    System.out.println("Lỗi: Duplicate TransCode, không thể thêm bản ghi.");
                    return false;  // ❌ Duplicate key, trả về false
                }
            }
            System.out.println("Lỗi SQL khác: " + e.getMessage());
            return false;  // ❌ Trả về false nếu có lỗi khác
        }
    }


}
