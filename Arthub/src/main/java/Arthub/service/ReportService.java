package Arthub.service;

import Arthub.entity.Report;

import java.util.ArrayList;

public interface ReportService {
    void addReport(Report report);
    ArrayList<Report> getAllReports();

    void LockAccount(int AccountID);
    void UnlockAccount(int AccountID);
}
