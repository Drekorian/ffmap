<%-- 
    Document   : deleteUserSuccess
    Created on : 27.6.2011, 3:40:46
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
        <h1>Uživatel <c:out value="${username}"></c:out> byl vymazán!</h1>
        <a href="<c:url value="/users" />">Klepnutím zde se vrátíte zpět na seznam uživatelů.</a><br/>
    </body>
</html>

<%@include file="includes/footer.jsp" %>
