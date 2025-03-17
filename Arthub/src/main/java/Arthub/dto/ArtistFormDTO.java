package Arthub.dto;

import java.util.Date;

public class ArtistFormDTO {

    private int formId;
    private String descriptions;
    private Integer status;
    private Date dateCreation;
    private int userId;
    private int RankID;

    public ArtistFormDTO() {
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

    public int getRankID() {
        return RankID;
    }

    public void setRankID(int rankID) {
        RankID = rankID;
    }
}

