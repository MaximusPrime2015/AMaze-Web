<%-- 
    Document   : Singleplayer
    Created on : May 23, 2016, 6:18:34 PM
    Author     : Max
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Single Player</title>
        <link rel="stylesheet" href="../styles.css">
        <script src="../mazeScripts.js"></script>
        <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-
a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
        <script>
            window.onkeydown = function(e) {
                var key = e.keyCode ? e.keyCode : e.which;
                if (key === 39)// right
                    movePlayer(0,1);
                else if (key === 37)// left
                    movePlayer(0,-1);
                else if (key === 38)// up
                    movePlayer(-1,0);
                else if (key === 40)// down
                    movePlayer(1,0);
            }
        </script>
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
            <image id="userPic" src="../images/<%=image%>.png"/>&nbsp;&nbsp;&nbsp;
            <a href="DisconnectServlet" id="button">Disconnect</a>
        </div>
        <div id="darkRedAlphaShadows" style="position: absolute; left:20px;">
            <a href="Menu" id="button">Back To Menu</a>
        </div>
            
        <!-- BODY PART -->
        <center>
            <br>
            <div id="darkRedAlphaShadows" style="width: 420px;border: rgba(0,0,0,0.4) solid 1px">
                name
                &nbsp;<input id="mazeName" class="input" type="text" value=""/>
                &nbsp;&nbsp;&nbsp;<button id="button" onclick="onCreateSinglePlayerMazeClicked(19,19)">Create</button>
            </div>
            <canvas id="mazeCanvas" width="400" height="400" class="canvasStyle"/>
        </center>
    </body>
</html>
