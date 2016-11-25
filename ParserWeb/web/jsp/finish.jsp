<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Finish</title>
</head>
<body>
    <table>
        <c:forEach var="result" items="${results}" varStatus="status">
            <tr>
                <!--<td><c:out value="${result.id}"/></td>
                <td><c:out value="${result.days}"/></td>
                <td><c:out value="${result.cost}"/></td>
                <td><c:out value="${result.transport}"/></td>-->
                <td><c:out value="${result}"/></td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
