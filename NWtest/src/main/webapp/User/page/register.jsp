<%@page contentType="text/html;charset=utf-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NW一轮考核</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/nw/User/css/login&register.css"/>
</head>
<body>
<div class="login">
  <h1>小红书</h1>
  <form action="http://localhost:8080/nw/UserRegister" method="post">
    <span>
    <input type="text" name="username" placeholder="起一个好听的名字" required="required">
      <p style="color: red">${requestScope.msg}</p>
    </span>
    <input type="password" name="password" placeholder="输入密码" required="required">
  <button type="submit">注册</button>
  <a href="/nw/User/page/login.jsp">已有账号？点击登录</a>
  </form>
</div>
</body>
</html>