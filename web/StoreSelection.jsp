<%-- 
    Document   : StoreSelection
    Created on : Nov 25, 2017, 12:54:25 AM
    Author     : kmne6
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <script src="ajax.js" type="text/javascript"></script>
    <script src="inventory.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript">
        function ajaxfunction()
        {
           // document.selection.actiontype.value = action;
            if(ajax) {
                // Prof. Daniel used sid where I use storeID
                var storeID = document.getElementById("storeid").value;
                ajax.open('get', 'ViewInventory?storeid=' + encodeURIComponent(storeID));
                ajax.send(null);
                
            } else {
                document.selection.submit();
            }
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Selection</title>
    </head>
    
        <!-- send them back if they aren't authenticated -->
    <c:if test="${!m.authenticated}">
        <script type="text/javascript">
            window.location = "/WebHenryBooks";
        </script>
    </c:if>
    
    <body>
        <h1>Select Store for Inventory:</h1>
        
        <p>
            User: ${user.userid} - ${user.username}, ${user.adminlevel} Level
        </p>
        <form action="ViewInventory" id="selection" method="post">
            Stores:<br>
            <select id="storeid" name="storeid">
                <c:forEach var="st" items="${stores}">
                    <option 
                        ${st.storeid == user.storeid ? 'Selected' : ''}
                        value="${st.storeid}">${st.storename}
                    </option>
                </c:forEach>
            </select>
            <input type="button" value="Select" onclick="ajaxfunction()"> <!-- send store selection in background -->
        </form>
        <br>
        <div id="results"></div>
        
        ${msg}
    </body>
</html>
