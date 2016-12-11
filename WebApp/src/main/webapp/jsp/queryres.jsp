<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Result</title>
</head>
<body>

<table>
    <c:forEach var="elem" items="${genres}">
        <tr>
            <td><c:out value="${elem}"/></td>
        </tr>
    </c:forEach>
</table>

<form name="back" action="/ParseServlet" method="POST">
    <input type="submit" name="button" value="${local:getSting("finish.back")}"/>
</form>

</body>
</html>
