<%-- 
    Document   : StoreSelection
    Created on : Nov 25, 2017, 12:54:25 AM
    Author     : kmne6
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Selection</title>
    </head>
    <body>
        <h1>Select Store for Inventory:</h1>
        
        <p>
            User: ${user.userid} - ${user.username}, ${user.adminlevel} Level
        </p>
        <form action="" id="selection" method="post">
            Stores:<br>
            <select id="storeid" name="storeid">
                <c:forEach var="st" items="${stores}">
                    <option 
                        ${st.storeid == user.storeid ? 'Selected' : ''}
                        value="${st.storeid}">${st.storename}
                    </option>
                </c:forEach>
            </select>
            <input type="submit" value="View/Edit Inventory">
        </form>
        
        ${msg}
    </body>
</html>
