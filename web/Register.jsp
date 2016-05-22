<%-- 
    Document   : Register
    Created on : May 21, 2016, 9:54:49 PM
    Author     : Max
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body style="background-image:url(http://www.hdwallpapery.com/static/images/background-wallpapers-26_fXb5f3k.jpg);background-size:cover;">
        <!-- HEADER PART -->
        <div id="darkRedAlphaShadows">
            <h1>AMaze-ing</h1>
        </div>
        
        <!-- BODY PART -->
        <div id="redCenteredAlphaShadows">
            <h3 align="center">Register</h3>
            <form action="RegisterServlet" method="post">
                <table border="0">
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp;Username :</div></th>
                        <th align="center"><input id="input" type="text" name="username"/></th>
                    </tr>
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp;Password :</div></th>
                        <th align="center"><input id="input" type="password" name="password"/></th>
                    </tr>
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp;Real Name :</div></th>
                        <th align="center"><input id="input" type="text" name="real_name"/></th>
                    </tr>
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp;Email :</div></th>
                        <th align="center"><input id="input" type="text" name="email"/></th>
                    </tr>
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp</div></th>
                    </tr>
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp;Profile Picture :&nbsp;&nbsp;</div></th>
                        <th align="center">
                            <div>
                                <input type="radio" name="ico1"><image src="images/ico1.png"/>
                                &nbsp;&nbsp;
                                <input type="radio" name="ico2"><image src="images/ico1.png"/>
                                &nbsp;&nbsp;
                                <input type="radio" name="ico3"><image src="images/ico1.png"/>
                            </div>
                            <br>
                            <div>
                                <input type="radio" name="ico4"><image src="images/ico1.png"/>
                                &nbsp;&nbsp;
                                <input type="radio" name="ico5"><image src="images/ico1.png"/>
                                &nbsp;&nbsp;
                                <input type="radio" name="ico6"><image src="images/ico1.png"/>
                            </div>
                        </th>
                    </tr>
                    <tr align="center">
                        <th></th>
                        <th align="right"><br><div><input id="button" type="submit" value="Register"/></div></th>
                    </tr>
                </table>
            </form>
        </div>
                
        <!-- FOOTER PART -->
        <div id="footer">
            Copyright © Michael & Max
        </div>
    </body>
</html>
