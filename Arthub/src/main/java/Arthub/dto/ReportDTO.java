package Arthub.dto;

public class ReportDTO {
    private int reportId;
    private int reporterId;
    private int reportedId;
    private int artworkId;
    private String description;
    private String createdDate;
    private int status;

    public ReportDTO(int reportId, int reporterId, int reportedId, int artworkId, String description, String createdDate, int status) {
        this.reportId = reportId;
        this.reporterId = reporterId;
        this.reportedId = reportedId;
        this.artworkId = artworkId;
        this.description = description;
        this.createdDate = createdDate;
        this.status = status;
    }

    public ReportDTO() {}

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

    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
