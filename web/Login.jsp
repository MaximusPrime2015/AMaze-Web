<%-- 
    Document   : Login
    Created on : May 21, 2016, 6:04:10 PM
    Author     : Max & Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Log In Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body style="background-image:url(http://www.hdwallpapery.com/static/images/background-wallpapers-26_fXb5f3k.jpg);background-size:cover;">
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
                        <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name</td>
                        <th align="center"><input id="input" type="text" name="username" /></td> 
                    </tr>
                    <tr>
                        <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password&nbsp;&nbsp;</td>
                        <th align="center"><input id="input" type="password" name="password" ></td> 
                    </tr>
                    <tr>
                        <th></th>
                        <th><input id="button" type="submit" value="Log in">
                            <a id="button" href="Register.jsp">Register</a>
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
