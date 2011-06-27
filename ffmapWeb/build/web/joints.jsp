<%--
    Document   : joints
    Created on : 2011.0627
    Author     : Marek Osvald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header_full.jsp" %>

<div id="text_wrapper">
    <div id="text">
        <ul>
            <c:forEach items="${joints}" var="joint">
                <li>
                    <a href="<c:url value="/joint/${joint.id}" />">${joint.name}</a>
                </li>
            </c:forEach>
        </ul>

        <p>
            Celkem hladových oken: ${fn:length(joints)}
        </p>
    </div>
</div>

<%@include file="includes/footer.jsp" %>