/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author admin1711
 */
public class OrderInfo {
    private Orders orders;
    private List<OrderDetail> orderDetail;
    private String name, phone, address;

    public OrderInfo() {
    }




    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderInfo(Orders orders, List<OrderDetail> orderDetail, String name, String phone, String address) {
        this.orders = orders;
        this.orderDetail = orderDetail;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OrderInfo(Orders orders, List<OrderDetail> orderDetail, String name, String phone) {
        this.orders = orders;
        this.orderDetail = orderDetail;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "OrderInfo{" + "orders=" + orders + ", orderDetail=" + orderDetail + ", name=" + name + ", phone=" + phone + ", address=" + address + '}';
    }

    

   

 
    
    
}
