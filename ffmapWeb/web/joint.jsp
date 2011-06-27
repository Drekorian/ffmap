<%-- 
    Document   : user
    Author     : Marek Osvald
    Version    : 2011.0626
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/includes/header_begin.jsp" %>


        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
        ${javaScript}
    </head>
    
    <body onload="initialize()">
<%@include file="/includes/body.jsp" %>

            <div id="text_wrapper">
                <div id="text">
                    <h2><c:out value="${joint.name}" /></h2>
                    <h3>Adresa</h3>
                    <ul>
                        <li><c:out value="${joint.location.streetName}" />
                            <c:out value="${joint.location.streetNumber}" />,
                            <c:out value="${joint.location.city}" />
                        </li>
                        <li>
                            GPS:
                                <fmt:formatNumber maxFractionDigits="0" value="${joint.location.latitude}" />°<fmt:formatNumber maxIntegerDigits="0" value="${joint.location.latitude}" />" /
                                <fmt:formatNumber maxFractionDigits="0" value="${joint.location.longitude}" />°<fmt:formatNumber maxIntegerDigits="0" value="${joint.location.longitude}" />"
                        </li>
                    </ul>

                    <h3>Otevírací doba</h3>
                    <ul>
                        <li>Pondělí
                            <ul>
                                <c:forEach items="${joint.monday}" var="i">
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.from}" /> --
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.to}" />
                                </c:forEach>
                            </ul>
                        </li>

                        <li>Úterý
                            <ul>
                                <c:forEach items="${joint.tuesday}" var="i">
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.from}" /> --
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.to}" />
                                </c:forEach>
                            </ul>
                        </li>

                        <li>Středa
                            <ul>
                                <c:forEach items="${joint.wednesday}" var="i">
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.from}" /> --
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.to}" />
                                </c:forEach>
                            </ul>
                        </li>

                        <li>Čtvrtek
                            <ul>
                                <c:forEach items="${joint.thursday}" var="i">
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.from}" /> --
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.to}" />
                                </c:forEach>
                            </ul>
                        </li>

                        <li>Pátek
                            <ul>
                                <c:forEach items="${joint.friday}" var="i">
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.from}" /> --
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.to}" />
                                </c:forEach>
                            </ul>
                        </li>

                        <li>Sobota
                            <ul>
                                <c:forEach items="${joint.saturday}" var="i">
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.from}" /> --
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.to}" />
                                </c:forEach>
                            </ul>
                        </li>

                        <li>Neděle
                            <ul>
                                <c:forEach items="${joint.sunday}" var="i">
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.from}" /> --
                                    <fmt:formatDate type="time" pattern="HH:mm" value="${i.to}" />
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>

                    <h3>Menu</h3>
                    <table class="jointMenu">
                        <c:forEach items="${joint.menu}" var="i">
                            <tr>
                                <td>${i.meal.name}</td>
                                <c:choose>
                                    <c:when test="${fn:length(i.prices)==1}">
                                        <c:forEach items="${i.prices}" var="p">
                                            <td><fmt:formatNumber maxFractionDigits="0" value="${p.value}" /> Kč</td>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${i.prices}" var="p">
                                            <td>
                                                <fmt:formatDate type="time" pattern="HH:mm" value="${p.key.from}" /> --
                                                <fmt:formatDate type="time" pattern="HH:mm" value="${p.key.to}" />:
                                                <fmt:formatNumber maxFractionDigits="0" value="${p.value}" /> Kč
                                            </td>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </table>

                    <h3>Web</h3>
                    <ul>
                        <li><a href="${joint.webPage}">${joint.webPage}</a></li>
                    </ul>

                    <p>
                        Tagy:
                        <c:forEach items="${joint.tags}" var="tag">
                            #${tag}
                        </c:forEach>
                    </p>

                    <h3>Komentáře</h3>
                    <c:forEach items="${joint.comments}" var="comment">
                        ${comment}
                    </c:forEach>

                    <div id="googleMap" style="width:910px; height:455px;"></div>
                </div>
            </div>

<%@include file="/includes/footer.jsp" %>
