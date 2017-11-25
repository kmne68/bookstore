/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.ConnectionPool;
import business.Store;
import business.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kmne6
 */
public class LogonServlet extends HttpServlet {

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

        String URL = "/StoreSelection.jsp";
        String sql = "";
        String msg = "";        
        ArrayList<Store> stores = new ArrayList<>();

        User user;

        // final version builds and authenticates per club logon
        user = new User();
        user.setUserid(1234);
        user.setUsername("John Smith");
        user.setStoreid(2);
        user.setAdminlevel("Admn");
        
        request.getSession().setAttribute("user", user);

        if (user.isAuthenticated()) {

            try {
                ConnectionPool pool = ConnectionPool.getInstance();
                Connection conn = pool.getConnection();

                Statement s = conn.createStatement();

                //query = "SELECT userName FROM users WHERE userID = 1234";
                
                sql = "SELECT * FROM stores ORDER BY StoreName ";

                ResultSet r = s.executeQuery(sql);
                //System.out.println("User = " + r.getString("userName"));                
               
                while (r.next()) {                    
                    Store st = new Store();
                    st.setStoreid(r.getInt("StoreID"));
                    st.setStorename(r.getString("StoreName"));
                    st.setStoreaddr(r.getString("StoreAddr"));
                    st.setStoreemp(r.getInt("StoreEmp"));
                    stores.add(st);
                }
                if(stores.size() > 0) {
                    request.getSession().setAttribute("stores", stores);
                } else {
                    msg = "No stores read from Stores table.<br>";
                }
            } catch (SQLException e) {
                msg = "SQL exception " + e + "<br>";

            }

        }
        
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
