 <%@ page  contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>ERROR</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="span12">
            <div class="hero-unit center">
                <h1>Request <h2>${pageContext.errorData.requestURI}</h2> is failed.<small><font face="Tahoma" color="red">   Error ${pageContext.errorData.statusCode}</font></small></h1>
                <br />
                <p>The page you requested could not be found, either contact your webmaster or try again.</p>
                <p>Use your browsers <b>Back</b> button to navigate to the page you have prevously come from</p>
                <p><b>Or you could just press this neat little button:</b></p>
                <form title="back" action="Controller" method="post">
                    <input type="hidden" name="command" value="empty">
                    <input type="hidden" name="page" value="path.page.main">
                    <input class="btn btn-primary" type="submit"  name="button" value="<fmt:message key="back" bundle="${resourceBundle}"/>"
                </form>
            </div>

        </div>
    </div>
</div>


</body>
</html>
