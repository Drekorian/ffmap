<%-- 
    Document   : users
    Created on : 24.6.2011, 4:19:12
    Author     : Marek Osvald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${users}" var="user">
            <c:out value="${user.firstName}"> </c:out>
            <c:out value="${user.surname}"></c:out>
        </c:forEach>
    </body>
</html>
