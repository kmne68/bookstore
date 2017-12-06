/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.ConnectionPool;
import business.User;
import business.Store;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
@WebServlet(name = "ViewInventoryServlet", urlPatterns = {"/Inventory"})
public class ViewInventoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String URL = "/ViewInventory.jsp";
        int storeid = 0;
        String sql = "";
        String msg = "";
        User user;
        Store store;
        
        try {
            storeid = Integer.parseInt(request.getParameter("storeid"));    // "storeid" is from the StoreSelection jsp
            msg = "Store " + storeid + " requests.";
            

            
            // next: new connection from pool 
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
            
            // obtain store from stores table and build store object
            store = new Store();
        //    store.setStoreid(storeid);
            
            // create inventory bean and arraylist of that object type filled by reading inventory table (sql command in spec) by reading book inventory
            // put items on the session so they're passed on


/*
            Statement s = conn.createStatement();
            sql = "SELECT storeName, storeAddr FROM stores where storeID = " + storeid + "' ";

            ResultSet r = s.executeQuery(sql);
            store.setStorename(r.getString("storeName"));
            store.setStoreaddr(r.getString("storeAddr"));

            System.out.println("Store name = " + r.getString("storeName"));
*/        //    request.getSession().setAttribute("store", store);
            
        } catch (Exception e) {
            msg = "Bad store number.<br>";
        }

        request.setAttribute("msg", msg);
        
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
