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
                <td>${user.status}</td>
                <td>
                    <form  action="Controller" method="post">
                        <input type="hidden" name="command" value="userbanned">
                        <input type="hidden" name="userId" value="${index}">
                        <input type="hidden" name="page" value="path.page.admin">
                        <input class="btn btn-default invisible" type="submit" name="button" value="user"/>
                        <label class="btn btn-warning active">
                            <input onchange="$(this).closest('form').submit();" type="radio" name="radio" id="option2" autocomplete="off">
                            <span class="glyphicon glyphicon-ok"></span>
                        </label>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
