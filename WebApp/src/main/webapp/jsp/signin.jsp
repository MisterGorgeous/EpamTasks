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
    <form class="form-horizontal" role="form">

        <div class="form-group">
            <label class="col-md-3 control-label">Login:</label>
            <div class="col-md-8">
                <input class="form-control" name="login" type="text"  pattern="[A-Za-z]\w{4,}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-3 control-label">email:</label>
            <div class="col-lg-8">
                <input class="form-control"  type="text" name="email">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Password:</label>
            <div class="col-md-8">
                <input class="form-control" name="password" type="password">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Confirm password:</label>
            <div class="col-md-8">
                <input class="form-control" name="password2" type="password">
            </div>
        </div>
        <div class="form-group">
            <label class=" col-md-3 control-label">Gender</label>
            <div class="col-md-8">
                <select name="gender" class="form-control input-xlarge">
                    <option>Select</option>
                    <option>Male</option>
                    <option>Female</option>
                    <option>Other</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label"></label>
            <div class="col-md-8">
                <input class="btn btn-primary" value="Save Changes" type="button">
            </div>
        </div>
    </form>

    <form title="back" action="Controller" method="post">
        <input type="hidden" name="command" value="cross">
        <input type="hidden" name="page" value="path.page.main">
        <input class="btn btn-primary" type="submit"  name="button" value="<fmt:message key="back" bundle="${resourceBundle}"/>" />
    </form>

</div>

<link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"
      type="text/css"/>
<script src="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="/js/email.js"></script>
</body>
</html>
