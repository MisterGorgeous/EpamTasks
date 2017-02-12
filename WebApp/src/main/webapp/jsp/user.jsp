<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

        <h1 class="page-header"><fmt:message key="user.edit" bundle="${resourceBundle}"/></h1>

        <div class="row">

                <div class=" col-lg-12 col-md-12 col-sm-6 col-xs-12 ">
                    <div class="alert alert-info text-center">
                        <i class="fa fa-coffee"></i>
                        <strong>${sessionScope.user.status}</strong>
                    </div>
                </div>


                    <form class="col-lg-6 col-md-6 col-sm-12 col-xs-12" title="changeuser" action="Controller" method="post">
                        <input type="hidden" name="command" value="changeprofile">
                        <input type="hidden" name="page" value="path.page.main">

                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class="col-md-3 control-label"><fmt:message key="sigin.login" bundle="${resourceBundle}"/></label>
                            <div class="col-md-8">
                                <input name="login" class="form-control" pattern="^[A-Za-z][\w ]{4,32}$$" value="${fn:escapeXml(sessionScope.user.login)}" type="text">
                            </div>
                        </div>
                        <div class="space"></div>
                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class="col-lg-3 control-label"><fmt:message key="sigin.email" bundle="${resourceBundle}"/></label>
                            <div class="col-lg-8">
                                <input name="email" class="form-control" pattern="^[\w.!#$%&’*+/=?^_`{|}~-]+@[\w-]+(?:\.[\w-]+)*$" value="${fn:escapeXml(sessionScope.user.email)}" type="text">
                            </div>
                        </div>
                        <div class="space"></div>
                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class="col-md-3 control-label"><fmt:message key="header.password" bundle="${resourceBundle}"/></label>
                            <div class="col-md-8">
                                <input name="password" class="form-control" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,32}$" value=""
                                       type="password">
                            </div>
                        </div>
                        <div class="space"></div>
                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class="col-md-3 control-label"><fmt:message key="sigin.confirm" bundle="${resourceBundle}"/></label>
                            <div class="col-md-8">
                                <input name="confpassword" class="form-control" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,32}$" value=""
                                       type="password">
                            </div>
                        </div>
                        <div class="space"></div>
                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class=" col-md-3 control-label"><fmt:message key="main.genre" bundle="${resourceBundle}"/></label>
                            <div class="col-md-8">
                                <select name="gender" name="selectbasic" class="form-control input-xlarge">
                                    <option>${sessionScope.user.gender}</option>
                                    <option>Male</option>
                                    <option>Female</option>
                                    <option>Other</option>
                                </select>
                            </div>
                        </div>
                        <div class="space"></div>
                        <div class="form-group col-md-8 col-sm-6 col-xs-12">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-8">
                                <input class="btn btn-info" type="submit" title="button"
                                <%-- value="<fmt:message key="header.logoff" bundle="${resourceBundle}"/>"/>--%>
                                       value="<fmt:message key="user.save" bundle="${resourceBundle}"/>">
                            </div>
                        </div>
                    </form>

                    <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                        <div class="text-center">
                            <h6>Upload photo</h6>
                            <img src="${sessionScope.user.icon}" class="avatar img-circle img-thumbnail" alt="avatar">
                            <form  action="/UploadServlet" method="post" enctype="multipart/form-data">
                                <input class="addIcon" type="file" name="file" accept=".png,.jpg,.jpeg"  onchange="this.form.submit()" >
                                <input  type="submit" hidden />
                            </form>
                        </div>
                    </div>
        </div>

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