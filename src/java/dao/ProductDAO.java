/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author admin1711
 */
public class ProductDAO extends DBContext {

    public List<Product> getListByPage(List<Product> list, int start, int end) {
        List<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Product> getRelatedProduct(String id) {
        String sql = "select * from Products  where price between (select price from Products where id = ?) - 10 "
                + "and (select price from Products where id = ?) + 10 and id != ?";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, id);
            ps.setString(3, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public String getSrcImg(int id) {
        String sql = "select image from Products where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> getAll() {
        String sql = "select * from Products where active != 0";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getAProductByID(String id) {
        String sql = "select * from Products where id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Product getAProductByID(int id) {
        String sql = "select * from Products where id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Product getHighestStockProduct() {
        try {
            String sql = "select * from Products where stock = (select max(stock) from Products)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Product> getByCid(String cid) {
        String sql = "select * from products where cateid =" + "'" + cid + "'";
        List<Product> list = new ArrayList<>();

        try {
            PreparedStatement p = connection.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> getBySearch(String txt) {
        List<Product> list = new ArrayList<>();
        String sql = "Select * from Products where name like " + "'%" + txt + "%' and active != 0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //price 
    public List<Product> getByPrice(Double price1, Double price2, int cid, int bid, int order) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE 1=1 and active != 0";

        if (cid != 0) {
            sql += " AND cateid = ?";
        }
        if (bid != 0) {
            sql += " And brandid = ?";
        }
        if (price1 != 0) {
            sql += " AND price >= ?";
        }
        if (price2 != 0) {
            sql += " AND price <= ?";
        }
        if (order == 1) {
            sql += " order by price";
        }
        if (order == 2) {
            sql += " order by price desc";
        }

        try (PreparedStatement p = connection.prepareStatement(sql.toString())) {
            int index = 1;

            if (cid != 0) {
                p.setInt(index++, cid);
            }
            if (bid != 0) {
                p.setInt(index++, bid);
            }
            if (price1 != 0) {
                p.setDouble(index++, price1);
            }
            if (price2 != 0) {
                p.setDouble(index++, price2);
            }

            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int maxID() {
        String sql = "select max(id) from Products ";
        int id = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    // them 1 san pham
    public void add(String name, String image, String title, String description, double price, int stock, int cateid, int bid) {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getAll();
        int id = dao.maxID() + 1;
        String sql = "insert into Products values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, image);
            ps.setDouble(4, price);
            ps.setString(5, title);
            ps.setString(6, description);
            ps.setInt(7, stock);
            ps.setInt(8, cateid);
            ps.setInt(9, bid);
            ps.setInt(10, 1);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //loc boi bran id
    public List<Product> getProductByBrandID(String bid) {
        String sql = "select * from Products where brandID = '" + bid + " '";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Loc ca bid va cid
    public List<Product> getProductByBCID(String bid, String cid) {
        String sql = "select * from Products where brandID = '" + bid + " ' and cateID = '" + cid + "'";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int countTotalProduct() {
        ProductDAO dao = new ProductDAO();
        int count = dao.getAll().size();
        return count;
    }

    public List<Product> paging(int index, int page) {
        String sql = "SELECT * \n"
                + "FROM Products \n"
                + "ORDER BY name \n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT ? ROWS ONLY;";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, page * (index - 1) + 1);
            ps.setInt(2, page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void update(Product p) {
        String sql = "update Products set name = ?, image = ?, price = ?, title = ?, description = ?, stock= ?, cateID = ?, brandID = ? where id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getImage());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getTitle());
            ps.setString(5, p.getDescription());
            ps.setInt(6, p.getStock());
            ps.setInt(7, p.getcID());
            ps.setInt(8, p.getbID());
            ps.setInt(9, p.getId());
            int update = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateQuantity(int num, int id) {
        String sql = "update Products set stock = stock - ? where id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, num);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String id) {
        String sql = "Update  Products set active = 0 where id =?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            int del = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Product> listOfBestSeller() {
        List<Product> list = new ArrayList<>();
        String sql = "select top 3 o1.pid,count(*) from Orders o "
                + "join OrderDetail o1 on o.id = o1.oid join Products p "
                + "on o1.pid = p.id where active != 0 group by o1.pid order by count(*) desc ";
        ProductDAO dao = new ProductDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                Product p = dao.getAProductByID(id);
                list.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getStock(int id) {
        String sql = "select stock from Products where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.listOfBestSeller();
        System.out.println(list.get(1));
    }

}
