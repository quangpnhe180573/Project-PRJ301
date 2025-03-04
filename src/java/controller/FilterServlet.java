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
import java.util.List;
import model.Brand;
import model.Category;
import model.Product;

/**
 *
 * @author admin1711
 */
public class FilterServlet extends HttpServlet {

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
            out.println("<title>Servlet FilterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        String cid_raw = request.getParameter("cid");
        String bid_raw = request.getParameter("bid");
        ProductDAO daoP = new ProductDAO();

        CategoryDAO daoC = new CategoryDAO();
        List<Category> listC = daoC.getAll();
        Product p = daoP.getHighestStockProduct();
        BrandDAO daoB = new BrandDAO();
        List<Brand> listB = daoB.getAll();

        String toprice_raw = request.getParameter("toprice");
        String fromprice_raw = request.getParameter("fromprice");
        String fconfirm = request.getParameter("fconfirm"); //tag3
        String order_raw = request.getParameter("order");

        double price1 = 0, price2 = Double.MAX_VALUE; // Đặt giá trị mặc định cho price2

        try {
            int cid = (cid_raw == null) ? 0 : Integer.parseInt(cid_raw); //tag1
            int bid = (bid_raw == null) ? 0 : Integer.parseInt(bid_raw); //tag2
            int order =(order_raw == null) ? 0 : Integer.parseInt(order_raw); //tag4

            price1 = (fromprice_raw == null) ? 0 : Double.parseDouble(fromprice_raw);
            price2 = (toprice_raw == null) ? 0 : Double.parseDouble(toprice_raw);

            // Lấy danh sách sản phẩm theo giá và cid
            List<Product> list1 = daoP.getByPrice(price1/1.1, price2/1.1, cid, bid,order);
//            if (listP.size() != 0) {
//                out.print("yes");
//                request.setAttribute("list", listP);
//            } else {
//                List<Product> listAll = daoP.getAll();
//                request.setAttribute("list", listAll);
//            }

            int page, numperpage = 9;
            int size = list1.size();

            String xpage = request.getParameter("page");
            out.print(xpage);

            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            int endpage = (size % 9 == 0) ? (size / 9) : (size / 9) + 1;

            start = (page - 1) * numperpage;
            end = Math.min(page * numperpage, size);

            List<Product> list = daoP.getListByPage(list1, start, end);
            
            if(list.size() == 0){
                out.println("ys");
            } else{
                out.println("No");
            }

            
            request.setAttribute("order", order);
            request.setAttribute("tag3",fconfirm);
            request.setAttribute("page", page);
            request.setAttribute("endPage", endpage);
            request.setAttribute("list", list);
            request.setAttribute("tag", cid_raw);
            request.setAttribute("tag2", bid_raw);
            request.setAttribute("p", p);
            request.setAttribute("listC", listC);
            request.setAttribute("listB", listB);
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        } catch (NumberFormatException e) {

            e.printStackTrace(); // Hoặc sử dụng Logger
        }
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
        processRequest(request, response);
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
