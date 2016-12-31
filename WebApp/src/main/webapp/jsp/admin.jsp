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
                <th>Login:</th>
                <th>email:</th>
                <th>status:</th>
                <th>banned:</th>
            </tr>
            </thead>
            <tbody>

            <c:set var="index" value="0" scope="page"/>

            <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.login}</td>
                <td>${user.email}</td>
                <td>
                    <form  class="navbar-form navbar-left" name="changestatus" action="Controller" method="post">

                        <input type="hidden" name="command" value="changestatus">
                        <input type="hidden" name="userId" value="${index}">
                        <input type="hidden" name="page" value="path.page.admin">
                        <input onchange="$(this).closest('form').submit();"  id="ex19" type="text" name="status"  data-provide="slider" data-slider-ticks="[1, 2, 3]" data-slider-ticks-labels='["beginer", "fan", "expert"]' data-slider-min="1" data-slider-max="3" data-slider-step="1"
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
                    <form  action="Controller" method="post">
                        <input type="hidden" name="command" value="userbanned">
                        <input type="hidden" name="userId" value="${index}">
                        <input type="hidden" name="page" value="path.page.admin">
                        <input class="btn btn-default invisible" type="submit" name="button" value="user"/>
                        <button onclick="$(this).closest('form').submit();" type="button" class="${user.banned ?'btn btn-danger': 'btn btn-success'}">Banned</button>
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

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="/js/bootstrap-slider.js"></script>
</body>
</html>
