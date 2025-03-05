package Arthub.api;

import Arthub.entity.Commission;
import Arthub.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Commission")
public class CommissionAPI {
    @Autowired
    private CommissionService commissionService;

    @PostMapping("/request")
    public ResponseEntity<Boolean> createCommission(@RequestBody Commission commission) {
        boolean success = commissionService.saveCommission(commission);
        if (success) {
            System.out.println("Yêu cầu commission đã được lưu thành công!");
            return ResponseEntity.ok(true);
        } else {
            System.out.println("Lỗi khi lưu commission.");
            return ResponseEntity.ok(false);
        }
    }
}
