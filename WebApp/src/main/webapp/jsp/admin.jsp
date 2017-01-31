<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="def" var="resourceBundle"/>

<%@ taglib prefix="slider" uri="custTag" %>

<html>
<head>
    <!--InternetExplorer link  -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta title="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-slider.css">


</head>


<body>
<%@ include file="/jsp/header.jsp" %>

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
                    <th>Gender:</th>
                    <th>Status:</th>
                    <th>Banned:</th>

                </tr>
                </thead>
                <tbody>

                <c:set var="i" value="0" scope="page" />

                <c:forEach var="user" items="${sessionScope.users}">

                        <tr>
                            <td><img src="${user.icon}" class="img-circle" alt="avatar" width="200" height="200"></td>
                            <td>${user.login}</td>
                            <td>${user.email}</td>
                            <td>
                                <label class="label label-info">${user.gender}</label>
                            </td>
                            <td>
                                <form name="changestatus" action="Controller" method="post">

                                    <input type="hidden" name="command" value="changestatus">
                                    <input type="hidden" name="userId" value="${i}">
                                    <input type="hidden" name="page" value="path.page.admin">

                                    <slider:custom-slider index="${i}"/>

                                    <input class="btn btn-default invisible" type="submit" name="button" value="user"/>
                                </form>
                            </td>

                            <td>
                                <form action="Controller" method="post">
                                    <input type="hidden" name="command" value="userbanned">
                                    <input type="hidden" name="userId" value="${i}">
                                    <input type="hidden" name="page" value="path.page.admin">
                                    <input class="btn btn-default invisible" type="submit" name="button" value="user"/>
                                    <button type="button"
                                            class="subForm ${user.banned ?'btn btn-danger': 'btn btn-success'}">Banned
                                    </button>
                                </form>
                            </td>
                        </tr>
                    <c:set var="i" value="${i + 1}" scope="page"/>
                </c:forEach>
                </tbody>
            </table>


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


</body>
</html>
