package Arthub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class TransactionDTO {
    @JsonProperty("transactionID")
    private int transactionID;
    @JsonProperty("price")
    private double price;
    @JsonProperty("buyDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime buyDate;
    @JsonProperty("sellerID")
    private int sellerID;
    @JsonProperty("buyerID")
    private int buyerID;
    @JsonProperty("artworkID")
    private int artworkID;

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDateTime buyDate) {
        this.buyDate = buyDate;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public int getArtworkID() {
        return artworkID;
    }

    public void setArtworkID(int artworkID) {
        this.artworkID = artworkID;
    }
}
