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

    <%-- <c:forEach var="movie" items="${sessionScope.movies}">--%>
    <c:forEach var="i" begin="${sessionScope.currentMoviePage * 6}" end="${sessionScope.currentMoviePage * 6 + 5}">
        <div class="col-xs-8 col-md-4 col-lg-4 ">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="movie">
                <input type="hidden" name="index" value="${i}">
                <input type="hidden" name="page" value="path.page.movie">
                <input class="btn btn-default invisible" type="submit" name="button" value="user"/>
                    <%--<img src="${movies.get(0).getIcon()}" alt="..." >--%>
                <img width="400" height="600" class="thumbnail " src="${sessionScope.movies.get(i).icon}" alt="..."
                     onclick="$(this).closest('form').submit();">
            </form>
            ${i}
        </div>
    </c:forEach>
</div>


<div class="row center-block">
    <div class="col-xs-6 col-md-6 col-lg-6">
    <c:if test="${sessionScope.currentMoviePage < sessionScope.movieSize}">
        <form title="next" action="Controller" method="post">
            <input type="hidden" name="command" value="pagination">
            <input type="hidden" name="page" value="path.page.main">
            <input type="hidden" name="attribute" value="currentMoviePage">
            <input type="hidden" name="action" value="next">
            <input class="btn btn-primary" type="submit" name="button" value="Next"/>
        </form>
    </c:if>
</div>
<div class="col-xs-6 col-md-6 col-lg-6">
    <c:if test="${sessionScope.currentMoviePage > 0}">
        <form title="previous" action="Controller" method="post">
            <input type="hidden" name="command" value="pagination">
            <input type="hidden" name="page" value="path.page.main">
            <input type="hidden" name="attribute" value="currentMoviePage">
            <input type="hidden" name="action" value="previous">
            <input class="btn btn-primary" type="submit" name="button2" value="Previous"/>
        </form>
    </c:if>
</div>
    <%-- <form title="back" action="/jsp/main.jsp" method="post">
         <input type="hidden" name="command" value="cross">
         <input type="hidden" name="page" value="path.page.main">
         <input type="button"   name="button" value="<fmt:message key="back" bundle="${resourceBundle}"/>"  />
     </form>
     ${sessionScope.currentMoviePage = sessionScope.currentMoviePage - 1}   --%>



</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>
