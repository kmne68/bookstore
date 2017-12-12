<%-- 
    Document   : Update Inventory
    Created on : Nov 14, 2017, 9:14:32 PM
    Author     : kmne6
--%>

<%@page import="business.Store"%>
<%@page import="business.Inventory"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Inventory</title>
    </head>
    <body>
        <h1>User: ${u.userid}} - ${user.username}, ${user.adminlevel} Level</h1>
        <br>
        <p>Branch #: <em>${user.storeid}</em></p>
        <p>Branch Name: <em>${store.storename}</em></p>
        <p>Branch Location: <em>${store.storeaddr}</em></p>
        <br>

        <p>Book Code: ${inv.bookcd}</p>
        <p>Book Tide: ${inv.booktitle}</p>
        <p>Author: ${inv.author}</p>

        <p>Inventory on hand in branch: 
        <input type="text" name="updateqty" id="updateqty" size="15">
        <input type="button" name="update" id="update" value="Update Inventory" onclick="pageAction('update')">
        </p>
        <!-- get attribute -->
        <input type="button" name="cancel" value="Cancel" onclick="pageAction('')">
        
        <br>
        <p>${msg}</p>
        <br>
        <a href=""MemberScreen.jsp">Back to Member Screen</a>

    </body>
</html>
