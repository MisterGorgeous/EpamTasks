<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="def" var="resourceBundle"/>

<html>
<head>
    <meta charset="utf-8">
    <!--InternetExplorer link  -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta title="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-slider.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>


<body>

<%@ include file="/jsp/header.jsp" %>

<h1 class="page-header">
   ${fn:escapeXml(chosenMovie.getTitle())} <span class="label label-danger col-lg-offset-6">   ${fn:escapeXml(chosenMovie.getRating())}</span>
</h1>

<div class="row">
    <div class="col-xs-6 col-md-4  col-lg-4">
        <div>
            <img src="${fn:escapeXml(chosenMovie.getIcon())}" alt="..." class="movImg">
        </div>

        <c:if test="${userStatus == 'GUEST'}">
            <form class="navbar-form navbar-left" title="login" action="Controller" method="post">
                <input type="hidden" name="command" value="writefeedback">
                <input type="hidden" name="page" value="path.page.movie">
                <input type="hidden" name="text" value="feedback.rate">
                <input type="submit" name="button" class="btn btn-info" value="<fmt:message key="movie.rate" bundle="${resourceBundle}"/>"/>
            </form>
        </c:if>
        <c:if test="${userStatus != 'GUEST' }">

            <form id="comment" class="navbar-form navbar-left" title="login" action="Controller" method="post">

                <input type="hidden" name="command" value="comment">
                <input type="hidden" name="command" value="showcomments">
                <input type="hidden" name="page" value="path.page.movie">

                <label class="col-sm-5 col-xs-5 col-md-7 col-lg-7 control-label" for="comment"><fmt:message key="movie.yourmark" bundle="${resourceBundle}"/></label>
                <div class="space"></div>
                <input class="col-sm-5 col-xs-5 col-md-7 col-lg-7 control-label" id="ex6" type="text" data-slider-min="0" data-slider-max="9.9" data-slider-step="0.1"
                       data-slider-value="5" form="comment" name="rating"/>
                <span  id="ex6CurrentSliderValLabel"><fmt:message key="movie.rating" bundle="${resourceBundle}"/> <span id="ex6SliderVal">5</span></span>
                <div class="space"></div>
                <label class="col-sm-5 col-xs-5 col-md-7 col-lg-7 control-label" for="comment"><fmt:message key="movie.yourcomment" bundle="${resourceBundle}"/></label>
                <div class="space"></div>
                <textarea class="col-sm-5 col-xs-5 col-md-7 col-lg-7 control-label" rows="4" cols="35" name="commentText" form="comment"></textarea>
                <div class="space"></div>
                <div class="space"></div>
                <input  type="submit" name="button" class="btn btn-info col-xs-6 col-md-7  col-lg-7 " value="<fmt:message key="movie.rate" bundle="${resourceBundle}"/>"/>
            </form>
        </c:if>



    </div>

    <div class="col-xs-6 col-md-4">
        <div class="panel panel-primary">
            <div class="panel-heading"><fmt:message key="movie.year" bundle="${resourceBundle}"/></div>
            <div class="panel-body">
                ${fn:escapeXml(chosenMovie.getYear())}
            </div>
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading"><fmt:message key="movie.country" bundle="${resourceBundle}"/></div>
            <div class="panel-body">
                ${fn:escapeXml(chosenMovie.getCountry())}
            </div>
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading"><fmt:message key="movie.description" bundle="${resourceBundle}"/></div>
            <div class="panel-body">
                ${fn:escapeXml(chosenMovie.getDescription())}
            </div>
        </div>
    </div>


    <div class="col-xs-6 col-md-4 col-lg-4">
        <div class="panel panel-primary">
            <div class="panel-heading"><fmt:message key="main.actor" bundle="${resourceBundle}"/></div>
            <div class="panel-body">

                <c:forEach var="actor" items="${actors}">
                    <ul>
                        <li>${fn:escapeXml(actor.firstName)} ${fn:escapeXml(actor.seccondName)} as  <span class="text-success sub-text">${fn:escapeXml(actor.role)}</span> </li>
                    </ul>
                </c:forEach>


            </div>
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading"><fmt:message key="main.genre" bundle="${resourceBundle}"/></div>
            <div class="panel-body">

                <c:forEach var="genre" items="${genres}">
                    <ul>
                        <li>${genre}</li>
                    </ul>
                </c:forEach>

            </div>
        </div>



        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="movie.comments" bundle="${resourceBundle}"/></div>
            <div class="panel-body">

                <div class="actionBox">
                    <ul class="commentList">


                        <c:forEach var="assess" items="${assessments}">
                            <li>
                                <div class="commentText">
                                    <p class="text-success">${fn:escapeXml(assess.user)}</p>
                                    <h4><p class="text-info">${fn:escapeXml(assess.comment)}</p></h4>
                                    <span class="date sub-text">${fn:escapeXml(assess.date)}</span> <%--class="date sub-text"--%>
                                    <span class="text-danger">${fn:escapeXml(assess.rating)}</span>
                                </div>
                            </li>
                        </c:forEach>

                    </ul>
                </div>
            </div>
        </div>

    </div>


</div>

<form title="back" action="Controller" method="post">
    <input type="hidden" name="command" value="empty">
    <input type="hidden" name="page" value="path.page.main">
    <input class="btn btn-primary" type="submit"  name="button" value="<fmt:message key="back" bundle="${resourceBundle}"/>" />
</form>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-slider.js"></script>
<script src="/js/custom.js"></script>

<script type="text/javascript">
    $("#ex6").slider();
    $("#ex6").on("slide", function (slideEvt) {
        $("#ex6SliderVal").text(slideEvt.value);
    });
</script>


</body>
</html>
