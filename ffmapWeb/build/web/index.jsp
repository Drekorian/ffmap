<%--
    Document   : index
    Version    : 2011.0627
    Author     : Marek Osvald
--%>

<%@page import="cz.muni.fi.pb138.ffmap.classes.DBHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/header_full.jsp" %>

<% DBHandler.getInstance().createHack(); %>

<div id="text_wrapper">
    <div id="text">
        <h2>Vítej!</h2>
        <p>
            Vítej v aplikaci ffmap. ffmap byl vytvořen studenty Fakulty
            informatiky Masarykovy univerzity Brně jako semestrální projekt
            k předmětu PB138 Moderní značkovací jazyky a jejich aplikace.
        </p>

        <h2>Co ffmap umí?</h2>
        <p>
            Cílem ffmap je vytvořit databázi fastfoodu (lidově nazývaných
            hladová okna) v Brně. Na ffmap se můžeš podílet i ty! Stačí zadat
            informace o svém oblíbeném okně do naší databáze!
        </p>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
