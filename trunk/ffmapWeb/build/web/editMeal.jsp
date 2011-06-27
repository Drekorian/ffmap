<%-- 
    Document   : editMeal
    Created on : 27.6.2011, 2:00:17
    Author     : Stash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header_full.jsp" %>

<div id="text_wrapper">
    <div id="text">
        <h2>Správa pokrmu <c:out value="${meal.name}" /></h2>
        <p>Pole označená * jsou povinná.</p>
        <form action="editMeal" method="post">
            <table>
                <tr>
                    <td><b>Nové jméno: </b></td>
                    <td><input type="text" value="${meal.name}" name="mname" size="20" maxlength="25">* <c:out value="${errName}"/></td>
                </tr>
                <tr>
                    <td>Nový popis:</td>
                    <td><textarea name="desc" cols="50" rows="5" wrap="hard"><c:out value="${meal.description}"></c:out></textarea></td>
                </tr>
            </table>
                <input type ="submit" value="Ulož">
        </form>
    </div>
</div>