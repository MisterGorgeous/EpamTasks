<%--
  Created by IntelliJ IDEA.
  User: Siarhei
  Date: 23.11.2016
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chose</title>
</head>
<body>
<h5>Chose parser.</h5>
<form name="Behavior" action="choseaction" method="POST">
   <!-- <input type="hidden" name="time" value=""/> -->
    <input type="submit" name="buttonDOM" value="DOM"/>
    <input type="submit" name="buttonSAX" value="SAX"/>
    <input type="submit" name="buttonStAX" value="StAX"/>
</form>
</body>
</html>
