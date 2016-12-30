<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="def" var="resourceBundle"/>

<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <!--InternetExplorer link  -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta title="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
</head>


<body>

<%@ include file="/jsp/header.jsp" %>


<div class="row">
    <div class="col-xs-8 col-md-4">

        <c:set var="index" value="0" scope="page"/>

        <c:forEach var="film" items="${sessionScope.films}">
            <form class="thumbnail" action="Controller" method="post">
                <input type="hidden" name="command" value="movie">
                <input type="hidden" name="index" value="${index}">
                <input type="hidden" name="page" value="path.page.movie">
                <input class="btn btn-default invisible" type="submit" name="button" value="user"/>
                    <%--<img src="${films.get(0).getIcon()}" alt="..." >--%>
                <img src="${film.icon}" alt="..." onclick="$(this).closest('form').submit();">
                <c:set var="index" value="${index + 1}" scope="page"/>
            </form>
        </c:forEach>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>

</html>
