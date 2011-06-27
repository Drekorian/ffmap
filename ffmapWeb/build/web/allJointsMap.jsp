<%-- 
    Document   : user
    Author     : Marek Osvald
    Version    : 2011.0626
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/includes/header_begin.jsp" %>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    ${javaScript}

    </head>
    <body onload="initialize()">

<%@include file="includes/body.jsp" %>
    <div id="text_wrapper">
        <div id="text">
            <div id="googleMap" style="width:910px; height:455px;"></div>
        </div>
    </div>

<%@include file="/includes/footer.jsp" %>
