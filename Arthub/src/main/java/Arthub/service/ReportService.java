package Arthub.service;

import Arthub.entity.Report;

import java.util.ArrayList;

public interface ReportService {
    void addReport(Report report);
    ArrayList<Report> getAllReportsFinish();
    ArrayList<Report> getAllReportsInProgress();

    void LockAccount(int AccountID);
    void UnlockAccount(int AccountID);

    void changeStatusCompletedProcess(int reportID);
    void changeStatusCompleted(int ArtworkID);

    ArrayList<Report> GetAllReports();
}
