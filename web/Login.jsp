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
        <title>Log In Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body style="background-image:url(http://i.imgur.com/nBp49hp.jpg);">
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
                        <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name</th>
                        <th align="center"><input id="input" type="text" name="username" /></th> 
                    </tr>
                    <tr>
                        <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password&nbsp;&nbsp;</th>
                        <th align="center"><input id="input" type="password" name="password" ></th> 
                    </tr>
                    <tr>
                        <th></th>
                        <th><input class="button" type="submit" value="Log in">
                            <a class="button" href="RegisterServlet">Register</a>
                        </th>
                    </tr>
                </table>
            </form>
            <% if (request.getAttribute("error") != null
            && (Boolean) request.getAttribute("error")) {%>
                <div style="color:white;">Wrong username/password. Please try again.</div>
            <% }%>
        </div>
        
        <!-- FOOTER PART -->
        <div id="footer">
            Copyright © Michael & Max
        </div>
    </body>
</html>
