package Arthub.entity;

import java.util.Date;

public class Commission {
    private int commissionID;
    private int requestor;
    private int receiver;
    private String phoneNumber;
    private String email;
    private String description;
    private Boolean accept;
    private Date creationDate;
    private Date acceptanceDate;
    private Date completionDate;
    private int progress;
    private String message;

    public Commission() {
        this.creationDate = new Date();
        this.progress = 0;
    }

    public int getCommissionID() { return commissionID; }
    public void setCommissionID(int commissionID) { this.commissionID = commissionID; }

    public int getRequestor() { return requestor; }
    public void setRequestor(int requestor) { this.requestor = requestor; }

    public int getReceiver() { return receiver; }
    public void setReceiver(int receiver) { this.receiver = receiver; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getAccept() { return accept; }
    public void setAccept(Boolean accept) { this.accept = accept; }

    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Date getAcceptanceDate() { return acceptanceDate; }
    public void setAcceptanceDate(Date acceptanceDate) { this.acceptanceDate = acceptanceDate; }

    public Date getCompletionDate() { return completionDate; }
    public void setCompletionDate(Date completionDate) { this.completionDate = completionDate; }

    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
