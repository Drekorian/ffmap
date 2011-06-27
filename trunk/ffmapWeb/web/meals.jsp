<%-- 
    Document   : meals
    Created on : 26.6.2011, 0:22:27
    Author     : Stash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ffmap</title>
    </head>
    <body>
        <div>
            <c:forEach items="${meals}" var="meal">
                <div>
                    <h2><a href="<c:url value="/meal/${meal.id}" />"><c:out value="${meal.name}" /></a></h2>
                    <c:if test="${logged_user != null}">
                        <a href="<c:url value="/editMeal/${meal.id}" />">Změnit</a>
                        <c:if test="${logged_user.role == 'ADMIN'}">
                            <a href="<c:url value="/deleteMeal/${meal.id}" />">Vymazat</a>
                        </c:if>
                    </c:if>
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

<%@include file="includes/footer.jsp" %>