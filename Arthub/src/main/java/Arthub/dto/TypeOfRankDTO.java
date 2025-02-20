package Arthub.dto;

public class TypeOfRankDTO {
    private int typeId;
    private String typeRankName;
    private double price;

    public TypeOfRankDTO(int typeId, String typeRankName, double price) {
        this.typeId = typeId;
        this.typeRankName = typeRankName;
        this.price = price;
    }

    public TypeOfRankDTO(){
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeRankName() {
        return typeRankName;
    }

    public void setTypeRankName(String typeRankName) {
        this.typeRankName = typeRankName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
