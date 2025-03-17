package Arthub.entity;

import java.util.Date;

public class ArtistForm {
    private int formId;
    private String descriptions;
    private Integer status;
    private Date dateCreation;
    private int userId;

    public ArtistForm() {
    }

    public ArtistForm(int formId, String descriptions, Integer status, Date dateCreation, int userId) {
        this.formId = formId;
        this.descriptions = descriptions;
        this.status = status;
        this.dateCreation = dateCreation;
        this.userId = userId;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        if (status != null && status != 0 && status != 1) {
            throw new IllegalArgumentException("Status chỉ có thể là NULL, 0 hoặc 1");
        }
        this.status = status;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
