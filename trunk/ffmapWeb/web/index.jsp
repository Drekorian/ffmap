<%--
    Document   : index
    Version    : 2011.0624
    Author     : Marek Osvald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>

<c:if test="${logged_user.role == 'ADMIN'}">
<a href="<c:url value="/users" />">Zobrazit registrované uživatele</a><br/>
</c:if>
<a href="<c:url value="/meals" />">Zobrazit jídla v databázi</a><br/>
<a href="<c:url value="/addMeal" />">Přidej jídlo</a>

<%@include file="includes/footer.jsp" %>
