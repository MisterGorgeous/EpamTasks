<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="def" var="resourceBundle"/>
<fmt:setLocale value=""/>

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
    <input type="submit" name="button" value="<fmt:message key="finish.back" bundle="${resourceBundle}"/>"/>
</form>

</body>
</html>
