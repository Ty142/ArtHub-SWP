package Arthub.repository;

import Arthub.entity.Report;

import java.util.ArrayList;

public interface ReportRepository {
    void addReport(Report report);
    ArrayList<Report> getAllReports();
}
