<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
</head>


<body>

<%@ include file="/jsp/header.jsp" %>


<div class="row">
    <div class="col-xs-6 col-md-3">
        <div>
            <h3><span class="label label-info">${chosenMovie.getTitle()}</span></h3>
        </div>
        <div>
            <img src="${chosenMovie.getIcon()}" alt="..." width="300" height="600">
        </div>

        <c:if test="${userStatus == 'GUEST'}">
            <form class="navbar-form navbar-left" title="login" action="Controller" method="post">
                <input type="hidden" name="command" value="">
                <input type="hidden" name="page" value="path.page.login">
                <input type="submit" name="button" class="btn btn-default" value="Rate"/>
            </form>
        </c:if>


           <%-- <div class="btn-group" role="group" aria-label="...">
                <button type="button" class="btn btn-default">1</button>
                <button type="button" class="btn btn-default">2</button>
                <button type="button" class="btn btn-default">3</button>
                <button type="button" class="btn btn-default">4</button>
                <button type="button" class="btn btn-default">5</button>
                <button type="button" class="btn btn-default">6</button>
                <button type="button" class="btn btn-default">7</button>
                <button type="button" class="btn btn-default">8</button>
                <button type="button" class="btn btn-default">9</button>
            </div>--%>





        <c:if test="${userStatus != 'GUEST' }">

            <form id="comment" class="navbar-form navbar-left" title="login" action="Controller" method="post">

                <input type="hidden" name="command" value="comment">
                <input type="hidden" name="page" value="path.page.movie">

                <input id="ex6" type="text" data-slider-min="0" data-slider-max="9.9" data-slider-step="0.1" data-slider-value="5" form="comment" name="rating"/>
                <span id="ex6CurrentSliderValLabel">Rating: <span id="ex6SliderVal">5</span></span>

                <label for="comment">Your comment:</label>
                <textarea rows="4" cols="35" name="commentText" form="comment"></textarea>
                <input type="submit" name="button" class="btn btn-default" value="Submit"/>
            </form>
        </c:if>

    </div>

    <div class="col-xs-6 col-md-3">
        <div class="panel panel-primary">
            <div class="panel-heading">Year:</div>
            <div class="panel-body">
                ${chosenMovie.getYear()}
            </div>
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading">Country:</div>
            <div class="panel-body">
                ${chosenMovie.getCountry()}
            </div>
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading">Description:</div>
            <div class="panel-body">
                ${chosenMovie.getDescription()}
            </div>
        </div>
    </div>


    <div class="col-xs-6 col-md-3">
        <div class="panel panel-primary">
            <div class="panel-heading">Actors:</div>
            <div class="panel-body">

                <c:forEach var="actor" items="${actors}">
                    <ul>
                        <li>${actor.firstName} ${actor.seccondName}</li>
                    </ul>
                </c:forEach>


            </div>
        </div>
        <div class="panel panel-primary">
            <div class="panel-heading">Genre:</div>
            <div class="panel-body">

        <c:forEach var="genre" items="${genres}">
            <ul>
                <li>${genre}</li>
            </ul>
        </c:forEach>

            </div>
        </div>

        <div>
            <h3><span class="label label-danger">${chosenMovie.getRating()}</span></h3>
        </div>


        <div class="panel panel-primary">
            <div class="panel-heading">Comemnts:</div>
            <div class="panel-body">

            <div class="actionBox">
                <ul class="commentList">


<c:forEach var="assess" items="${assessments}">
                    <li>
                        <div class="commentText">
                            <p class="text-success">${assess.user}</p>
                            <p class="text-info">${assess.comment}</p>
                            <span class="date sub-text" >${assess.date}</span> <%--class="date sub-text"--%>
                            <span class="text-danger">${assess.rating}</span>
                        </div>
                    </li>
</c:forEach>

                </ul>
            </div>
        </div>
        </div>

    </div>

</div>





<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-slider.js"></script>
<script type="text/javascript">
    $("#ex6").slider();
    $("#ex6").on("slide", function(slideEvt) {
        $("#ex6SliderVal").text(slideEvt.value);
    });
</script>
</body>

</html>
