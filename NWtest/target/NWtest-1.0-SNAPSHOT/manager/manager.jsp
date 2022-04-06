<%--
  Created by IntelliJ IDEA.
  User: TiNa
  Date: 2022/3/30
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" type="text/css" href="http://localhost:8080/nw/User/css/login&register.css" />
</head>
<body>
<div class="login">
    <h3 align="center" style="color: royalblue">后台管理员界面</h3>
    <form action="http://localhost:8080/nw/ManagerServlet" method="post">
        <p style="color: red">${requestScope.errMsg}</p>
    <span>
      <input type="text" name="account" placeholder="管理员账号" required="required">
    </span>
        <label>
            <input type="password" name="password" placeholder="管理员密码" required="required">
        </label>
        <button type="submit" value="登录" name="action">登录</button>
    </form>
</div>
</body>
</html>
