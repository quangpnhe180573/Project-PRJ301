/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin_controller;

import dao.OrdersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author admin1711
 */
public class DashBoardServlet extends HttpServlet {

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
            out.println("<title>Servlet DashBoardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashBoardServlet at " + request.getContextPath() + "</h1>");
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
        OrdersDAO daoO = new OrdersDAO();
        int num1 = daoO.getTotalOrderPerMonth(1);
        int num2 = daoO.getTotalOrderPerMonth(2);
        int num3 = daoO.getTotalOrderPerMonth(3);
        int num4 = daoO.getTotalOrderPerMonth(4);
        int num5 = daoO.getTotalOrderPerMonth(5);
        int num6 = daoO.getTotalOrderPerMonth(6);
        int num7 = daoO.getTotalOrderPerMonth(7);
        int num8 = daoO.getTotalOrderPerMonth(8);
        int num9 = daoO.getTotalOrderPerMonth(9);
        int num10 = daoO.getTotalOrderPerMonth(10);
        int num11 = daoO.getTotalOrderPerMonth(11);
        int num12 = daoO.getTotalOrderPerMonth(12);

        double rev1 = daoO.getRevenuePerMonth(1);
        double rev2 = daoO.getRevenuePerMonth(2);
        double rev3 = daoO.getRevenuePerMonth(3);
        double rev4 = daoO.getRevenuePerMonth(4);
        double rev5 = daoO.getRevenuePerMonth(5);
        double rev6 = daoO.getRevenuePerMonth(6);
        double rev7 = daoO.getRevenuePerMonth(7);
        double rev8 = daoO.getRevenuePerMonth(8);
        double rev9 = daoO.getRevenuePerMonth(9);
        double rev10 = daoO.getRevenuePerMonth(10);
        double rev11 = daoO.getRevenuePerMonth(11);
        double rev12 = daoO.getRevenuePerMonth(12);

        int totalOrder = daoO.getTotalOrderPerYear();
        double totalRevenue = daoO.getRevenuePerYear();
        double totalCost = daoO.getCostProduct();

        double orderPerMonth = totalOrder / 12;
        double revenuePerMonth = totalRevenue / 12;
        double profit = totalRevenue - totalCost;

        request.setAttribute("m1", num1);
        request.setAttribute("m2", num2);
        request.setAttribute("m3", num3);
        request.setAttribute("m4", num4);
        request.setAttribute("m5", num5);
        request.setAttribute("m6", num6);
        request.setAttribute("m7", num7);
        request.setAttribute("m8", num8);
        request.setAttribute("m9", num9);
        request.setAttribute("m10", num10);
        request.setAttribute("m11", num11);
        request.setAttribute("m12", num12);

        request.setAttribute("rev1", rev1);
        request.setAttribute("rev2", rev2);
        request.setAttribute("rev3", rev3);
        request.setAttribute("rev4", rev4);
        request.setAttribute("rev5", rev5);
        request.setAttribute("rev6", rev6);
        request.setAttribute("rev7", rev7);
        request.setAttribute("rev8", rev8);
        request.setAttribute("rev9", rev9);
        request.setAttribute("rev10", rev10);
        request.setAttribute("rev11", rev11);
        request.setAttribute("rev12", rev12);

        request.setAttribute("totalOrder", totalOrder);
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("totalCost", totalCost);
        request.setAttribute("orderPerMonth", orderPerMonth);
        request.setAttribute("revenuePerMonth", revenuePerMonth);
        request.setAttribute("profit", profit);
        request.setAttribute("mF", daoO.getHighestMonthOrders());
        request.setAttribute("mS", daoO.getHighestMonthRevenue());

        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        OrdersDAO daoO = new OrdersDAO();
        PrintWriter out = response.getWriter();
        int num1 = daoO.getTotalOrderPerMonth(1);
        int num2 = daoO.getTotalOrderPerMonth(2);
        int num3 = daoO.getTotalOrderPerMonth(3);
        int num4 = daoO.getTotalOrderPerMonth(4);
        int num5 = daoO.getTotalOrderPerMonth(5);
        int num6 = daoO.getTotalOrderPerMonth(6);
        int num7 = daoO.getTotalOrderPerMonth(7);
        int num8 = daoO.getTotalOrderPerMonth(8);
        int num9 = daoO.getTotalOrderPerMonth(9);
        int num10 = daoO.getTotalOrderPerMonth(10);
        int num11 = daoO.getTotalOrderPerMonth(11);
        int num12 = daoO.getTotalOrderPerMonth(12);

        double rev1 = daoO.getRevenuePerMonth(1);
        double rev2 = daoO.getRevenuePerMonth(2);
        double rev3 = daoO.getRevenuePerMonth(3);
        double rev4 = daoO.getRevenuePerMonth(4);
        double rev5 = daoO.getRevenuePerMonth(5);
        double rev6 = daoO.getRevenuePerMonth(6);
        double rev7 = daoO.getRevenuePerMonth(7);
        double rev8 = daoO.getRevenuePerMonth(8);
        double rev9 = daoO.getRevenuePerMonth(9);
        double rev10 = daoO.getRevenuePerMonth(10);
        double rev11 = daoO.getRevenuePerMonth(11);
        double rev12 = daoO.getRevenuePerMonth(12);

        int totalOrder = daoO.getTotalOrderPerYear();
        double totalRevenue = daoO.getRevenuePerYear();
        double totalCost = daoO.getCostProduct();

        double orderPerMonth = (double) totalOrder / 12;
        double revenuePerMonth = totalRevenue / 12;
        double profit = totalRevenue - totalCost;

        request.setAttribute("m1", num1);
        request.setAttribute("m2", num2);
        request.setAttribute("m3", num3);
        request.setAttribute("m4", num4);
        request.setAttribute("m5", num5);
        request.setAttribute("m6", num6);
        request.setAttribute("m7", num7);
        request.setAttribute("m8", num8);
        request.setAttribute("m9", num9);
        request.setAttribute("m10", num10);
        request.setAttribute("m11", num11);
        request.setAttribute("m12", num12);

        request.setAttribute("rev1", rev1);
        request.setAttribute("rev2", rev2);
        request.setAttribute("rev3", rev3);
        request.setAttribute("rev4", rev4);
        request.setAttribute("rev5", rev5);
        request.setAttribute("rev6", rev6);
        request.setAttribute("rev7", rev7);
        request.setAttribute("rev8", rev8);
        request.setAttribute("rev9", rev9);
        request.setAttribute("rev10", rev10);
        request.setAttribute("rev11", rev11);
        request.setAttribute("rev12", rev12);

  

        request.setAttribute("totalOrder", totalOrder);
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("totalCost", totalCost);
        request.setAttribute("orderPerMonth", orderPerMonth);
        request.setAttribute("revenuePerMonth", revenuePerMonth);
        request.setAttribute("profit", profit);
        request.setAttribute("mF", daoO.getHighestMonthOrders());
        request.setAttribute("mS", daoO.getHighestMonthRevenue());

        request.getRequestDispatcher("index.jsp").forward(request, response);

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
