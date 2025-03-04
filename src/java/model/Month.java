/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin1711
 */
public class Month {

    private int month;
    private int numOfOrders;
    private double totalRevenue;

    public Month(int month, int numOfOrders, double totalRevenue) {
        this.month = month;
        this.numOfOrders = numOfOrders;
        this.totalRevenue = totalRevenue;
    }

    public Month(int month, double totalRevenue) {
        this.month = month;
        this.totalRevenue = totalRevenue;
    }

    public Month(int month, int numOfOrders) {
        this.month = month;
        this.numOfOrders = numOfOrders;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNumOfOrders() {
        return numOfOrders;
    }

    public void setNumOfOrders(int numOfOrders) {
        this.numOfOrders = numOfOrders;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    @Override
    public String toString() {
        return "Month{" + "month=" + month + ", numOfOrders=" + numOfOrders + ", totalRevenue=" + totalRevenue + '}';
    }

}
