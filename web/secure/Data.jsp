<%-- 
    Document   : Data
    Created on : May 21, 2016, 6:39:51 PM
    Author     : Max
--%>

<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Private data</title>
    </head>
    <body>
        <div>
            <div>Users:</div>
            <%List<User> list = (ArrayList) session.getAttribute("list");
                for (User r : list) {%>
                <div><%= r.getUsername()%></div>
                <div><%= r.getProfile()%></div>
            <%}%>
        </div>
    </body>
</html>
