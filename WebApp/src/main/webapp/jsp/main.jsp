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
    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <style>

    </style>
</head>


<body>

<%@ include file="/jsp/header.jsp" %>


<div class="btn-group col-lg-offset-1" role="group">
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="specificgenre">
        <input type="hidden" name="page" value="path.page.main">
        <c:forEach var="genre" items="${sessionScope.genrelist}">
            <button name="button" value="${genre}" type="submit" class="btn btn-info">${genre}</button>
        </c:forEach>
    </form>
</div>

<c:if test="${sessionScope.movieSize == 0}">
    <div class="center-block">
        <h3><span class="label label-warning ">No Result</span></h3>
    </div>
</c:if>

<div class="row">

    <c:forEach var="i" begin="${sessionScope.currentMoviePage * 6}" end="${sessionScope.currentMoviePage * 6 + 5}">
        <c:if test="${i < sessionScope.movieSize}">
            <div class="col-xs-8 col-md-4 col-lg-4 ">
                <form action="Controller" method="post" class="movieForm">
                    <input type="hidden" name="command" value="movie">
                    <input type="hidden" name="index" value="${i}">
                    <input type="hidden" name="page" value="path.page.movie">
                    <h2 class="movTitle">
                        <span class="subForm label label-info col-lg-offset-6">${sessionScope.movies.get(i).title}</span>
                    </h2>
                    <h2 class="movRating">
                        <span class="subForm label label-danger col-lg-offset-6">${sessionScope.movies.get(i).rating} </span>
                    </h2>
                    <input class="btn btn-default invisible" type="submit" name="button" value="user"/>

                    <img class="subForm movImg" src="${sessionScope.movies.get(i).icon}" alt="">

                </form>
            </div>
        </c:if>
    </c:forEach>
</div>


<div class="row">

    <div class="col-xs-6 col-md-1 col-lg-1 col-md-offset-5 col-lg-offset-5">

        <form title="previous" action="Controller" method="post">
            <input type="hidden" name="command" value="pagination">
            <input type="hidden" name="page" value="path.page.main">
            <input type="hidden" name="attribute" value="currentMoviePage">
            <input type="hidden" name="action" value="previous">
            <input class="btn btn-primary" type="submit" name="button2" value="Previous"
                    <c:if test="${sessionScope.currentMoviePage == 0}"> disabled </c:if>/>
        </form>

    </div>

    <div class="col-xs-6 col-md-1 col-lg-1">

        <form title="next" action="Controller" method="post">
            <input type="hidden" name="command" value="pagination">
            <input type="hidden" name="page" value="path.page.main">
            <input type="hidden" name="attribute" value="currentMoviePage">
            <input type="hidden" name="action" value="next">
            <input class="btn btn-primary" type="submit" name="button" value="Next"
                    <c:if test="${sessionScope.currentMoviePage == sessionScope.numPages}"> disabled </c:if>/>
        </form>
    </div>

</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/bootstrap.min.js"></script>

<script src="/js/custom.js"></script>

<script type="text/javascript">


</script>
</body>

</html>
