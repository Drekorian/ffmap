<%--
    Document   : users
    Created on : 2011.0624
    Author     : Marek Osvald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Všichni uživatelé v systému</title>
    </head>
    <body>
        <div>
            <c:forEach items="${users}" var="user">
                <div>
                    <h2><a href="<c:url value="/user/${user.id}" />"><c:out value="${user.userName}" /></a></h2>
                    <ul>
                        <li>
                            Jméno:
                            <c:out value="${user.firstName}" />
                            <c:out value="${user.surname}" />
                        </li>
                        <li>
                            Datum registrace: 
                            <f:formatDate type="date" value="${user.dateRegistered}" pattern="d. M. 20y" />
                        </li>
                        <li>
                            <c:choose>
                                <c:when test="${user.active}">
                                    aktivní
                                </c:when>
                                <c:otherwise>
                                    neaktivní
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </ul>
                </div>
            </c:forEach>
        </div>

        <p>
            Celkem uživatelů: ${fn:length(users)}
        </p>
    </body>
</html>
