package Arthub.api;

import Arthub.entity.Commission;
import Arthub.service.CommissionService;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/commissions")
public class CommissionAPI {

    private final CommissionService commissionService;

    // Constructor injection
    public CommissionAPI(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @GetMapping
    public List<Commission> getAllCommissions() {
        return commissionService.getAllCommissions();
    }

    @PutMapping("/{commissionId}/accept")
    public void updateCommissionAccept(
            @PathVariable int commissionId,
            @RequestParam boolean accept,
            @RequestParam(required = false) String message,
            @RequestParam(required = false) String acceptanceDate) {
        Timestamp finalAcceptanceDate = null;
        if (accept && acceptanceDate == null) {
            // Tạo timestamp hiện tại với cả ngày và giờ
            finalAcceptanceDate = new Timestamp(System.currentTimeMillis());
        } else if (acceptanceDate != null) {
            try {
                // Chuyển đổi chuỗi acceptanceDate (nếu có) thành Timestamp
                finalAcceptanceDate = Timestamp.valueOf(acceptanceDate);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid date format for acceptanceDate. Expected yyyy-MM-dd HH:mm:ss, got: " + acceptanceDate, e);
            }
        }
        commissionService.updateCommissionAccept(commissionId, accept, message != null ? message : "", finalAcceptanceDate); // Truyền Timestamp trực tiếp
    }

    @PutMapping("/{commissionId}/progress")
    public void updateCommissionProgress(
            @PathVariable int commissionId,
            @RequestParam int progress) {
        if (progress == 4) { // Giả sử progress = 4 tương ứng với "Commission Completed!"
            commissionService.updateCommissionProgress(commissionId, progress, new Timestamp(System.currentTimeMillis()));
        } else {
            commissionService.updateCommissionProgress(commissionId, progress, null);
        }
    }
}