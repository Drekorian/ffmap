<%--
    Document   : users
    Created on : 2011.0624
    Author     : Marek Osvald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header_full.jsp" %>

<div id="text_wrapper">
    <div id="text">
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
    </div>
</div>

<%@include file="includes/footer.jsp" %>