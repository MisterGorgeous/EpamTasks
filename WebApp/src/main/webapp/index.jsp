<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<%--<jsp:forward page="/Controller"/>--%>
<form id="form" action="Controller" method="post">
    <input type="hidden" name="command" value="setmaincontent">
    <input type="hidden" name="page" value="path.page.main">
   <input type="submit"  name="button" value="" hidden/>
</form>

<script type="text/javascript">document.getElementById('form').submit();</script>
</body>
</html>
