<%-- 
    Document   : deleteMeal
    Created on : 27.6.2011, 0:27:24
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
        <h2>Smazání pokrmu <c:out value="${meal.name}" /></h2>
        <c:if test="${meal.description != null}">
        <p>Popis: <c:out value="${meal.description}" /></p>
        </c:if>
        <p>Opravdu chcete smazat pokrm tento pokrm?</p>
        <form action="deleteMeal" method="post">
            <input type="submit" value="Ano" name="conf">
            <input type="submit" value="Ne" name="conf">
        </form>
    </body>
</html>
<%@include file="includes/footer.jsp" %>
<c:remove var="meal" />
