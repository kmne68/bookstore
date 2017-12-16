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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
        ArrayList inv;
        int idata = 0; // inventory data
        ArrayList newinv;
        Inventory inventory;
        String bookcd = "";
        int onhand = 0;

        int bookqty = 0;
        User user;
        Store store;
        String bookcode = "";
        String teststring = "";
        String storeid = "";

//        Store newStore = new Store();
        try {
            String path = getServletContext().getRealPath("/WEB-INF/") + "\\";

            ConnectionPool pool = ConnectionPool.getInstance();

            // A new inventory object to hold current details
            inventory = new Inventory();

            // store = (Store) request.getSession().getAttribute("store");
            // newinv = new ArrayList();
            // get the inventory object that is on the session
            // inv = (ArrayList) request.getSession().getAttribute("inventory");
            // fill the new inventory array list with existing inventory values
            // newinv = inv;
            String action = request.getParameter("actiontype");

            bookcode = (String) request.getSession().getAttribute("bookcode");

            // Obtain the new quantity 
            try {
                if (action.equalsIgnoreCase("updateqty")) {
                    bookqty = Integer.parseInt(request.getParameter("updateqty"));
                    inventory.setQuantity(bookqty);
                    // SQL update statement                
                }
            } catch (Exception e) {
                msg += "Unable to obtain new quantity.<br>";
            }

            // implement cancel button
            try {
                if (action.equalsIgnoreCase("cancel")) {
                    URL = "/ViewInventory.jsp";
                }
            } catch (Exception e) {
                msg += "Unable to return to inventory screen, error is: " + e + "<br>";
            }
            
            // get inventory on hand for the book with the target book code at store 
            // with the target store ID.
            if (msg.isEmpty()) {

                Connection conn = pool.getConnection();
                Statement s = conn.createStatement();

                sql = "SELECT OnHand "
                        + "FROM bookinv "
                        + "WHERE bookinv.bookID = " + bookcode + " and bookinv.storeID = "
                        + storeid;

                ResultSet rs = s.executeQuery(sql);

                // store current inventory in case the update fails
                onhand = ((Integer) rs.getObject(1)).intValue();
                inventory.setQuantity(onhand);
            }

            // upadate the inventory with the new book quantity
            try {
                if (msg.isEmpty()) {
                    Connection conn = pool.getConnection();

                    sql = "UPDATE bookinv SET onhand = ?"
                            + "WHERE storeID = ? "
                            + " AND bookID = ? ";

                    PreparedStatement ps = conn.prepareStatement(sql);

                    ps.setInt(1, inventory.getQuantity());
                    ps.setInt(2, inventory.getStoreid());
                    ps.setString(3, inventory.getBookcd());

                    int rc = ps.executeUpdate();
                    if (rc == 0) {
                        msg = "Update failed, no records were changed<br>";
                    } else if (rc == 1) {
                        msg += "Inventory has been updated";
                        request.getSession().setAttribute("inventory", inventory);
                    } else {
                        msg += "Unexpected update of " + rc + "records.<br>";
                    }

                }
            } catch (Exception e) {
                msg += "Update failed; " + e;
            }

            //  request.setAttribute("store", store);
            //  storename = store.getStorename();
            //  request.setAttribute("storename", storename);
            //          try{
            //            inv = (ArrayList) request.getAttribute("inventory");
            //            msg += "inventory array list worked <br>";
            //          } catch (Exception e) {
            //              msg += "inventory failed to load " + e + "<br>";
            //          }      
            //        inventory.setBookcd();
            //        teststring =  (String) inv.get(2);
            msg += "teststring = " + teststring;

            //        bookcd = request.getParameter("bookcd");
            msg += "Book code = " + bookcd;

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
