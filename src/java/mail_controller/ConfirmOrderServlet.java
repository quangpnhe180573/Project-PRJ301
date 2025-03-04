/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mail_controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;
import model.Account;
import model.Cart;
import model.OrderDetail;
import model.OrderInfo;

/**
 *
 * @author admin1711
 */
public class ConfirmOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet ConfirmOrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmOrderServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart1");
        Account a = (Account) session.getAttribute("acc1");
        OrderInfo o = (OrderInfo) session.getAttribute("orderinfor");
        List<OrderDetail> list = o.getOrderDetail();

        // Initialize total amount
        double totalAmount = 0.0;

        // Create email content
        String content = "<h1>Order Confirmation</h1>"
                + "<p>Thank you, " + a.getName() + "!</p>"
                + "<h2>Order Details:</h2>"
                + "<table border='1' style='width: 100%; border-collapse: collapse;'>"
                + "<tr><th>Product Name</th><th>Quantity</th><th>Price</th></tr>";

        for (OrderDetail detail : list) {
            double price = detail.getPrice();
            int quantity = detail.getQuantity();
            totalAmount += price * quantity;

            content += "<tr>"
                    + "<td>" + detail.getPname() + "</td>"
                    + "<td>" + quantity + "</td>"
                    + "<td>" + price + "</td>"
                    + "</tr>";
        }

        // Calculate VAT and shipping
        double vat = totalAmount * 0.10; // 10% VAT
        DecimalFormat format = new DecimalFormat("#.00");
        String vat_f = format.format(vat);
        double shippingFee = 5.0; // Shipping fee
        double final_total = totalAmount + vat + shippingFee;
        String ftotal = format.format(final_total);

        // Add VAT and total amounts to the email content
        content += "</table>"
                + "<p>VAT (10%): $" + vat_f + "</p>"
                + "<p>Shipping Fee: $" + shippingFee + "</p>"
                + "<h3>Total Amount: $" + ftotal + "</h3>"
                + "<p>We will notify you once your order is shipped.</p>";

        sendConfirmOrder sendEmail = new sendConfirmOrder();
        sendEmail.sendEmail(a.getEmail(), "Order Details", content);
        session.setAttribute("acc1", null);
        session.setAttribute("size", 0);
        session.setAttribute("cart", null);
        response.sendRedirect("home");
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
