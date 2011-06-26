%--
    Document   : index
    Version    : 2011.0624
    Author     : Marek Osvald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>

<b>
    <c:choose>
        <c:when test="${session} = true">Logged in</c:when>
        <c:otherwise>
            <form action="login" method="post">
                Username: <input type="text" name="username" value="${uname}" size="20" maxlength="15">
                Password: <input type="password" name="password" value="${pass}" size="20" maxlength="15">
            </form>
        </c:otherwise>
    </c:choose>

</b>
<a href="<c:url value="users" />">Zobrazit registrované uživatele</a><br/>
<a href="<c:url value="meals" />">Zobrazit jídla v databázi</a><br/>
<a href="<c:url value="register" />">Registrace nového uživatele</a>

<%@include file="includes/footer.jsp" %>
