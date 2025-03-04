/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin1711
 */
public class OrderDetail {

    private int oid, pid;
    private int quantity;
    private double price;
    private String image, pname;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public OrderDetail(int oid, int pid, int quantity, double price, String image, String pname) {
        this.oid = oid;
        this.pid = pid;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.pname = pname;
    }

    public OrderDetail(int oid, int pid, int quantity, double price, String image) {
        this.oid = oid;
        this.pid = pid;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public OrderDetail() {
    }

    public OrderDetail(int oid, int pid, int quantity, double price) {
        this.oid = oid;
        this.pid = pid;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "oid=" + oid + ", pid=" + pid + ", quantity=" + quantity + ", price=" + price + ", image=" + image + ", pname=" + pname + '}';
    }

   

    public void setPrice(double price) {
        this.price = price;
    }

}
