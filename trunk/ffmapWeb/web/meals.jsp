<%-- 
    Document   : meals
    Created on : 26.6.2011, 0:22:27
    Author     : Stash
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
        <title>Výpis všech jídel v databázi</title>
    </head>
    <body>
        <div>
            <c:forEach items="${meals}" var="meal">
                <div>
                    <h2><a href="<c:url value="/meal/${meal.id}" />"><c:out value="${meal.name}" /></a></h2>
                    <ul>
                        <li>
                            Název:
                            <c:out value="${meal.name}" />
                        </li>
                        <c:choose>
                        <c:when test="${meal.description != null}">
                        <li>
                            Popis:
                            <c:out value="${meal.description}" />
                        </li>
                        </c:when>
                        </c:choose>
                    </ul>
                </div>
            </c:forEach>
        </div>

        <p>
            Celkem jídel: ${fn:length(meals)}
        </p>
    </body>
</html>
