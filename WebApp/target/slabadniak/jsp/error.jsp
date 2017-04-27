<%@ page isELIgnored="false" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="span12">
            <div>
                <h1>Request ${pageContext.errorData.requestURI} is failed.</h1>
                <h3 class="error">Error ${pageContext.errorData.statusCode}</h3>
                <br/>
                <p>Your doing something wrong, either contact your webmaster or try again.</p>
                <p>Use your browsers <b>Back</b> button to navigate to the page you have prevously come from</p>
                <p><b>Or you could just press this button:</b></p>
                <form title="back" action="Controller" method="post">
                    <input type="hidden" name="command" value="empty">
                    <input type="hidden" name="page" value="path.page.main">
                    <input class="btn btn-primary" type="submit" name="Back" value="Back"/>
                </form>
            </div>

        </div>
    </div>
</div>

<script src="/js/bootstrap.min.js"></script>
</body>
</html>
