<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>


<fmt:setBundle basename="def" var="resourceBundle"/>
<fmt:setLocale value="${sessionScope.local}"/>

<html>
<head>
    <title>MENU</title>
</head>

<body>
<fmt:message key="start.name" bundle="${resourceBundle}"/>

<form name="parse" action="/ParseServlet" method="post">
    <input type="submit" name="button" value="DOM"/>
    <input type="submit" name="button" value="<fmt:message key="start.sax" bundle="${resourceBundle}"/>"/>
    <input type="submit" name="button" value="<fmt:message key="start.stax" bundle="${resourceBundle}"/>"/>
    <input type="submit" name="button" value="<fmt:message key="start.en" bundle="${resourceBundle}"/>"/>
    <input type="submit" name="button" value="<fmt:message key="start.ru" bundle="${resourceBundle}"/>"/>
</form>

<h3> Choose File to Upload in Server </h3>
<!--<form name="upload" action="/ParseServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="file" value="file" />
    <input type="submit" name="button" value="upload" />
</form>-->
<form method="post" action="/ParseServlet" enctype="multipart/form-data">
    <input type="file" name="file" accept=".txt"/>
    <button type="submit">File</button>
    <input type="hidden" name="button" value="upload">
</form>

<h3>Get Genres </h3>
<form name="query" action="/ParseServlet" method="post">
    <input type="submit" name="button" value="query" />
</form>

<h3>Get Genres </h3>
<form name="table" action="/ParseServlet" method="post">
    <input type="submit" name="button" value="table" />
</form>

<h3>Get Films by year</h3>
<form name="film" action="/ParseServlet" method="post">
    <input name="year" maxlength="25" size="40" value="" pattern="\d{4}">
    <input type="submit" name="button" value="film" />
</form>



</body>
</html>
