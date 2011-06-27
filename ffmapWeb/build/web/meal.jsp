<%-- 
    Document   : meal
    Created on : 26.6.2011, 0:36:14
    Author     : Stash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header_full.jsp" %>


<div id="text_wrapper">
    <div id="text">
        <h2>${meal.name}</h2>
        <ul>
            <li><strong>Jméno:</strong> ${meal.name}</li>
        </ul>
    </div>
</div>

<%@include file="includes/footer.jsp" %>
