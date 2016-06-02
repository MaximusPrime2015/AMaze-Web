<%-- 
    Document   : Multiplayer
    Created on : May 23, 2016, 6:18:40 PM
    Author     : Max
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Multiplayer</title>
        <link rel="stylesheet" href="../styles.css">
        <script src="../mazeScripts.js"></script>
        <script src="../scripts.js"></script>
        <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-
a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
        <script>
            window.onkeydown = function(e) { keyPressed(e);}
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
            <a href="DisconnectServlet" class="button">Disconnect</a>
        </div>
        <div id="darkRedAlphaShadows" style="position: absolute; left:20px;">
            <a href="Menu" class="button" margin-right:40px >Back To Menu</a>
            <button id="hintBtn" class="button" margin-left:40px disabled onclick="drawHint()">Hint</button>
            <br><br>
            Game Name:
            <input id="game_name" type="text" onclick="clearName()" />
            <button id="connectBtn" class="button" onclick="multiplayerConnect()">Connect</button>

        </div>
            
        <!-- BODY PART -->
        <center>
            <br><br><br><br><br><br>
            <table>
                <tr>
                    <th><canvas id="mazeCanvas" width="400" height="400" class="canvasStyle"/></th>
                    <th> <div id="darkRedAlphaShadows"><h2>VS</h2></div></th>
                    <th><canvas id="opponentMazeCanvas" width="400" height="400" class="canvasStyle"/></th>
                </tr>
            </table>
        </center>
    </body>
</html>
