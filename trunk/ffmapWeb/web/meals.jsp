<%-- 
    Document   : meals
    Created on : 26.6.2011, 0:22:27
    Author     : Stash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header_full.jsp" %>

<div id="text_wrapper">
    <div id="text">
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

        <p>
            Celkem jídel: ${fn:length(meals)}
        </p>
    </div>
</div>

<%@include file="includes/footer.jsp" %>