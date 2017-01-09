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

<div class="container">
    <div class="container">
        <h1 class="page-header">Edit Profile</h1>
        <div class="row">
            <form class="navbar-form navbar-right" title="changeuser" action="Controller" method="post">
                <input type="hidden" name="command" value="changeprofile">
                <input type="hidden" name="page" value="path.page.main">

                <!-- left column -->
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="text-center">
                        <form id="loadmovieicon" action="/UploadServlet" method="post" enctype="multipart/form-data">
                            <img src="${sessionScope.user.icon}" class="avatar img-circle img-thumbnail" alt="avatar">
                            <h6>Upload photo</h6>
                            <input type="file" name="file" class="text-center center-block well well-sm">
                            <input type="submit"/>
                        </form>
                    </div>
                </div>


                <!-- edit form column -->
                <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
                    <div class="alert alert-info text-center">
                        <i class="fa fa-coffee"></i>
                        <strong>${sessionScope.user.status}</strong>
                    </div>
                    <h3>Personal info</h3>
                    <form class="form-horizontal" role="form">

                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class="col-md-3 control-label">Login:</label>
                            <div class="col-md-8">
                                <input name="login" class="form-control" value="${sessionScope.user.login}" type="text">
                            </div>
                        </div>
                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class="col-lg-3 control-label">email:</label>
                            <div class="col-lg-8">
                                <input name="email" class="form-control" value="${sessionScope.user.email}" type="text">
                            </div>
                        </div>

                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class="col-md-3 control-label">Password:</label>
                            <div class="col-md-8">
                                <input name="password" class="form-control" value="${sessionScope.user.password}"
                                       type="password">
                            </div>
                        </div>

                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class="col-md-3 control-label">Confirm password:</label>
                            <div class="col-md-8">
                                <input name="password" class="form-control" value="${sessionScope.user.password}"
                                       type="password">
                            </div>
                        </div>
                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class=" col-md-3 control-label">Gender</label>
                            <div class="col-md-8">
                                <select name="gender" name="selectbasic" class="form-control input-xlarge">
                                    <option>${sessionScope.user.gender}</option>
                                    <option>Male</option>
                                    <option>Female</option>
                                    <option>Other</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-8">
                                <input class="btn btn-primary" type="submit" title="button"
                                <%-- value="<fmt:message key="header.logoff" bundle="${resourceBundle}"/>"/>--%>
                                       value="Save">
                            </div>
                        </div>
                    </form>
                </div>
            </form>
        </div>


    </div>
</div>


<link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"
      type="text/css"/>
<script src="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>