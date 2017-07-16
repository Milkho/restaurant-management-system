package model;

import java.io.Serializable;

public class Ordersaccount implements Serializable {

    private Integer amount;
    private Integer dishID;
    private Integer orderID;

    public Ordersaccount() {

    }

    public Ordersaccount(Integer orderID, Integer dishID, Integer amount) {
        this.orderID = orderID;
        this.dishID = dishID;
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        Integer hash = 5;
        hash = 79 * hash + this.amount;
        hash = 79 * hash + this.dishID;
        hash = 79 * hash + this.orderID;
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
        final Ordersaccount other = (Ordersaccount) obj;
        if (this.amount != other.amount) {
            return false;
        }
        if (this.dishID != other.dishID) {
            return false;
        }
        if (this.orderID != other.orderID) {
            return false;
        }
        return true;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDishID() {
        return dishID;
    }

    public void setDishID(Integer dishID) {
        this.dishID = dishID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

}
