<%-- 
    Document   : Login
    Created on : May 21, 2016, 6:04:10 PM
    Author     : Max
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Log In Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body style="background-image:url(http://almaqtari.net/wp-content/uploads/2014/04/galaxy-background-twitter.jpg)">
        <!-- HEADER PART -->
        <div id="darkRedAlphaShadows">
            <h1>AMaze-ing</h1>
        </div>
        
        <!-- BODY PART -->
        <div id="redCenteredAlphaShadows">
            <h3 align="center">Log in</h3>
            <form action="LoginServlet" method="post">
                <table border="0">
                    <tr align="center">
                        <th align="left">Name</td>
                        <th><input id="input" type="text" name="username" /></td> 
                    </tr>
                    <tr>
                        <th align="left">Password&nbsp;&nbsp;</td>
                        <th><input id="input" type="password" name="password" ></td> 
                    </tr>
                    <tr>
                        <th></th>
                        <th><input id="button" type="submit" value="log in">
                            <button id="button" onclick="window.location:'Register.jsp'">register</button>
                        </th>
                    </tr>
                </table>
            </form>
            <% if (request.getAttribute("error") != null
            && (Boolean) request.getAttribute("error")) {%>
                <div style="color:white;">Wrong username/password. Please try again</div>
            <% }%>
        </div>
        
        <!-- FOOTER PART -->
        <div id="footer">
            Copyright © Michael & Max
        </div>
    </body>
</html>
