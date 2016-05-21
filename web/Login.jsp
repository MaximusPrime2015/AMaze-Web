<%-- 
    Document   : Login
    Created on : May 21, 2016, 6:04:10 PM
    Author     : Max
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>My Form</title>
    </head>
    <body>
        <form action="LoginServlet" method="post">
            <%
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                out.println("<div>Current time: " + sdf.format(cal.getTime()) + "</div>");
            %>
            <div>Username:</div>
            <input type="text" name="username"/>
            <div>Password:</div>
            <input type="password" name="password"/>
            <div><input type="submit"/></div>
            <div><a href="Register.jsp">Register</a></div>
            <% if (request.getAttribute("error") != null
             && (Boolean) request.getAttribute("error")) {%>
            <div>Wrong username/password. Please try again</div>
            <% }%>
        </form>
    </body>
</html>
