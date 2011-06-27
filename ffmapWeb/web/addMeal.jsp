<%-- 
    Document   : addMeal
    Created on : 26.6.2011, 20:19:10
    Author     : Stash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header_full.jsp" %>

<div id="text_wrapper">
    <div id="text">
        <h1>Přidání nového jídla</h1>
        <p>Pole označená * jsou povinná</p>
        <form action="addMeal" method="post">
            <table border="0">
                <tr>
                    <td><b>Název: </b></td>
                    <td><input type="text" name="mealname" size="20" maxlength="25">* <c:out value="${mealname_error}"></c:out></td>
                </tr>
                <tr>
                    <td><b>Popis: </b></td>
                    <td><textarea name="desc" cols="50" rows="5" wrap="hard"><c:out value="${meal_desc}"></c:out></textarea></td>
                </tr>
            </table>
            <input type="submit" value="Přidat" align="center">
        </form>
    </div>
</div>

<%@include file="includes/footer.jsp" %>
