<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="myshortname" uri="mycompany" %>
<html>
<head>
    <title>Table</title>
</head>
<body>
<myshortname:table-revenue rows="4" head="Revenue">
    ${rw.getRevenue()}
</myshortname:table-revenue >
<br/>
<myshortname:table-revenue>5 rub BulbaComp</myshortname:table-revenue >

Кодировка запроса: ${ pageContext.request.characterEncoding }
</body>
</html>
