<%-- 
    Document   : editMeal
    Created on : 27.6.2011, 2:00:17
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
        <h2>Správa pokrmu <c:out value="${meal.name}" /></h2>
        <p>Pole označená * jsou povinná.</p>
        <form action="editMeal" method="post">
            <table>
                <tr>
                    <td><b>Nové jméno: </b></td>
                    <td><input type="text" value="${meal.name}" name="mname" size="20" maxlength="25">* <c:out value="${errName}"/></td>
                </tr>
                <tr>
                    <td>Nový popis:</td>
                    <td><textarea name="desc" cols="50" rows="5" wrap="hard"><c:out value="${meal.description}"></c:out></textarea></td>
                </tr>
            </table>
                <input type ="submit" value="Ulož">
        </form>
    </body>
    </body>
</html>
