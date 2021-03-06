/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.ConnectionPool;
import business.Inventory;
import business.User;
import business.Store;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

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
        //    User user;
        Store store;
        // Inventory inv;
        String invsql = "";
        String bookcode = "";

        String action = request.getParameter("actiontype");
        try {
            // Not sure we need this:
            String path = getServletContext().getRealPath("/WEB-INF/") + "\\";

            storeid = Integer.parseInt(request.getParameter("storeid"));    // "storeid" is from the StoreSelection jsp
            msg = "Store " + storeid + " requests.";

            // set the bookcode whose inventory is to be updated
            bookcode = request.getParameter("updateqty");
            request.setAttribute("bookcode", bookcode);

            // next: new connection from pool 
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();

            // obtain store from stores table and build store object
            store = new Store();
            Statement s = conn.createStatement();
            sql = "SELECT storeID, storeName, storeAddr, storeEmp FROM stores where storeID = 2"; // + storeid + "' ";

            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                store.setStoreid(storeid);
                store.setStoreemp(r.getInt("storeEmp"));
                store.setStorename(r.getString("storeName"));
                store.setStoreaddr(r.getString("storeAddr"));
            }
            msg += "store address " + store.getStoreaddr();

            request.setAttribute("store", store);

            r.close();
            pool.freeConnection(conn);
            conn.close();

            // create inventory bean and arraylist of that object type filled by reading inventory table (sql command in spec) by reading book inventory
            // put items on the session so they're passed on
            // need: Store, Book Cd, Title, Retail Price, Quantity
            // TABLE bookinv has: bookID, storeID, OnHand
            // TABLE booklist has: bookID, title, author, publisher_Code, booktype, price
            ConnectionPool ipool = ConnectionPool.getInstance();
            Connection iconn = ipool.getConnection();
            Statement is = iconn.createStatement();
            invsql = "SELECT i.storeID, i.bookID, i.OnHand, l.title, l.price FROM bookinv i, booklist l  WHERE i.bookID = l.bookID AND storeID = " + storeid;

            /*        "SELECT i.storeID, i.bookID, i.OnHand, l.title, l.price FROM "
                    + "bookinv i, booklist l  WHERE i.bookID = l.bookID AND"
                    + " storeID = " + storeid; */
            ResultSet inventorySet = is.executeQuery(invsql);
            ArrayList<Inventory> inventory = new ArrayList<>();

            while (inventorySet.next()) {
                Inventory inv = new Inventory(
                        inventorySet.getInt("storeID"),
                        inventorySet.getString("bookID"),
                        inventorySet.getInt("OnHand"),
                        inventorySet.getString("title"),
                        inventorySet.getDouble("price")
                );
                inventory.add(inv);
            }

            inventorySet.last();
            msg = "Books in inventory: " + inventorySet.getRow() + ".<br>";

            request.setAttribute("inventory", inventory);
            inventorySet.close();
            ipool.freeConnection(iconn);
            iconn.close();

        } catch (Exception e) {
            msg = "Bad store number.<br>";
        }

        // set the bookcode whose inventory is to be updated, put it on the session
        try {
            if (action.equalsIgnoreCase("edit")) {
                bookcode = request.getParameter("bookcd");
                request.setAttribute("bookcode", bookcode);
            }
        } catch (Exception e) {
            msg += "Unable to set bookcode due to " + e;
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
