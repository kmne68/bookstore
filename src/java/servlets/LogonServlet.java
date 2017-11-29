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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kmne6
 */
@WebServlet(name = "LogonServlet", urlPatterns = {"/Logon"})
public class LogonServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String URL = "/StoreSelection.jsp";
        String sql = "";
        String authString = "";
        String msg = "";
        int userid = 0;
        int passattempt;
        ArrayList<Store> stores = new ArrayList<>();

        User user;

        // final version builds and authenticates per club logon        
/*        user = new User();
        user.setUserid(1234);
        user.setUsername("John Smith");
        user.setStoreid(2);
        user.setAdminlevel("Admn");
         */
        //     if (user.isAuthenticated()) {
        try {
            userid = Integer.parseInt(request.getParameter("userid").trim());
            passattempt = Integer.parseInt(request.getParameter("password"));
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();

            Statement s = conn.createStatement();
            sql = "SELECT * FROM stores ORDER BY StoreName ";
            authString = "SELECT * FROM users WHERE userID = '" + userid + "';";

            ResultSet r = s.executeQuery(authString);

            if (r.next()) {

                user = new User();
                user.setUserid(userid);
                user.setPassword(r.getInt("userPassword"));
                user.setPwdattempt(passattempt);
                user.setUsername(r.getString("userName"));
                user.setStoreid(r.getInt("storeID"));
                user.setAdminlevel(r.getString("adminLevel"));
                

                if (user.isAuthenticated()) {
                    
                    ResultSet rs = s.executeQuery(sql);

                    while (rs.next()) {
                        Store st = new Store();
                        st.setStoreid(rs.getInt("StoreID"));
                        st.setStorename(rs.getString("StoreName"));
                        st.setStoreaddr(rs.getString("StoreAddr"));
                        st.setStoreemp(rs.getInt("StoreEmp"));
                        stores.add(st);
                    }

                    if (stores.size() > 0) {
                        request.getSession().setAttribute("stores", stores);
                    } else {
                        msg = "No stores read from Stores table.<br>";
                    }

                    URL = "/StoreSelection.jsp";
                    msg = "User authenticated.";

                } else {
                    msg = "Unable to authenticate.";
                }

                request.getSession().setAttribute("user", user);

                Cookie uid = new Cookie("userid", Integer.toString(userid));
                uid.setMaxAge(60 * 10);
                uid.setPath("/"); // make cookie available to every page on root
                response.addCookie(uid);
            }
        } catch (SQLException e) {
            msg = "SQL exception " + e + "<br>";

        } catch (NumberFormatException e) {
            msg = "Illegal password<br>";

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
