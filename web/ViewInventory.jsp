<%-- 
    Document   : Inventory
    Created on : Nov 28, 2017, 8:53:12 PM
    Author     : kmne6
--%>

<%@page import="business.Store"%>
<%@page import="java.util.ArrayList"%>
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
        <p>Book Cd: <input=></p>
        
        Message from servlet is: ${msg}
    </body>
</html>
