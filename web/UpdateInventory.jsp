<%-- 
    Document   : Update Inventory
    Created on : Nov 14, 2017, 9:14:32 PM
    Author     : kmne6
--%>

<%@page import="business.Store"%>
<%@page import="business.Inventory"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Inventory</title>
    </head>
    <body>
        <h1>User: ${user.userid} - ${user.username}, ${user.adminlevel} Level</h1>
        <br>
        <p>Branch #: <em>${user.storeid}</em></p>
        <p>Branch Name: <em>${store.storename}</em></p>
        <p>Branch Location: <em>${store.storeaddr}</em></p>
        <br>
        
        <p>Test Book Code: ${bookcode}</p>
        
        

        <p>Book Code: ${inv.bookcd}</p>
        <p>Book Tide: ${inv.booktitle}</p>
        <p>Author: ${inv.author}</p>

        <h2>Inventory on hand in branch: </h2>
        <form action="InventoryUpdate" name="inventory" id="inventory" method="post">
        <input type="text" name="bookqty" id="bookqty" size="15">
        <input type="button" name="update" id="update" value="Update Inventory" onclick="pageAction('update')">
        
        
        <!-- get attribute -->
        <input type="button" name="cancel" value="Cancel" onclick="pageAction('cancel')">
        </form>
        
        <br>
        <!-- experimental code -->
                <table border="1">
            <tr>
                <th>Store</th>
                <th>Book Cd</th>
                <th>Quantity</th>
                <th>Title</th>
                <th>Retail Price</th>
            </tr>
            <c:forEach var="inv" items="${inv}">
                <tr>
                    <td align="left">${inv.storeid}</td>
                    <td align="left">${inv.bookcd}</td>
                    <td align="left">${inv.quantity}</td>
                    <td align="left">${inv.title}</td>
                    <td align="left">${inv.price}</td>
                </tr>
            </c:forEach>
        </table>
        <!-- End experimental code -->
        
        
        <br>
        <p>${msg}</p>
        <br>
        <a href="ViewInventory.jsp">Back to Inventory</a>

    </body>
</html>
