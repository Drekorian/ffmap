<%-- 
    Document   : deleteUser
    Created on : 27.6.2011, 2:44:50
    Author     : Stash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="includes/header_full.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ffmap</title>
    </head>
    <body>
        <h2>Smazání uživatele <c:out value="${user.userName}" /></h2>
        <table>
            <tr>
                <td>Uživatelské jméno: </td>
                <td><c:out value="${user.userName}"/></td>
            </tr>
            <tr>
                <td>Jméno: </td>
                <td><c:out value="${user.firstName}"/></td>
            </tr>
            <tr>
                <td>Příjmení: </td>
                <td><c:out value="${user.surname}"/></td>
            </tr>
        </table>
        <p>Opravdu chcete smazat tohoto uživatele?</p>
        <form action="deleteUser" method="post">
            <input type="submit" value="Ano" name="conf">
            <input type="submit" value="Ne" name="conf">
        </form>
    </body>
</html>

<%@include file="includes/footer.jsp" %>
