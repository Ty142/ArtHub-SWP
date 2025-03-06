package Arthub.service.Impl;

import Arthub.entity.Commission;
import Arthub.repository.CommissionRepository;
import Arthub.service.CommissionService;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {
    @Autowired
    public CommissionRepository commissionRepository;


    @Override
    public List<Commission> getAllCommissions() {
        return commissionRepository.findAllCommissions();
    }

    @Override
    public void updateCommissionAccept(int commissionId, Boolean accept, String message, Timestamp acceptanceDate) {
        commissionRepository.updateCommissionAccept(commissionId, accept, message, acceptanceDate);
    }

    @Override
    public void updateCommissionProgress(int commissionId, int progress, Timestamp completionDate) {
        commissionRepository.updateCommissionProgress(commissionId, progress, completionDate);
    }
    @Override
    public boolean saveCommission(Commission commission) {
        try {
            return commissionRepository.save(commission);
        } catch (DataAccessException e) {
            Throwable rootCause = e.getRootCause();
            if (rootCause instanceof SQLServerException) {
                SQLServerException sqlException = (SQLServerException) rootCause;
                if (sqlException.getMessage().contains("Violation of UNIQUE KEY constraint")) {
                    System.out.println("Lỗi: Duplicate Commission ID, không thể thêm bản ghi.");
                    return false;
                }
            }
            System.out.println("Lỗi SQL khác: " + e.getMessage());
            return false;
        }
    }
}