<%-- 
    Document   : registrationSuccess
    Created on : 26.6.2011, 5:34:29
    Author     : Stash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="includes/header.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ffmap</title>
    </head>
    <body>
        <h1>Registrace se zdařila!</h1>
        <p>Váš účet byl úspěšně vytvořen, gratulujeme!</p>
        <h3>Vaše údaje:</h3>
        <ul>
            <li><b>Uživatelské jméno: </b><c:out value="${username}"></c:out></li>
            <li><b>Heslo: </b><c:out value="${password}"></c:out></li>
            <li><b>Jméno: </b><c:out value="${firstname}"></c:out> <c:out value="${lastname}"></c:out></li>
        </ul>
        <a href="<c:url value="/index.jsp" />">Klepnutím zde se vraťte na úvodní stránku kde se můžete přihlásit</a><br/>
    </body>
</html>

<%@include file="includes/footer.jsp" %>
