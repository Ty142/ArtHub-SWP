package Arthub.service.Impl;

import Arthub.entity.Commission;
import Arthub.repository.CommissionRepository;
import Arthub.service.CommissionService;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;

@Service
public class CommissionServiceImpl implements CommissionService {
    @Autowired
    public CommissionRepository commissionRepository;

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
