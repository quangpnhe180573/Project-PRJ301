/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import model.Product;

/**
 *
 * @author admin1711
 */
public class AccountDAO extends DBContext {
    
    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account where active != 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getString("id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("name"), rs.getString("phone"), rs.getString("address"), rs.getInt("role"),
                        rs.getInt("active"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Account> getAllNotNull() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account where active != 0 and username is not null";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getString("id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("name"), rs.getString("phone"), rs.getString("address"), rs.getInt("role"),
                        rs.getInt("active"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Account> getListByPage(List<Account> list, int start, int end) {
        List<Account> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    
    public Account login(String username, String pass) {
        String sql = "select * from Account where username =? and password =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String generateID() {
        String sql = "SELECT CAST(SUBSTRING(id, 3, LEN(id) - 2) AS INT) AS NumberPart FROM Account WHERE id LIKE 'KH%'";
        PreparedStatement ps;
        List<Integer> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("NumberPart"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String rs = "";
        int max = Collections.max(list) + 1;
        if (Collections.max(list) + 1 < 10) {
            rs += "0" + max;
        } else {
            rs += max;
        }
        return rs;
    }
    
    public void insert(String username, String password, String name, String phone, String address, String email) {
        String sql = "insert into Account values(?,?,?,?,?,?,?,?,?)";
        AccountDAO dao = new AccountDAO();
        List<Account> list = dao.getAll();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "KH" + dao.generateID());
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, name);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setInt(7, 0);
            ps.setInt(8, 1);
            ps.setString(9, email);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insert(String username, String password, String name, String phone, String address, int role, String email) {
        String sql = "insert into Account values(?,?,?,?,?,?,?,?,?)";
        AccountDAO dao = new AccountDAO();
        List<Account> list = dao.getAll();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "KH" + dao.generateID());
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, name);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setInt(7, role);
            ps.setInt(8, 1);
            ps.setString(9, email);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insert(String name, String phone, String address, String email) {
        String sql = "insert into Account values(?,?,?,?,?,?,?,?,?)";
        AccountDAO dao = new AccountDAO();
        List<Account> list = dao.getAll();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "KH" + dao.generateID());
            ps.setString(2, null);
            ps.setString(3, null);
            ps.setString(4, name);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setInt(7, 0);
            ps.setInt(8, 1);
            ps.setString(9, email);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Account getLastAccount() {
        String sql = "select top 1 *, CAST(SUBSTRING(id, 3, LEN(id) - 2) AS INT) AS NumberPart from Account  WHERE id LIKE 'KH%' order by NumberPart desc ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getString(9));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void update(String id, String name, String phone, String address, String pass, String email) {
        String sql = "update Account set name = ?, password = ?, phone = ?, address = ?, email = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(3, phone);
            ps.setString(2, pass);
            ps.setString(4, address);
            ps.setString(5, email);
            ps.setString(6, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(String id, String name, String phone, String address, String pass, String email, String role) {
        String sql = "update Account set name = ?, password = ?, email = ?, phone = ?, address = ?, role = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(3, email);
            ps.setString(2, pass);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, role);
            ps.setString(7, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getEmailByUsername(String username) {
        String sql = "select email from account where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getPassByUsername(String username) {
        String sql = "select password from account where username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Account getAccountByID(String id) {
        String sql = "select * from Account where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getString(9));
                return a;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Account> getAccountNotNull() {
        String sql = "select * from Account where username is not null";
        List<Account> list = new ArrayList<>();
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getString("id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("name"), rs.getString("phone"), rs.getString("address"), rs.getInt("role"),
                        rs.getInt("active"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void deleteAccountByID(String id) {
        String sql = "Update Account set active =0 where id  = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<String> getListEmail() {
        String sql = "select email from Account where username is not null";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.getListEmail().get(0));
    }
}
