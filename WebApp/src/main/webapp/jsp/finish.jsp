<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>${local:getSting("finish.name")}</title>
</head>
<body>

<h3>${local.getSting("finish.title")} <c:out value="${paramValues.button}"/></h3>

<table>
    <c:forEach var="elem" items="${results}">
        <tr>
            <td><c:out value="${elem.id}"/></td>
            <td><c:out value="${elem.days}"/></td>
            <td><c:out value="${elem.cost}"/></td>
            <td><c:out value="${elem.transport}"/></td>
        </tr>
    </c:forEach>
</table>

<form name="back" action="/ParseServlet" method="POST">
    <input type="submit" name="button" value="${local:getSting("finish.back")}"/>
</form>


</body>
</html>
