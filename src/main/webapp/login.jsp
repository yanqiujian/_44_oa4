<%--
  Created by IntelliJ IDEA.
  User: yangjw
  Date: 2018/3/30
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath = request.getContextPath(); %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="user/login" method="post">
    username:<input name="username" type="text"/>
    password:<input name="password" type="text"/>
    <input type="submit">
</form>
</body>
</html>
