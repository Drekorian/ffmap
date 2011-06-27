<%-- 
    Document   : registrationSuccess
    Created on : 26.6.2011, 5:34:29
    Author     : Stash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header_full.jsp" %>

<div id="text_wrapper">
    <div id="text">
        <h2>Registrace se zdařila!</h2>
        <p>Váš účet byl úspěšně vytvořen, gratulujeme!</p>
        <h3>Vaše údaje:</h3>
        <ul>
            <li><b>Uživatelské jméno: </b><c:out value="${username}"></c:out></li>
            <li><b>Heslo: </b><c:out value="${password}"></c:out></li>
            <li><b>Jméno: </b><c:out value="${firstname}"></c:out> <c:out value="${lastname}"></c:out></li>
        </ul>
        <a href="<c:url value="/index.jsp" />">Klepnutím zde se vraťte na úvodní stránku kde se můžete přihlásit</a><br/>
    </div>
</div>

<%@include file="includes/footer.jsp" %>
