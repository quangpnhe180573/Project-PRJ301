/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author admin1711
 */
public class Orders {

    private int id;
    private String date;
    private String accId;
    private double totalMoney;

    public Orders(int id, String date, String accId, double totalMoney) {
        this.id = id;
        this.date = date;
        this.accId = accId;
        this.totalMoney = totalMoney;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Orders other = (Orders) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalMoney) != Double.doubleToLongBits(other.totalMoney)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return Objects.equals(this.accId, other.accId);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccId() {
        return accId;
    }

    @Override
    public String toString() {
        return "Orders{" + "id=" + id + ", date=" + date + ", accId=" + accId + ", totalMoney=" + totalMoney + '}';
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getTotalMoney2() {
        return (totalMoney - 5)/1.1;
    }

    public Orders() {
    }

}
