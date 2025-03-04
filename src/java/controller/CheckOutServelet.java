/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.OrdersDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import model.Account;
import model.Cart;
import model.Item;
import model.OrderInfo;

/**
 *
 * @author admin1711
 */
public class CheckOutServelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckOutServelet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServelet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            String err = "Please add products to cart to check out!!";
            request.setAttribute("err", err);
            request.getRequestDispatcher("Cart.jsp").forward(request, response);

            return;
        }
        Cart cart1 = cart;

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        AccountDAO daoA = new AccountDAO();
        OrdersDAO daoO = new OrdersDAO();
        ProductDAO daoP = new ProductDAO();

        for (Item item : cart.getItems()) {
            int currentStock = daoP.getStock(item.getProduct().getId());
            if (item.getQuantity() > currentStock) {
                request.setAttribute("err", "Exceed remain stock");
                request.getRequestDispatcher("Cart.jsp").forward(request, response);
                return;
            }
        }

        Account account = (Account) session.getAttribute("acc");
        if (account == null) {
            daoA.insert(name, phone, address, email);
            account = daoA.getLastAccount();
        }

        if (account != null) {

            try {
                daoO.addOrder(account, cart);
                for (Item item : cart.getItems()) {
                    daoP.updateQuantity(item.getQuantity(), item.getProduct().getId());
                }
                List<OrderInfo> list = daoO.createOrderInfor();
                int id = daoO.getLastOrderID();
                OrderInfo oi = new OrderInfo();
                for (OrderInfo o : list) {
                    if (o.getOrders().getId() == id) {
                        oi = o;
                        break;
                    }
                }
                session.setAttribute("orderinfor", oi);
                session.setAttribute("cart1", cart1);
                session.setAttribute("acc1", account);
                request.getRequestDispatcher("confirmorder").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("err", "Exceed remain stock of product");
                request.getRequestDispatcher("Cart.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("err", "Không thể tạo tài khoản. Vui lòng thử lại.");
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
