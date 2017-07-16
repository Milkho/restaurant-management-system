package model;

import java.io.Serializable;
import java.util.Objects;

public class Dish implements Serializable {

    private Integer dishID;

    private String dishName;

    private Double netWeight;

    private Double price;


    public Dish() {
    }

    public Dish(Integer dishID, String dishName, Double netWeight, Double price) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.netWeight = netWeight;
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.dishID);
        hash = 47 * hash + Objects.hashCode(this.dishName);
        hash = 47 * hash + Objects.hashCode(this.netWeight);
        hash = 47 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dish other = (Dish) obj;
        if (!Objects.equals(this.dishName, other.dishName)) {
            return false;
        }
        if (!Objects.equals(this.dishID, other.dishID)) {
            return false;
        }
        if (!Objects.equals(this.netWeight, other.netWeight)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

  


    public Integer getDishID() {
        return dishID;
    }

    public void setDishID(Integer dishID) {
        this.dishID = dishID;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
