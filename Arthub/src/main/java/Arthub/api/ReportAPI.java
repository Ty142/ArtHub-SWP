package Arthub.api;

import Arthub.entity.Report;
import Arthub.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/Report")
public class ReportAPI {
    @Autowired
    ReportService reportService;

    @GetMapping
    public ResponseEntity<ArrayList<Report>> getAllReports() {
        System.out.println("📥 Nhận yêu cầu lấy tất cả Reports");
        ArrayList<Report> reports = reportService.getAllReports();
        if (reports.isEmpty()) {
            System.out.println("⚠️ Không tìm thấy reports!");
            return ResponseEntity.noContent().build();

        }
        System.out.println("✅ Trả về " + reports.size() + " artworks.");
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/Form")
    public ResponseEntity<String> addReport(@RequestBody Report report) {
        reportService.addReport(report);
        return ResponseEntity.noContent().build();
    }



}
