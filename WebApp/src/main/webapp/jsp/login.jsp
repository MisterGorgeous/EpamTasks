<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="def" var="resourceBundle"/>

<html>
<head>
    <title>Login</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta title="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">

</head>



<body>

<%@ include file="/jsp/header.jsp" %>

<form title="login" action="Controller" method="post">
    <input type="hidden" name="command" value="login">
    <input type="hidden" name="page" value="path.page.main">
    <fieldset id="form">
        <legend>Log In:</legend>

        <div>
            <strong>Login :</strong>
            <input name="login" class="textField" maxlength="25" size="40" value="" pattern="[A-Za-z]\w{4,}"
                   required name="Username should only contain lowercase letters. e.g. john">
        </div>

        <div><strong>Password :</strong>
            <input id="pass1" class="textField" type="password" maxlength="25" size="40" name="pass"
                   pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$" required>
        </div>

        <div>
            <input id="submit" type="submit" value="Log on">
        </div>

    </fieldset>


</form>

<div>
    <form title="back" action="Controller" method="post">
        <input type="hidden" name="command" value="cross">
        <input type="hidden" name="page" value="path.page.main">
        <input type="submit"  name="button" value="<fmt:message key="back" bundle="${resourceBundle}"/>" /> />
    </form>
</div>




<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="/js/bootstrap-slider.js"></script>
</body>
</html>
