package Arthub.service.Impl;

import Arthub.entity.Commission;
import Arthub.repository.CommissionRepository;
import Arthub.service.CommissionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {

    private final CommissionRepository commissionRepository;

    public CommissionServiceImpl(CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
    }

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
}