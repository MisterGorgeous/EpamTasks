<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="def" var="resourceBundle"/>

<html>
<head>
    <title>Login</title>
    <meta title="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>


<body>

<%@ include file="/jsp/header.jsp" %>

<div class="container pagination-centered">


     <h1 class="page-header">Edit Profile</h1>
    <form action="Controller" method="post" class="form-horizontal" onsubmit="return checkPasswords()">
        <input type="hidden" name="command" value="signin">
        <input type="hidden" name="page" value="path.page.signin">

        <div class="form-group">
            <label class="col-md-3 control-label">Login:</label>
            <div class="col-md-5">
                <input class="form-control" name="login" type="text" placeholder="Only latin and numeric, 4 to 32 symbols."  pattern="^[A-Za-z]\w{4,32}$" required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-3 control-label">email:</label>
            <div class="col-lg-5">
                <input class="form-control"  type="text" name="email" pattern="^[\w.!#$%&’*+/=?^_`{|}~-]+@[\w-]+(?:\.[\w-]+)*$" required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Password:</label>
            <div class="col-md-5">
                <input id="password" placeholder="Must consist numeric, leters in low and up register,6 to 32 symbols." class="form-control" name="password" type="password"  pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,32}$" required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Confirm password:</label>
            <div class="col-md-5">
                <input id="confPassword" placeholder="Must consist numeric, leters in low and up register,6 to 32 symbols." class="form-control" name="confpassword" type="password"  pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,32}$" required>
            </div>
        </div>
        <div class="form-group">
            <label class=" col-md-3 control-label">Gender</label>
            <div class="col-md-5">
                <select name="gender" class="form-control input-xlarge" required>
                    <option>Male</option>
                    <option>Female</option>
                    <option>Other</option>
                </select>
            </div>
        </div>


<%-- <c:if test="${feedback.written}">
    <div class="center-block">
        <h3><span class="label label-warning ">${feedback.message}</span></h3>
    </div>
</c:if>

   <div class="form-group">
            <h3><span  id="infoLabel" class="label label-warning "></span></h3>
        </div>
        --%>

        <div class="space"></div>

        <div class="form-group">
            <label class="col-md-5 control-label"></label>
            <div class="col-md-5">
                <input class="btn btn-info" value="Sign in" type="submit">
            </div>
        </div>
    </form>

    <form title="back" action="Controller" method="post">
        <input type="hidden" name="command" value="empty">
        <input type="hidden" name="page" value="path.page.main">
        <input class="btn btn-primary" type="submit"  name="button" value="<fmt:message key="back" bundle="${resourceBundle}"/>" />
    </form>

</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="/js/custom.js"></script>
</body>
</html>
