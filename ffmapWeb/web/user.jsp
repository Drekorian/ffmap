<%-- 
    Document   : user
    Created on : 24.6.2011, 14:53:33
    Author     : Marek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header_full.jsp" %>

<div id="text_wrapper">
    <div id="text">
        <h2>${user.userName}</h2>

        <ul>
            <li><strong>Jméno:</strong> ${user.firstName}</li>
            <li><strong>Příjmení:</strong> ${user.surname}</li>
            <li><strong>Datum registrace:</strong>
                <fmt:formatDate pattern="dd. MM. yyyy" value="${user.dateRegistered}" /></li>
            <li><strong>Aktivní:</strong>
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
</div>

<%@include file="/includes/footer.jsp" %>