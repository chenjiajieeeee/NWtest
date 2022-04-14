<%@page contentType="text/html;charset=utf-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NW一轮考核</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/nw/User/css/login&register.css" />
</head>
<body>
<div class="login">
  <h1>小红书</h1>
  <form action="http://localhost:8080/nw/user/login" method="post">
    <span>
             <p style="color: red">${requestScope.updateMsg}</p>
              <p style="color: red">${requestScope.registerMsg}</p>
      <input type="text" name="username" placeholder="输入用户名" required="required" value="${requestScope.username}">
             <p style="color: red">${requestScope.msg}</p>
    </span>
    <label>
      <input type="password" name="password" placeholder="输入密码" required="required">
    </label>
    <button type="submit">登录</button>
  <a href="http://localhost:8080/nw/User/page/register.jsp">还没有账号？立即注册</a>
  </form>
</div>
</body>
</html>