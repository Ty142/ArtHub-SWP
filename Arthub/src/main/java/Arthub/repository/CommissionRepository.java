package Arthub.repository;

import Arthub.entity.Commission;

import java.sql.Timestamp;
import java.util.List;

public interface CommissionRepository {
    List<Commission> findAllCommissions();
    void updateCommissionAccept(int commissionId, Boolean accept, String message, Timestamp acceptanceDate);
    void updateCommissionProgress(int commissionId, int progress, Timestamp completionDate, String artworkURL);
    String getArtworkURL(int commissionId);
    boolean save(Commission commission);
    List<Commission> getCommissionsByRequestor(int requestorId);

}