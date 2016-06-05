<%-- 
    /**
    * exe 3
    * @author Michael Vassernis 319582888 vaserm3
    * @author 
    */
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game Menu</title>
        <link rel="stylesheet" href="../styles.css">
        <script src="../scripts.js"></script>
    </head>
    <body style="background-image:url(http://i.imgur.com/nBp49hp.jpg);">
        <!-- HEADER PART -->
        <% String username = session.getAttribute("username").toString(); %>
        <% String name = session.getAttribute("name").toString(); %>
        <% String image = session.getAttribute("pic").toString(); %>
        <div id="darkRedAlphaShadows">
            <h1>AMaze-ing</h1>
        </div>
        <div id="darkRedAlphaShadows" style="position: absolute; right:20px;">
            <%=username%>&nbsp;-&nbsp;
            <%=name%>&nbsp;&nbsp;
            <image src="../images/<%=image%>.png"/>&nbsp;&nbsp;&nbsp;
            <a href="DisconnectServlet" class="button">Disconnect</a>
        </div>
        
        <!-- BODY PART -->
        <div id="redCenteredAlphaShadows">
            <br>
            <a class="button" href="Singleplayer">Singleplayer</a><br><br>
            <a class="button" href="Multiplayer">Multiplayer</a><br><br>
        </div>
        
        <!-- FOOTER PART -->
        <div id="footer">
            Copyright © Michael & Max
        </div>
    </body>
</html>
