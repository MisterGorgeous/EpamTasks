<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="def" var="resourceBundle"/>

<html>
<head>
    <title>Login</title>
    <meta title="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="/css/loginstyle.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>


<body>

<%@ include file="/jsp/header.jsp" %>

<form title="login" action="Controller" method="post">
    <input type="hidden" name="command" value="signin">
    <input type="hidden" name="page" value="path.page.signin">
    <fieldset id="form">
        <legend>Sign In:</legend>

        <div>
            <strong>Login :</strong>
            <input title="login" class="textField" maxlength="25" size="40" value="" pattern="[A-Za-z]\w{4,}"
                   required title="Username should only contain lowercase letters. e.g. john">
        </div>

        <div><strong>Password :</strong>
            <input id="pass1" class="textField" type="password" maxlength="25" size="40" title="pass"
                   pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$" required>
        </div>

        <div>
            <strong>Confirm password:</strong>
            <input id="pass2" class="textField" type="password" maxlength="25" size="40" title="confpass"
                   pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$" required>
        </div>

        <div>
            <strong>email :</strong>
            <input id="email" class="textField" type="email" title="email"
                   pattern="^[\w.!#$%&’*+/=?^_`{|}~-]+@[\w-]+(?:\.[\w-]+)*$" required>
            <input id="add" type="button" title="add" value="Another email">
        </div>

        <div id="pforemail" hidden>
            <strong>email2 :</strong>
            <input id="email2" type="email" title="email2" pattern="^[\w.!#$%&’*+/=?^_`{|}~-]+@[\w-]+(?:\.[\w-]+)*$">
            <input id="delete" type="button" title="delete" value="Delete">
        </div>

        <div>
            <strong>Age :</strong>
            <input type="number" class="textField" size="1" title="age" min="7" max="120" value="">
        </div>

        <div>
            <input id="submit" type="submit" value="Sign in">
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

<script src="/js/email.js"></script>
</body>
</html>
