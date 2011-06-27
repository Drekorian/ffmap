<%-- 
    Document   : header
    Created on : 24.6.2011, 17:57:45
    Author     : Marek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--<?xml version="1.0 encoding="UTF-8" ?>-->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>ffmap</title>
        <link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" media="all" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id="content">
            <c:choose>
                <c:when test="${logged_user == null}">
                    <form action="login" method="post">
                        <p align="right">
                        Jméno: <input type="text" name="username" size="20" maxLength="20" >
                        Heslo: <input type="password" name="password" size="20" maxLength="20">
                        <input type="submit" value="Login"> <a href="<c:url value="/register" />">Registrovat</a>
                        <br/><c:out value="${login_error}"></c:out>
                        </p>
                    </form>
                </c:when>
                <c:otherwise>
                    <p align="right">
                    <b>Přihlášen: </b><c:out value="${logged_user.userName}"></c:out> 
                    (<c:out value="${logged_user.firstName}"></c:out> <c:out value="${logged_user.surname}"></c:out>)
                    <a href="<c:url value="/logout" />">Logout</a><br/>
                    </p>
                </c:otherwise>
            </c:choose>
            <br>
            <p align="center"><c:out value="${msg}"/></p>
            <h1>
                <a href="<c:url value="/" />">
                    <img src="<c:url value="/logo.png" />" border="0" width="100" height="100" />
                </a>
            </h1>