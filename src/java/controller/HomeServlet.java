/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Brand;
import model.Cart;
import model.Category;
import model.Product;

/**
 *
 * @author admin1711
 */
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o; // Cast từ 'o' thành 'cart'
        } else {
            cart = new Cart(); // Tạo giỏ hàng mới nếu không tồn tại
        }
        ProductDAO daoP = new ProductDAO();
        CategoryDAO daoC = new CategoryDAO();
        BrandDAO daoB = new BrandDAO();
        List<Brand> listB = daoB.getAll();
        List<Product> list1 = daoP.getAll();
        List<Category> listC = daoC.getAll();
        List<Product> listSeller = daoP.listOfBestSeller();

        Product p = daoP.getHighestStockProduct();

        //phan trang
        int page, numperpage = 9;
        int size = list1.size();

        String xpage = request.getParameter("page");

        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        int endpage = (size % 9 == 0) ? (size / 9) : (size / 9) + 1;
        out.println(endpage);
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);

        List<Product> list = daoP.getListByPage(list1, start, end);

        request.setAttribute("page", page);
        request.setAttribute("endPage", endpage);

        request.setAttribute("p", p);
        session.setAttribute("listBestSeller", listSeller);
        session.setAttribute("cart", cart);
        session.setAttribute("listB", listB);
        request.setAttribute("list", list);
        session.setAttribute("listC", listC);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
        doGet(request, response);
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
