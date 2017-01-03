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

<div class="container" >
    <div class="container">
        <h1 class="page-header">Edit Profile</h1>
        <div class="row">
            <!-- left column -->
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="text-center">
                    <img src="http://lorempixel.com/200/200/people/9/" class="avatar img-circle img-thumbnail" alt="avatar">
                    <h6>Upload photo</h6>
                    <input type="file" class="text-center center-block well well-sm">
                </div>
            </div>
            <!-- edit form column -->
            <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
                <div class="alert alert-info alert-dismissable text-center">
                    <i class="fa fa-coffee"></i>
                    <strong>Beginer</strong>
                </div>
                <h3>Personal info</h3>
                <form class="form-horizontal" role="form">

                    <div class="form-group">
                        <label class="col-md-3 control-label">Login:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="janeuser" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">email:</label>
                        <div class="col-lg-8">
                            <input class="form-control" value="janesemail@gmail.com" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">Password:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="11111122333" type="password">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">Confirm password:</label>
                        <div class="col-md-8">
                            <input class="form-control" value="11111122333" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" col-md-3 control-label">Gender</label>
                        <div class="col-md-8">
                            <select name="selectbasic" class="form-control input-xlarge">
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