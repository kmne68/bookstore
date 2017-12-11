<%-- 
    Document   : Inventory
    Created on : Nov 28, 2017, 8:53:12 PM
    Author     : kmne6
--%>

<%@page import="business.Store"%>
<%@page import="business.Inventory"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Henry Books</title>
    </head>
    <body>
        <h1>User: ${user.userid} - ${user.username}, ${user.adminlevel} Level</h1>
        <br>
        <p>Branch #: <em>${user.storeid}</em></p>
        <p>Branch Name: <em>${store.storename}</em></p>
        <p>Branch Location: <em>${store.storeaddr}</em></p>
        <br>

        <c:if test="${user.adminlevel == 'Admn'}">       
            <input type="button" name="edit" id="edit" value="Edit" ><!--onclick="pageAction('edit')"-->
        </c:if>

            <br>
        <!--input type="button" name="edit" id="edit" value="Edit" onclick="pageAction('edit')"-->

        Message from servlet is: ${msg}

        <table border="1">
            <tr>
                <th>Store</th>
                <th>Book Cd</th>
                <th>Quantity</th>
                <th>Title</th>
                <th>Retail Price</th>
            </tr>
            <c:forEach var="inv" items="${inventory}">
                <tr>
                    <td align="left">${inv.storeid}</td>
                    <td align="left">${inv.bookcd}</td>
                    <td align="left">${inv.quantity}</td>
                    <td align="left">${inv.title}</td>
                    <td align="left">${inv.price}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
    </body>
</html>
