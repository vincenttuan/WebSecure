<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <link rel="stylesheet" href="https://unpkg.com/purecss@2.0.3/build/pure-min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View  Member</title>
    </head>
    <body style="padding: 20px">
        <h1>Hello ${username} 您好 !</h1>
        <table class="pure-table pure-table-bordered">
            <thead>
                <tr>
                    <th>username</th>
                    <th>email</th>
                    <th>cardno</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="map" items="${members}">
                <tr>
                    <c:forEach var="m" items="${map}">
                        <c:choose>
                            <c:when test="${m.key == 'USERNAME'}">
                                <td><a href="/WebSecure/sso/view/salary?username=${m.value}">${m.value}</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>${m.value}</td>
                            </c:otherwise>   
                        </c:choose>
                    </c:forEach>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <p />
        <button type="button" class="pure-button pure-button-primary" onclick="window.location.href='/WebSecure/sso/logout';">Logout</button>
        <button type="button" class="pure-button pure-button-primary" onclick="window.location.href='/WebSecure/mango/input';">Mango</button>
    </body>
</html>
