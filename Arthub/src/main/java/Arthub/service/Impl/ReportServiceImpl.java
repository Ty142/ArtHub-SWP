package Arthub.service.Impl;

import Arthub.entity.Report;
import Arthub.repository.ReportRepository;
import Arthub.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Override
    public void addReport(Report report) {
        reportRepository .addReport(report);
    }

    @Override
    public ArrayList<Report> getAllReports() {
        return reportRepository.getAllReports();
    }

    @Override
    public void LockAccount(int AccountID) {
        reportRepository.LockAccount(AccountID);
    }

    @Override
    public void UnlockAccount(int AccountID) {
        reportRepository.UnlockAccount(AccountID);
    }
}
