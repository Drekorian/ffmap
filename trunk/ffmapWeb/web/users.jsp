<%--
    Document   : users
    Created on : 2011.0624
    Author     : Marek Osvald
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
        <c:choose>
            <c:when test="${logged_user.role == 'ADMIN'}">
                <div>
            <form action="users" method="get">
            Řadit podle: <select name="order-by">
                <option value="uName">Uživatelského jména</option>
                <option value="fName">Křestního jména</option>
                <option value="lName">Příjmení</option>
            </select>
            <input type="submit" name="order" value="Seřadit!">
            </form>
            <c:forEach items="${users}" var="user">
                <div>
                    <h2><a href="<c:url value="/user/${user.id}" />"><c:out value="${user.userName}" /></a></h2>
                    <a href="<c:url value="/deleteUser/${user.id}" />">Vymazat</a>
                    <ul>
                        <li>
                            Jméno:
                            <c:out value="${user.firstName}" />
                            <c:out value="${user.surname}" />
                        </li>
                        <li>
                            Heslo (SHA1):
                            <c:out value="${user.password}" />
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
            </c:when>
            <c:otherwise>
                Nemáte oprávnění sledovat tuto stránku!
            </c:otherwise>
        </c:choose>
        
    </body>
</html>

<%@include file="includes/footer.jsp" %>