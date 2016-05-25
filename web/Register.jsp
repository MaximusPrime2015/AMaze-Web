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
        <script src="scripts.js"></script>
    </head>
    <body style="background-image:url(http://i.imgur.com/nBp49hp.jpg);">
        <!-- HEADER PART -->
        <div id="darkRedAlphaShadows">
            <h1>AMaze-ing</h1>
        </div>
        
        <!-- BODY PART -->
        <div id="redCenteredAlphaShadows">
            <h3 align="center">Register</h3>
            <form name="registerForm" action="RegisterServlet" method="post" onsubmit="return validateRegistrationForm()">
                <table border="0">
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp;Username :</div></th>
                        <th align="center"><input id="input" type="text" name="username"/>
                            <image id="userNameValid" src="images/error.png" style="visibility: hidden"/>
                        </th>
                    </tr>
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp;Password :</div></th>
                        <th align="center"><input id="input" type="password" name="password"/>
                            <image id="passValid" src="images/error.png" style="visibility: hidden"/>
                        </th>
                    </tr>
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp;Real Name :</div></th>
                        <th align="center"><input id="input" type="text" name="real_name"/>
                            <image id="nameValid" src="images/error.png" style="visibility: hidden"/>
                        </th>
                    </tr>
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp;Email :</div></th>
                        <th align="center"><input id="input" type="text" name="email"/>
                            <image id="emailValid" src="images/error.png" style="visibility: hidden"/>
                        </th>
                    </tr>
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp</div></th>
                    </tr>
                    <tr align="center">
                        <th align="center"><div>&nbsp;&nbsp;Profile Picture :&nbsp;&nbsp;</div></th>
                        <th align="center">
                            <div>
                                <input type="radio" checked="true" name="icons" value="ico1"><image src="images/ico1.png"/>
                                &nbsp;&nbsp;
                                <input type="radio" name="icons" value="ico2"><image src="images/ico2.png"/>
                                &nbsp;&nbsp;
                                <input type="radio" name="icons" value="ico3"><image src="images/ico3.png"/>
                            </div>
                            <br>
                            <div>
                                <input type="radio" name="icons" value="ico4"><image src="images/ico4.png"/>
                                &nbsp;&nbsp;
                                <input type="radio" name="icons" value="ico5"><image src="images/ico5.png"/>
                                &nbsp;&nbsp;
                                <input type="radio" name="icons" value="ico6"><image src="images/ico6.png"/>
                            </div>
                        </th>
                    </tr>
                    <tr align="center">
                        <th></th>
                        <th align="right"><br><div><input class="button" type="submit" value="Register"/></div></th>
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
