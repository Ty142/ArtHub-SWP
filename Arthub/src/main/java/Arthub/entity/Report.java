package Arthub.entity;

import java.time.LocalDateTime;

public class Report {
    private int reportId;
    private int reporterId;
    private int reportedId;
    private Integer artworkId;
    private String description;
    private LocalDateTime createdDate;
    private int status;

    public Report() {
        this.createdDate = LocalDateTime.now(); // 🟢 Lấy thời gian hiện tại đúng cách
    }

    public Report(int reportId, int reporterId, int reportedId, Integer artworkId, String description, LocalDateTime createdDate, int status) {
        this.reportId = reportId;
        this.reporterId = reporterId;
        this.reportedId = reportedId;
        this.artworkId = artworkId;
        this.description = description;
        this.createdDate = createdDate;
        this.status = status;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public int getReportedId() {
        return reportedId;
    }

    public void setReportedId(int reportedId) {
        this.reportedId = reportedId;
    }

    public Integer getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(Integer artworkId) {
        this.artworkId = artworkId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
