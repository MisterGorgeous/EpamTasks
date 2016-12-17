<%@ page  contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
 <c:forEach var="trace" items="${pageContext.exception.stackTrace}">
    <p>${trace}</p>
</c:forEach>
<a href="/jsp/start.jsp">BACK</a>
</body>
</html>
