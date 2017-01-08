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

<c:if test="${param.command == 'users'}">


    <div class="container">
        <div class="container">
            <h1 class="page-header">User:</h1>
            <div class="row">


                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Icon:</th>
                        <th>Login:</th>
                        <th>Email:</th>
                        <th>Genre:</th>
                        <th>Status:</th>
                        <th>Banned:</th>

                    </tr>
                    </thead>
                    <tbody>

                    <c:set var="index" value="0" scope="page"/>

                   <%-- <c:forEach var="user" items="${users}"> --%>
                    <c:forEach var="i" begin="${sessionScope.currentUserPage * 20}" end="${sessionScope.currnetUserPage * 20 + 19}">
                        <tr>
                            <td><img src="${users.get(i).icon}" class="img-circle" alt="avatar"></td>
                            <td>${users.get(i).login}</td>
                            <td>${users.get(i).email}</td>
                            <td>
                                <label class="label label-info <c:choose>
                                                        <c:when test="${users.get(i).gender == 'male'}">
                                                           fa fa-mars
                                                        </c:when>
                                                        <c:when test="${users.get(i).status == 'female'}">
                                                            fa fa-venus
                                                        </c:when>
                                                        <c:otherwise>
                                                            fa fa-transgender-alt
                                                        </c:otherwise>
                                                    </c:choose>"></label>
                            </td>
                            <td>
                                <form name="changestatus" action="Controller" method="post">

                                    <input type="hidden" name="command" value="changestatus">
                                    <input type="hidden" name="userId" value="${index}">
                                    <input type="hidden" name="page" value="path.page.admin">
                                    <input onchange="$(this).closest('form').submit();" id="ex19" type="text"
                                           name="status"
                                           data-provide="slider" data-slider-ticks="[1, 2, 3]"
                                           data-slider-ticks-labels='["beginer", "fan", "expert"]' data-slider-min="1"
                                           data-slider-max="3" data-slider-step="1"
                                           data-slider-value="<c:choose>
                                                        <c:when test="${users.get(i).status == 'beginer'}">
                                                           1
                                                        </c:when>
                                                        <c:when test="${users.get(i).status == 'fan'}">
                                                            2
                                                        </c:when>
                                                        <c:otherwise>
                                                            3
                                                        </c:otherwise>
                                                    </c:choose>" data-slider-tooltip="hide"/>
                                    <input class="btn btn-default invisible" type="submit" name="button" value="user"/>
                                </form>
                            </td>

                            <td>
                                <form action="Controller" method="post">
                                    <input type="hidden" name="command" value="userbanned">
                                    <input type="hidden" name="userId" value="${index}">
                                    <input type="hidden" name="page" value="path.page.admin">
                                    <input class="btn btn-default invisible" type="submit" name="button" value="user"/>
                                    <button onclick="$(this).closest('form').submit();" type="button"
                                            class="${users.get(i).banned ?'btn btn-danger': 'btn btn-success'}">Banned
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <c:set var="index" value="${index + 1}" scope="page"/>
                    </c:forEach>
                    </tbody>
                </table>


            </div>
        </div>
    </div>
<div class="row center-block">
    <div class="col-xs-6 col-md-6 col-lg-6">
        <c:if test="${sessionScope.currentUserPage < sessionScope.usersSize}">
            <form title="next" action="Controller" method="post">
                <input type="hidden" name="command" value="pagination">
                <input type="hidden" name="page" value="path.page.admin">
                <input type="hidden" name="attribute" value="currentUserPage">
                <input type="hidden" name="action" value="next">
                <input class="btn btn-primary" type="submit" name="button" value="Next"/>
            </form>
        </c:if>
    </div>
    <div class="col-xs-6 col-md-6 col-lg-6">
        <c:if test="${sessionScope.currentUserPage > 0}">
            <form title="previous" action="Controller" method="post">
                <input type="hidden" name="command" value="pagination">
                <input type="hidden" name="page" value="path.page.admin">
                <input type="hidden" name="attribute" value="currentUserPage">
                <input type="hidden" name="action" value="previous">
                <input class="btn btn-primary" type="submit" name="button2" value="Previous"/>
            </form>
        </c:if>
    </div>
</c:if>


