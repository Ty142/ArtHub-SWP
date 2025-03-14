package Arthub.service;

import Arthub.entity.Commission;

import java.sql.Timestamp;
import java.util.List;

public interface CommissionService {
    List<Commission> getAllCommissions();
    void updateCommissionAccept(int commissionId, Boolean accept, String message, Timestamp acceptanceDate);
    void updateCommissionProgress(int commissionId, int progress, Timestamp completionDate, String artworkURL);
    boolean saveCommission(Commission commission);
    String getArtworkURL(int commissionId);
}