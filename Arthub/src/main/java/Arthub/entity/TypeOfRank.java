package Arthub.entity;

import Arthub.repository.impl.TypeOffRankRepositoryImpl;

public class TypeOfRank {
    private int typeId;
    private String typeRankName;
    private double price;

    public TypeOfRank(){

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

    @Override
    public String toString() {
        return "TypeOfRank{" +
                "typeId=" + typeId +
                ", typeRankName='" + typeRankName + '\'' +
                ", price=" + price +
                '}';
    }
}
