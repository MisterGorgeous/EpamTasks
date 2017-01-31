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
    <h1 class="page-header">Add Actors:</h1>

    <form title="addactor" action="Controller" method="post">
        <input type="hidden" name="command" value="addactor">
        <input type="hidden" name="page" value="path.page.main">

        <div class="form-group col-md-8 col-sm-6 col-xs-12">
            <label class="col-md-3 control-label">Movie:</label>
            <div class="col-md-8">
                <input type="text" name="movie" placeholder="" class="col-lg-6 col-xs-6 col-sm-6 col-md-6">
            </div>
        </div>
        <div class="space"></div>
        <div class="form-group col-md-8 col-sm-6 col-xs-12">
            <label class="col-md-3 control-label">Year:</label>
            <div class="col-md-8">
                <select class="span2 col-lg-6 col-xs-6 col-sm-6 col-md-6" name="year">
                    <c:forEach var="i" begin="1970" end="2017">
                        <option>${i}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="space"></div>

        <input class="btn btn-info btn-lg" type="submit" name="button2" value="Save"/>

        <div class="space"></div>

        <div class="col-lg-10 col-xs-12 col-sm-12 col-md-10 pagination-centered">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <button id="addactor" type="button" class="btn btn-primary">Add</button>
                    <button id="deleteactor" type="button" class="btn btn-primary">Delete</button>
                </div>
                <div id="actorlist" class="col-lg-12 col-xs-12 col-sm-12 col-md-12 panel-body row">
                    <div class="row">

                        <%--   <div class="actor col-md-4 col-lg-4 well" name="actor">
                                <label class="control-label">First name:</label>
                                <input type="text" name="fname" placeholder="" class="input">
                                <label class="control-label">Seccond name:</label>
                                <input type="text" name="sname" placeholder="" class="input">
                                <label class="control-label">Role:</label>
                                <input type="text" name="role" placeholder="" class="input">
                                <label class="control-label">Profession:</label>
                               <input type="text" name="profession" placeholder="" class="input">
                                <label class="control-label">Birthday:</label>
                                <input type="date" name="birthday" value="2017-01-01" max="2017-01-01" min="1945-01-01">
                                <label class="control-label">Birth Place:</label>
                                <input type="text" name="birthplace" placeholder="" class="input">
                            </div>';  --%>


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

