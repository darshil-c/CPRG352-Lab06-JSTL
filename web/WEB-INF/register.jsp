<%-- 
    Document   : register
    Created on : 24-Jun-2021, 1:42:16 PM
    Author     : Chaudhari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form method="POST">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username">
            <input type="submit" value="Register">
            <input type="hidden" name="action" value="register">
        </form>
        <p>${message}</p>
    </body>
</html>
