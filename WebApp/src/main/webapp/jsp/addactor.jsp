<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="def" var="resourceBundle"/>



<html>
<head>
    <!--InternetExplorer link  -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta title="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-slider.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>


<body>
<%@ include file="/jsp/header.jsp" %>


<div class="container">
    <h1 class="page-header"><fmt:message key="header.addactor" bundle="${resourceBundle}"/>:</h1>

    <form title="addactor" action="Controller" method="post">
        <input type="hidden" name="command" value="addactor">
        <input type="hidden" name="page" value="path.page.main">

        <div class="form-group col-md-8 col-sm-6 col-xs-12">
            <label class="col-md-3 control-label"><fmt:message key="actor.movie" bundle="${resourceBundle}"/></label>
            <div class="col-md-8">
                <input type="text" name="movie" placeholder="" class="col-lg-6 col-xs-6 col-sm-6 col-md-6" pattern="[A-Za-z][\w ]{1,56}$" required>
            </div>
        </div>
        <div class="space"></div>
        <div class="form-group col-md-8 col-sm-6 col-xs-12">
            <label class="col-md-3 control-label"><fmt:message key="movie.year" bundle="${resourceBundle}"/></label>
            <div class="col-md-8">
                <select class="span2 col-lg-6 col-xs-6 col-sm-6 col-md-6" name="year" required>
                    <c:forEach var="i" begin="1970" end="2017">
                        <option>${i}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="space"></div>

        <input class="btn btn-info btn-lg" type="submit" name="button2" value="<fmt:message key="user.save" bundle="${resourceBundle}"/>"/>

        <div class="space"></div>

        <div class="col-lg-10 col-xs-10 col-sm-10 col-md-10 pagination-centered">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <button id="addactor" type="button" class="btn btn-primary"><fmt:message key="header.addactor" bundle="${resourceBundle}"/></button>
                    <button id="deleteactor" type="button" class="btn btn-primary"><fmt:message key="actor.delete" bundle="${resourceBundle}"/></button>
                </div>
                <div id="actorlist" class="col-lg-10 col-xs-10 col-sm-10 col-md-10 panel-body row">
                    <div class="row">

                        <!-- actors tags -->
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<form title="back" action="Controller" method="post">
    <input type="hidden" name="command" value="empty">
    <input type="hidden" name="page" value="path.page.main">
    <input class="btn btn-primary" type="submit" name="button"
           value="<fmt:message key="back" bundle="${resourceBundle}"/>"/>
</form>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->

<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-slider.js"></script>
<script src="/js/custom.js"></script>


</body>
</html>

