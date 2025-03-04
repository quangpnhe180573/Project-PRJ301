/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author admin1711
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        Cookie arr[] = request.getCookies();
        HttpSession session = request.getSession();

        if (arr != null || arr.length != 0) {
            for (Cookie c : arr) {
                if (c.getName().equals("userC")) {
                    request.setAttribute("username", c.getValue());
                }
                if (c.getName().equals("passC")) {
                    request.setAttribute("password", c.getValue());
                }
            }
        }
        request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String remember = request.getParameter("remember");
        out.print(remember);
        HttpSession session = request.getSession();

        if (user == null || pass == null || user.isEmpty() || pass.isEmpty()) {
            // Chuyển tiếp đến trang đăng nhập mà không có thông báo lỗi
            response.sendRedirect("Login.jsp");
            return;
        }

        AccountDAO dao = new AccountDAO();
        Account a = dao.login(user, pass);
        if (a.getActive() == 0) {
            String err = "Account has banned";
            request.setAttribute("err", err);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        if (a == null) {
            String err = "Wrong username or Password";
            request.setAttribute("err", err);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            if (remember != null) {
                //set cookie
                Cookie u = new Cookie("userC", user);
                Cookie p = new Cookie("passC", pass);

                //set max time
                u.setMaxAge(2000);
                p.setMaxAge(2000);

                response.addCookie(u);
                response.addCookie(p);
            } else {
                Cookie u = new Cookie("userC", user);
                u.setMaxAge(60);
            }
            session.setAttribute("acc", a);
            response.sendRedirect("home");
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
