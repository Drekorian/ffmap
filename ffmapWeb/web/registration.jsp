<%-- 
    Document   : registration
    Author     : Aleksandar Zivkovic
    Created on : 2011.0627
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header_full.jsp" %>

<div id="text_wrapper">
    <div id="text">
        <h2>Registrace nového uživatele</h2>
        <p class="exclamation">Všechna pole jsou povinná</p>
        <form action="register" method="post">
            <table border="0">
                <tr>
                    <td><strong>Uživatelské jméno: </strong></td>
                    <td><input type="text" name="username" value="${uname}" size="20" maxlength="20"></td>
                    <td><c:out value="${username_error}"></c:out></td>
                </tr>
                <tr>
                    <td><strong>Heslo: </strong></td>
                    <td><input type="password" name="pass" size="20" maxlength="20"></td>
                    <td><c:out value="${pass_error}"></c:out></td>
                </tr>
                <tr>
                    <td><strong>Potvrzení hesla: </strong></td>
                    <td><input type="password" name="passconf" size="20" maxlength="20"></td>
                    <td><c:out value="${passconf_error}"></c:out></td>
                </tr>
                <tr>
                    <td><strong>Křestní jméno: </strong></td>
                    <td><input type="text" name="firstname" value="${fname}" size="20"></td>
                    <td><c:out value="${firstname_error}"></c:out></td>
                </tr>
                <tr>
                    <td><strong>Příjmení: </strong></td>
                    <td><input type="text" name="lastname" value="${lname}" size="20"></td>
                    <td><c:out value="${lastname_error}"></c:out></td>
                </tr>
            </table>
            <input type="submit" value="Registrovat" align="center">
            <input type="reset" value="Vymazat" align="center">
        </div>
</div>

<%@include file="includes/footer.jsp" %>