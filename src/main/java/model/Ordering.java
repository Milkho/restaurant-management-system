package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Ordering implements Serializable {

    private Integer orderID;
    private Integer tableID;
    private Integer employeeID;
    private Date date;
    
    public Ordering() {

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.orderID);
        hash = 59 * hash + Objects.hashCode(this.tableID);
        hash = 59 * hash + Objects.hashCode(this.employeeID);
        hash = 59 * hash + Objects.hashCode(this.date);
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
        final Ordering other = (Ordering) obj;
        if (!Objects.equals(this.orderID, other.orderID)) {
            return false;
        }
        if (!Objects.equals(this.tableID, other.tableID)) {
            return false;
        }
        if (!Objects.equals(this.employeeID, other.employeeID)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    public Ordering(Integer orderID, Integer tableID, Integer employeeID) {
        this.orderID = orderID;
        this.tableID = tableID;
        this.employeeID = employeeID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID = tableID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
