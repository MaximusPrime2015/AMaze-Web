<%-- 
    Document   : Menu
    Created on : 22/05/2016, 23:02:14
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game Menu</title>
        <link rel="stylesheet" href="../styles.css">
    </head>
    <body style="background-image:url(http://www.hdwallpapery.com/static/images/background-wallpapers-26_fXb5f3k.jpg);background-size:cover;">
        <!-- HEADER PART -->
        <% String name = session.getAttribute("name").toString(); %>
        <% String image = session.getAttribute("pic").toString(); %>
        <div id="darkRedAlphaShadows">
            <h1>AMaze-ing</h1>
        </div>
        <div id="darkRedAlphaShadows" style="position: absolute; right:20px;">
                <%=name%>&nbsp;&nbsp;
                <image src="../images/<%=image%>.png"/>&nbsp;&nbsp;&nbsp;
                <a href="DisconnectServlet" id="button">Disconnect</a>
            </div>
        
        <!-- BODY PART -->
        <div id="redCenteredAlphaShadows">
            <br>
            <a id="button" href="Singleplayer">Singleplayer</a><br><br>
            <a id="button" href="Multiplayer">Multiplayer</a><br><br>
        </div>
        
        <!-- FOOTER PART -->
        <div id="footer">
            Copyright © Michael & Max
        </div>
    </body>
</html>
