<%-- 
    Document   : shoppingList
    Created on : 24-Jun-2021, 1:42:31 PM
    Author     : Chaudhari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username} <a href="<c:url value="/ShoppingList">
                                     <c:param name="action" value="logout"></c:param>
                                 </c:url>">Logout</a> </p>

        <h2>List</h2>
        <form method="POST">
            <label for="item">Add item:</label>
            <input type="text" name="item" id="item">
            <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>
        <form method="POST">
            <table>
                <c:forEach var="list" items="${list}">
                    <tr>
                        <td>
                            <input type="radio" name="listitem" value="${list}" id="${list}">
                        </td>
                        <td>
                            <label for="${list}">${list}</label>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>
