<%-- 
    Document   : registration
    Created on : 26.6.2011, 1:48:24
    Author     : Stash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ffmap</title>
    </head>
    <body>
        <h1>User registration</h1>
        <form action="register" method="post">
            <table border="0">
                <tr>
                    <td><b>User name: </b></td>
                    <td><input type="text" name="username" value="" size="20" maxlength="15"></td>
                    <td><c:out value="${username_error}"></c:out></td>
                </tr>
                <tr>
                    <td><b>Password: </b></td>
                    <td><input type="password" name="pass" value="" size="20" maxlength="15"></td>
                    <td><c:out value="${pass_error}"></c:out></td>
                </tr>
                <tr>
                    <td><b>Confirm password: </b></td>
                    <td><input type="password" name="passconf" value="" size="20" maxlength="15"></td>
                    <td><c:out value="${passconf_error}"></c:out></td>
                </tr>
                <tr>
                    <td><b>First name: </b></td>
                    <td><input type="text" name="firstname" value="" size="20"></td>
                    <td><c:out value="${firstname_error}"></c:out></td>
                </tr>
                <tr>
                    <td><b>Last name: </b></td>
                    <td><input type="text" name="lastname" value="" size="20"></td>
                    <td><c:out value="${lastname_error}"></c:out></td>
                </tr>
            </table>
            <input type="submit" value="Submit" align="center">
        </form>
    </body>
</html>
