package Arthub.repository;

import Arthub.entity.Report;

import java.util.ArrayList;

public interface ReportRepository {
    void addReport(Report report);
    ArrayList<Report> getAllReportsFinish();
    ArrayList<Report> getAllReportsInProgress();

    void LockAccount(int AccountID);
    void UnlockAccount(int AccountID);

    void changTheStatusToCompletedReport(int ReportID);

    void ClearArtworkIDByReport(int ArtworkID);

    void changeStatusReportAfterDelete(int ArtworkID);

    ArrayList<Report> getAllReports();


}
