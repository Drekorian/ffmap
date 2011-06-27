<%-- 
    Document   : addMeal
    Created on : 26.6.2011, 20:19:10
    Author     : Stash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="includes/header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ffmap</title>
    </head>
    <body>
        <h1>Přidání nového jídla</h1>
        <p>Pole označená * jsou povinná</p>
        <form action="addMeal" method="post">
            <table border="0">
                <tr>
                    <td><b>Název: </b></td>
                    <td><input type="text" name="mealname" size="20" maxlength="25">* <c:out value="${mealname_error}"></c:out></td>
                </tr>
                <tr>
                    <td><b>Popis: </b></td>
                    <td><textarea name="desc" cols="50" rows="5" wrap="hard"><c:out value="${meal_desc}"></c:out></textarea></td>
                </tr>
            </table>
            <input type="submit" value="Add" align="center">
        </form>
    </body>
</html>

<%@include file="includes/footer.jsp" %>
