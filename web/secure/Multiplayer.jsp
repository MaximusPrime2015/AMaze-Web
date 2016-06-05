<%-- 
    /**
    * exe 3
    * @author Michael Vassernis 319582888 vaserm3
    * @author Max Anisimov 322068487 anisimm
    */
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Multiplayer</title>
        <link rel="stylesheet" href="../styles.css">
        <script src="../mazeScripts.js"></script>
        <script src="../multiplayerScripts.js"></script>
        <script src="../scripts.js"></script>
        <script src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-
a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
        <script>
            window.onkeyup = function(e) { m_keyPressed(e);}
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
        </div>
            
        <!-- BODY PART -->
        <center>
            <br><br><br><br><br>
            <table>
                <tr>
                    <th><canvas id="myCanvas" width="400" height="400" class="canvasStyle"/></th>
                    <th> <div id="darkRedAlphaShadows"><h2>VS</h2></div></th>
                    <th><canvas id="othersCanvas" width="400" height="400" class="canvasStyle"/></th>
                </tr>
            </table>
        </center>
        <script>
            m_printLoading();
            // long poll request for maze
            m_requestMaze();
        </script>
    </body>
</html>
