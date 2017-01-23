<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="def" var="resourceBundle"/>

<%@ taglib prefix="myshortname" uri="mycompany" %>

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

                <%-- <c:forEach var="user" items="${users}"> --%>
                <c:forEach var="i" begin="${sessionScope.currentUserPage * 20}" end="${sessionScope.currnetUserPage * 20 + 19}">
                    <c:if test="${i < sessionScope.usersSize}">
                        <tr>
                            <td><img src="${sessionScope.users.get(i).icon}" class="img-circle" alt="avatar"></td>
                            <td>${sessionScope.users.get(i).login}</td>
                            <td>${sessionScope.users.get(i).email}</td>
                            <td>
                                <label class="label label-info">${sessionScope.users.get(i).gender}</label>
                            </td>
                            <td>
                                <form name="changestatus" action="Controller" method="post">

                                    <input type="hidden" name="command" value="changestatus">
                                    <input type="hidden" name="userId" value="${i}">
                                    <input type="hidden" name="page" value="path.page.admin">

                                    <myshortname:custom-slider index="${i}"  />

                                    <input class="btn btn-default invisible" type="submit" name="button" value="user"/>
                                </form>
                            </td>

                            <td>
                                <form action="Controller" method="post">
                                    <input type="hidden" name="command" value="userbanned">
                                    <input type="hidden" name="userId" value="${i}">
                                    <input type="hidden" name="page" value="path.page.admin">
                                    <input class="btn btn-default invisible" type="submit" name="button" value="user"/>
                                    <button onclick="$(this).closest('form').submit();" type="button"
                                            class="subForm ${sessionScope.users.get(i).banned ?'btn btn-danger': 'btn btn-success'}">Banned
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>


        </div>
    </div>
</div>


<div class="row center-block">
    <div class="col-xs-6 col-md-6 col-lg-6">
        <form title="previous" action="Controller" method="post">
            <input type="hidden" name="command" value="pagination">
            <input type="hidden" name="page" value="path.page.admin">
            <input type="hidden" name="attribute" value="currentUserPage">
            <input type="hidden" name="action" value="previous">
            <input class="btn btn-primary" type="submit" name="button2" value="Previous"
                    <c:if test="${sessionScope.currentUserPage == 0}"> disabled </c:if>/>
        </form>
    </div>
    <div class="col-xs-6 col-md-6 col-lg-6">
        <form title="next" action="Controller" method="post">
            <input type="hidden" name="command" value="pagination">
            <input type="hidden" name="page" value="path.page.admin">
            <input type="hidden" name="attribute" value="currentUserPage">
            <input type="hidden" name="action" value="next">
            <input class="btn btn-primary" type="submit" name="button" value="Next"
                    <c:if test="${sessionScope.currentUserPage == sessionScope.userPages}"> disabled </c:if>/>
        </form>
    </div>

</div>

<form title="back" action="Controller" method="post">
    <input type="hidden" name="command" value="">
    <input type="hidden" name="page" value="path.page.main">
    <input class="btn btn-primary" type="submit"  name="button" value="<fmt:message key="back" bundle="${resourceBundle}"/>" />
</form>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap-slider.js"></script>


<script type="text/javascript">

    $(".subForm").change(function(){
        $(this).closest('form').submit();
    });

</script>

</body>
</html>
