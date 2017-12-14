/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.ConnectionPool;
import business.Inventory;
import business.Store;
import business.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kmne6
 */
@WebServlet(name = "InventoryUpdateServlet", urlPatterns = {"/InventoryUpdate"})
public class InventoryUpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String sql = "";
        String msg = "test from inventoryupdateservlet";
        String URL = "/UpdateInventory.jsp";
        String bookcd = "";
        int bookqty = 0;
        int idata = 0; // inventory data
        User user;
        Store store;
        ArrayList inventory;
        String storename = "";
        String bookcode = "";
        
        System.out.println("TEST");

        try {
            String path = getServletContext().getRealPath("/WEB-INF/") + "\\";
            
            store = (Store) request.getAttribute("store");
          //  request.setAttribute("store", store);
          //  storename = store.getStorename();
          //  request.setAttribute("storename", storename);
     //       inventory = (ArrayList) request.getAttribute("inventory");

            
    //        bookcd = request.getParameter("bookcd");
            msg += "Book code = " + bookcd;
            
            String action = request.getParameter("actiontype");

            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();

//             not sure this is an appropriate place for this:
//            if (action.equalsIgnoreCase("edit")) {
//                URL = "UpdateInventory.jsp";
//                msg += "TEST TEST TEST";
//                try {
//                    // setAttribute
//
//                request.setAttribute("msg", msg);
//                } catch (Exception e) {
//
//                }
//            }

            // Update quantity 
//            if(action.equalsIgnoreCase("updateqty")) {
//                bookqty = Integer.parseInt(request.getParameter("updateqty"));
//                inventory.setQuantity(bookqty);
//                // SQL update statement
//                
//            }
            
        } catch (Exception e) {
            msg = "An exception occurred in the InventoryUpdateServlet.<br>";
        }

        try {
            idata = Integer.parseInt(request.getParameter("onhand"));
        } catch (Exception e) {

        }

        request.getSession().setAttribute("msg", msg);

        RequestDispatcher disp = getServletContext().getRequestDispatcher(URL);
        disp.forward(request, response);

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
