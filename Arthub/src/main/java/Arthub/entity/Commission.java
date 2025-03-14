package Arthub.entity;

import java.util.Date;

public class Commission {
    private int commissionID;
    private Integer requestor;
    private Integer receiver;
    private String phoneNumber;
    private String email;
    private String description;
    private Boolean accept;
    private Date creationDate;
    private Date acceptanceDate;
    private Date completionDate;
    private Integer progress;
    private String message;
    private String artworkURL;

    public Commission() {
        this.creationDate = new Date();
        this.progress = 0;
    }

    public Commission(int commissionID, Integer requestor, Integer receiver, String phoneNumber, String email,
                      String description, Boolean accept, Date creationDate, Date acceptanceDate,
                      Date completionDate, Integer progress, String message, String artworkURL) {
        this.commissionID = commissionID;
        this.requestor = requestor;
        this.receiver = receiver;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.description = description;
        this.accept = accept;
        this.creationDate = creationDate;
        this.acceptanceDate = acceptanceDate;
        this.completionDate = completionDate;
        this.progress = progress;
        this.message = message;
        this.artworkURL = artworkURL;
    }

    public String getArtworkURL() {
        return artworkURL;
    }

    public void setArtworkURL(String artworkURL) {
        this.artworkURL = artworkURL;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCommissionID() {
        return commissionID;
    }

    public void setCommissionID(int commissionID) {
        this.commissionID = commissionID;
    }

    public Integer getRequestor() {
        return requestor;
    }

    public void setRequestor(Integer requestor) {
        this.requestor = requestor;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}