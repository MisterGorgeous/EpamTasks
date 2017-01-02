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

    <div class="panel panel-primary">
        <div class="panel-heading">Users:</div>
        <div class="panel-body">


            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Icon:</th>
                    <th>Login:</th>
                    <th>email:</th>
                    <th>Genre:</th>
                    <th>Status:</th>
                    <th>Banned:</th>

                </tr>
                </thead>
                <tbody>

                <c:set var="index" value="0" scope="page"/>

                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.login}</td>
                        <td>${user.email}</td>
                        <td>
                            <form name="changestatus" action="Controller" method="post">

                                <input type="hidden" name="command" value="changestatus">
                                <input type="hidden" name="userId" value="${index}">
                                <input type="hidden" name="page" value="path.page.admin">
                                <input onchange="$(this).closest('form').submit();" id="ex19" type="text" name="status"
                                       data-provide="slider" data-slider-ticks="[1, 2, 3]"
                                       data-slider-ticks-labels='["beginer", "fan", "expert"]' data-slider-min="1"
                                       data-slider-max="3" data-slider-step="1"
                                       data-slider-value="<c:choose>
                                                        <c:when test="${user.status == 'beginer'}">
                                                           1
                                                        </c:when>
                                                        <c:when test="${user.status == 'fan'}">
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
                                        class="${user.banned ?'btn btn-danger': 'btn btn-success'}">Banned
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
</c:if>

<c:if test="${param.command == 'allgenres'}">

    <div class="panel panel-primary">
        <div class="panel-heading">Movie:</div>
        <div class="panel-body">


            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Title:</th>
                    <th>Description:</th>
                    <th>Country:</th>
                    <th>Year:</th>
                    <th>Icon:</th>
                    <th>Rating:</th>
                </tr>
                </thead>
                <form  id="addmovie" class="navbar-form navbar-left" title="addmovie"  action="Controller" method="post">
                    <input type="hidden" name="command" value="addmovie">
                    <input type="hidden" name="page" value="path.page.admin">
                    <input class="btn btn-default" type="submit" name="button" value="Add"/>

                    <tbody>
                    <tr>
                        <td><textarea class="form-control" rows="1" name="title"></textarea></td>
                        <td><textarea class="form-control" rows="10" name="description"></textarea></td>
                        <td><textarea class="form-control" rows="1" name="country"></textarea></td>
                        <td><textarea class="form-control" rows="1" name="year"></textarea></td>
                        <td>
                            <form method="post" action="Controller" enctype="multipart/form-data">
                                <input type="hidden" name="command" value="upload">
                                <input type="file" name="file"/>
                                <input type="submit">load</input>

                            </form></td>
                        <td><textarea class="form-control" rows="1" name="rating">5.0</textarea></td>
                    </tr>
                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
                                <div class="panel panel-default">
                                    <tr>
                                        <td>
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
                                        </td>
                                    </tr>
                                </div>
                            </div>
                        </div>
                    </div>
                    </tbody>
                </form>
            </table>


        </div>
    </div>
</c:if>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"
      type="text/css"/>
<script src="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

<script src="js/bootstrap.min.js"></script>
<script src="/js/bootstrap-slider.js"></script>
</body>
</html>
