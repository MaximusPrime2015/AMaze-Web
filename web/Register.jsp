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
    </head>
    <body>
        <form action="RegisterServlet" method="post">
            <div>Username:</div>
            <input type="text" name="username"/>
            <div>Password:</div>
            <input type="password" name="password"/>
            <div>Real Name:</div>
            <input type="text" name="real_name"/>
            <div>Email:</div>
            <input type="text" name="email"/>
            <div>Profile Picture</div>
            <div>
                <input type="radio" name="profile" value="Red"> Red<br>
                <input type="radio" name="profile" value="Green"> Green<br>
                <input type="radio" name="profile" value="Blue"> Blue
            </div>
            <div><input type="submit"/></div>
        </form>
    </body>
</html>
