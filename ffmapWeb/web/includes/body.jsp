<%-- 
    Document   : body
    Created on : 27.6.2011, 7:05:35
    Author     : Marek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div id="content">
            <div id="header_wrapper">
                <div style="float:right">
                    <c:choose>
                        <c:when test="${logged_user == null}">
                            <form action="<c:url value="/login" />" method="post">
                                <strong>Jméno:</strong> <input type="text" name="username" size="20" maxLength="20" >
                                <strong>Heslo:</strong> <input type="password" name="password" size="20" maxLength="20">
                                <input type="submit" value="Login">
                                <a href="<c:url value="/register" />">Registrovat</a><br/>
                                ${login_error}
                            </form>
                        </c:when>
                        <c:otherwise>
                            <strong>Přihlášen: </strong>
                            ${logged_user.userName}
                            (${logged_user.firstName} ${logged_user.surname})

                            <a href="<c:url value="/logout" />">Odhlásit</a>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div id="header">
                    <h1 id="title">
                        <a href="<c:url value="/" />">
                            <img src="<c:url value="/logo.png" />" alt="ffmap" width="100" height="100" />
                        </a>
                    </h1>
                    <p id="subtitle">Vyhledávač hladových oken :)</p>
                </div>
            </div>

            <div id="menu_wrapper">
                <div id="menu">
                    <table>
                        <tr>
                            <td>
                                Zobrazit hladová okna:
                            </td>
                            <td>
                                <ul>
                                    <li><a href="<c:url value="/allJointsMap" />">
                                            na mapě!
                                    </a></li>
                                    <li><a href="<c:url value="/cheapestMap" />">
                                            nejlevnější na mapě!
                                    </a></li>
                                    <li><a href="<c:url value="/joints" />">
                                            výpis!
                                    </a></li>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Profily:
                            </td>
                            <td>
                                <ul>
                                    <c:if test="${(logged_user != null) && logged_user.role == \"ADMIN\"}">
                                        <li><a href="<c:url value="/users" />">
                                                všechny!
                                        </a></li>
                                    </c:if>
                                    <c:if test="${logged_user != null}">
                                        <li><a href="<c:url value="/user/${logged_user.id}" />">
                                                můj!
                                        </a></li>
                                    </c:if>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Jídla:
                            </td>
                            <td>
                                <ul>
                                    <li><a href="<c:url value="/meals" />">
                                            všechny!
                                    </a></li>
                                </ul>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <hr />

            <c:if test="${msg}">
                <p class="flash">
                    <c:out value="${msg}"/>
                </p>
            </c:if>
