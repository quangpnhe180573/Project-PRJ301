/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.servlet.jsp.jstl.sql.Result;
import java.time.LocalDate;
import model.Account;
import model.Cart;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
import model.Month;
import model.OrderDetail;
import model.OrderInfo;
import model.Orders;

/**
 *
 * @author admin1711
 */
public class OrdersDAO extends DBContext {

    public void addOrder(Account a, Cart cart) {
        // Lấy ngày và giờ hiện tại
        LocalDateTime now = LocalDateTime.now();

        // Định dạng thành chuỗi phù hợp với SQL
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = now.format(formatter);

        try {

            // Chèn vào Orders
            String sql1 = "insert into Orders (date, cid, total_money) values (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setString(1, date);
            ps.setString(2, a.getId());
            ps.setDouble(3, cart.getTotalMoney() * 1.1 + 5);
            ps.executeUpdate();

            // Lấy ID đơn hàng vừa chèn
            String sql2 = "select top 1 id from Orders order by id desc"; // Sử dụng SCOPE_IDENTITY() để lấy ID vừa chèn
            PreparedStatement ps1 = connection.prepareStatement(sql2);
            ResultSet rs = ps1.executeQuery();

            int oid = 0; // Khởi tạo oid
            if (rs.next()) {
                oid = rs.getInt(1); // Lấy ID vừa chèn
            }

            // Chèn vào OrderDetail
            for (Item i : cart.getItems()) {
                String sql3 = "insert into OrderDetail (oid, pid, quantity, price, address) values (?, ?, ?, ?,?)";
                PreparedStatement ps3 = connection.prepareStatement(sql3);
                ps3.setInt(1, oid);
                ps3.setInt(2, i.getProduct().getId());
                ps3.setInt(3, i.getQuantity());
                ps3.setDouble(4, i.getPrice());
                ps3.setString(5, a.getAddress());
                ps3.executeUpdate();
            }

        } catch (SQLException e) {

        }
    }

    public int getTotalOrderPerMonth(int month) {
        String sql = "select count(*) from Orders where year(date) = 2024 and MONTH(date) = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                return num;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getTotalOrderPerYear() {
        String sql = "select count(*) from Orders where year(date) = 2024 ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                return num;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public double getRevenuePerMonth(int month) {
        String sql = "select sum(total_money) from Orders where year(date) = 2024 and MONTH(date) = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                double num = rs.getInt(1);
                return num;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public double getRevenuePerYear() {
        String sql = "select sum(total_money) from Orders where year(date) = 2024 ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                double num = rs.getInt(1);
                return num;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public double getCostProduct() {
        String sql = " select  sum(t1.Quantiy * t1.price) as Cost from (select t.pid, t.Quantiy, p.price from (select o.pid, sum(o.quantity) as Quantiy from OrderDetail o \n"
                + "  join Orders o1  on o.oid = o1.id \n"
                + "  group by o.pid) t join Products p on p.id = t.pid) t1";

        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                double total = rs.getDouble(1);
                return total;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Month getHighestMonthOrders() {
        String sql = "select  month(date) as [month], count(*) as [numOfOrders] from Orders group by MONTH(date) \n"
                + "having count(*) = (select max(t.numOfOrders)\n"
                + "from (select  count(*) as [numOfOrders] from Orders group by MONTH(date)) t)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Month m = new Month(rs.getInt(1), rs.getInt(2));
                return m;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Month getHighestMonthRevenue() {
        String sql = "select MONTH(date) as[month] ,sum(total_money) as [total] from Orders group by MONTH(date) \n"
                + "having sum(total_money) = (select max(t.total) as total from \n"
                + "(select MONTH(date) as[month] ,sum(total_money) as [total] \n"
                + "from Orders group by MONTH(date)) t)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Month m = new Month(rs.getInt(1), rs.getDouble(2));
                return m;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Integer> getListOrderID() {
        List<Integer> list = new ArrayList<>();
        String sql = "select id from Orders";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Orders getOrderById(int id) {
        String sql = "select * from Orders where id  = ?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders o = new Orders(id, rs.getString(2), rs.getString(3), rs.getDouble(4));
                return o;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<OrderInfo> createOrderInfor() {
        List<OrderInfo> list = new ArrayList<>();
        OrdersDAO dao = new OrdersDAO();
        ProductDAO daoP = new ProductDAO();
        List<Integer> listID = dao.getListOrderID();
        String phone = "";
        String name = "";
        String address = "";

        String sql = "select o1.*, a.phone, a.name from \n"
                + "Orders o  join OrderDetail o1 on o.id = o1.oid join Account a on a.id = o.cid where o.id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Integer i : listID) {
                List<OrderDetail> listOD = new ArrayList<>();
                ps.setInt(1, i);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    listOD.add(new OrderDetail(rs.getInt(1), rs.getInt(2),
                            rs.getInt(3), rs.getDouble(5), daoP.getSrcImg(rs.getInt(2)),
                            daoP.getAProductByID(rs.getInt(2)).getName()));
                    phone = rs.getString(6);
                    name = rs.getString(7);
                    address = rs.getString(4);
                }
                Orders o = dao.getOrderById(i);
                list.add(new OrderInfo(o, listOD, name, phone, address));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<OrderInfo> paging(List<OrderInfo> list, int start, int end) {
        List<OrderInfo> list1 = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }

    public List<OrderInfo> getHistoryOrder(String id) {
        OrdersDAO dao = new OrdersDAO();
        List<OrderInfo> list = dao.createOrderInfor();
        List<OrderInfo> list2 = new ArrayList<>();
        for (OrderInfo o : list) {
            if (o.getOrders().getAccId().equals(id)) {
                list2.add(o);
            }
        }
        return list2;
    }

    public List<Integer> getListID(String cid) {
        String sql = "SELECT id FROM Orders WHERE cid = ?";
        List<Integer> list = new ArrayList<>();

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "SQL error: " + ex.getMessage(), ex);
        }
        return list;
    }

    public List<OrderInfo> createOrderInforHistory(String cid) {
        List<OrderInfo> list = new ArrayList<>();
        OrdersDAO dao = new OrdersDAO();
        ProductDAO daoP = new ProductDAO();
        List<Integer> listID = dao.getListID(cid);
        String phone = "";
        String name = "";
        String address = "";

        String sql = "select o1.*, a.phone, a.name from \n"
                + "Orders o  join OrderDetail o1 on o.id = o1.oid join Account a on a.id = o.cid where o.id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Integer i : listID) {
                List<OrderDetail> listOD = new ArrayList<>();
                ps.setInt(1, i);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    listOD.add(new OrderDetail(rs.getInt(1), rs.getInt(2),
                            rs.getInt(3), rs.getDouble(5), daoP.getSrcImg(rs.getInt(2)),
                            daoP.getAProductByID(rs.getInt(2)).getName()));
                    phone = rs.getString(6);
                    name = rs.getString(7);
                    address = rs.getString(4);
                }
                Orders o = dao.getOrderById(i);
                list.add(new OrderInfo(o, listOD, name, phone, address));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getLastOrderID() {
        String sql = "select top 1 id from Orders order by id desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        OrdersDAO dao = new OrdersDAO();
        System.out.println(dao.createOrderInforHistory("KH94").size());
    }

}