<c:if test="${param.command == 'allgenres'}">
    <div class="container">
        <div class="container">
            <h1 class="page-header">Edit Movie</h1>
            <div class="row">

                <form id="loadmovieicon" action="/UploadServlet" method="post" enctype="multipart/form-data">
                    <div class="control-group">
                        <div class="panel panel-primary control-label">
                            <div class="panel-heading">Icon:</div>
                            <div class="panel-body">
                                <input  type="file" name="file"  />
                                <input  type="submit"  hidden/>
                            </div>
                        </div>
                    </div>
                </form>


                <form id="addmovie" title="addmovie" action="Controller" method="post">
                    <input type="hidden" name="command" value="addmovie">
                    <input type="hidden" name="page" value="path.page.admin">

                    <div class="col-lg-6 col-xs-12 col-md-4">

                        <div class="control-group">
                            <label class="control-label" for="title">Title:</label>
                            <div class="controls">
                                <input type="text" id="title" name="title" placeholder="" class="input-xlarge">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="description">Description:</label>
                            <div class="controls">
                                <textarea type="text" id="description" name="description" placeholder=""
                                          class="input-xlarge" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="country">country:</label>
                            <div class="controls">
                                <input type="text" id="country" name="country" placeholder="" class="input-xlarge">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="movieYear">Year:</label>
                            <div class="controls">
                                <select id="movieYear"  name="movieYear">
                                    <c:forEach var="i" begin="1970" end="2017">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">rating:</label>
                            <div class="controls">
                                <input id="ex6" type="text" data-slider-min="0" data-slider-max="9.9"
                                       data-slider-step="0.1" data-slider-value="5" form="addmovie" name="rating"/>
                                <span id="ex6CurrentSliderValLabel">Rating: <span id="ex6SliderVal">5</span></span>
                            </div>
                        </div>


                        <input id="subMovie" class="btn btn-danger btn-lg" type="submit" name="button" value="Add"/>

                    </div>


                    <div class="col-lg-6 col-xs-12 col-sm-4 col-md-4 ">
                        <div class="panel panel-default">
                            <ul class="list-group">
                                <c:forEach var="genre" items="${sessionScope.genrelist}">
                                    <li class="list-group-item">
                                            ${genre}
                                        <div class="material-switch pull-right">
                                            <input form="addmovie" id="${genre}" name="${genre}" type="checkbox"/>
                                            <label for="${genre}" class="label-info"></label>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </form>

            </div>

        </div>
    </div>
</c:if>


<c:if test="${param.command != 'allgenres' && param.command != 'users' }">

    <div class="container">
        <h1 class="page-header">Add Actor:</h1>

        <form title="addactor" action="Controller" method="post">
            <input type="hidden" name="command" value="addactor">
            <input type="hidden" name="page" value="path.page.admin">

            <label class="control-label">Movie:</label>
            <input type="text" name="movie" placeholder="" class="input">

                <label class="control-label" for="addactor">Year:</label>
                    <select class="span2" name="year">
                        <c:forEach var="i" begin="1970" end="2017">
                            <option>${i}</option>
                        </c:forEach>
                    </select>

            <input  class="btn btn-danger btn-lg" type="submit" name="button" value="Add"/>
            <div class="col-lg-10 col-xs-12 col-sm-12 col-md-10 pagination-centered">
                <div class="panel panel-primary">
                    <div class="panel-heading text-center">
                        <button id="addactor" type="button" class="btn btn-primary">
                            Add
                        </button>
                        <button id="deleteactor" type="button" class="btn btn-primary">
                            Delete
                        </button>
                    </div>
                    <div id="actorlist" class="col-lg-12 col-xs-12 col-sm-12 col-md-12 panel-body row">
                        <div class="row">

                            <div class="actor col-md-4 col-lg-4 well" name="actor">
                                <label class="control-label">First name:</label>
                                <input type="text" name="fname" placeholder="" class="input">
                                <label class="control-label">Seccond name:</label>
                                <input type="text" name="sname" placeholder="" class="input">
                                <label class="control-label">Role:</label>
                                <input type="text" name="role" placeholder="" class="input">
                                <label class="control-label">Birthday:</label>
                                <input type="date" name="birthday" value="2017-01-01" max="2017-01-01" min="1945-01-01">
                                <label class="control-label">Birth Place:</label>
                                <input type="text" name="birthplace" placeholder="" class="input"></div>';


                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

</c:if>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"
      type="text/css"/>
<script src="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-slider.js"></script>
<script type="text/javascript">
    $("#ex6").slider();
    $("#ex6").on("slide", function (slideEvt) {
        $("#ex6SliderVal").text(slideEvt.value);
    });
</script>
<script type="text/javascript">
    var addB = document.getElementById('addactor');
    addB.addEventListener("click", addtag);
    var delB = document.getElementById('deleteactor');
    delB.addEventListener("click", deltag);
    var list = document.getElementById('actorlist');
    var subMovie = document.getElementById('subMovie');
    subMovie.addEventListener("click",submitForms );
    var loadMovieIcon = document.getElementById('loadmovieicon');

    function load() {
       $(loadMovieIcon).submit();
    }

    submitForms = function(){
        document.getElementById("loadmovieicon").submit();
        document.getElementById("addmovie").submit();
    }


    function addtag() {
      //  var tag = '<div class="actor col-md-4 col-lg-4 well" name="actor"> <label class="control-label">First name:</label> <input type="text" name="fname" placeholder="" class="input"> <label class="control-label">Seccond name:</label> <input type="text" name="sname" placeholder="" class="input"> <label class="control-label">Role:</label> <input type="text" name="role" placeholder="" class="input"> </div>';
        var tag = '<div class="actor col-md-4 col-lg-4 well" name="actor"> <label class="control-label">First name:</label> <input type="text" name="fname" placeholder="" class="input"> <label class="control-label">Seccond name:</label> <input type="text" name="sname" placeholder="" class="input"> <label class="control-label">Role:</label> <input type="text" name="role" placeholder="" class="input"> <label class="control-label">Birthday:</label><input type="date" name="birthday" value="2017-01-01" max="2017-01-01" min="1945-01-01"> <label class="control-label">Birth Place:</label> <input type="text" name="birthplace" placeholder="" class="input"></div>';
        $(list).append(tag);
    }
    function deltag() {
        $(list).empty();
    }
</script>

</body>
</html>
