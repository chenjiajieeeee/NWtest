<%--
  Created by IntelliJ IDEA.
  User: TiNa
  Date: 2022/3/28
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/note/dist/css/bootstrap.min.css">
</head>
<body background="http://localhost:8080/nw/User/img/1.jpeg">
<form  action="http://localhost:8080/nw/PageServlet" method="post">
    <ul class="breadcrumb " style="color: cornsilk;">
        <li class="active "><h2 style="color: crimson;">小红书</h2></li>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <li class="active "><h4 style="color: black;">标记我的生活</h4></li>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <li class="active"><h3 style="color: #67b168">欢迎你！${requestScope.username}</h3></li>
        &nbsp;&nbsp;
        <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post">
            <input type="submit" value="首页" name="action" class="btn btn-success">
            <input type="hidden" value="${requestScope.username}" name="username">
            <input type="hidden" value="${requestScope.password}" name="password">
        </form>
        </li>
        <li><a href="http://localhost:8080/nw/User/page/login.jsp" class="btn btn-success">退出登录</a></li>
    </ul>
</form>
<div style="padding: 100px 100px 10px;" class="container">
    <h1 style="color: red" align="center">修改个人信息</h1>
    <h4>个人昵称：${requestScope.username}</h4>
    <br>
    <h4>生日：</h4>
    <br>
    <h4>邮箱：</h4>
    <br>
    <h4>手机号码: </h4>
    <br>
    <form class="bs-example bs-example-form" role="form" method="post" action="http://localhost:8080/nw/UserRoleServlet">
        <p style="color: red">${requestScope.updateMsg}</p>
        <div class="input-group">
            <span class="input-group-addon">修改用户名：</span>
            <input type="text" class="form-control" placeholder="输入新的用户名称，不能为空！" name="newName">
            <input type="hidden" name="oldName" value="${requestScope.username}">
            <input type="hidden" name="password" value="${requestScope.password}">
        </div>
        <br>
        <input type="submit" name="action" value="确认更改用户名" class="btn btn-success"/>
        <br>
    </form>
        <br>
    <form class="bs-example bs-example-form" role="form" method="post" action="http://localhost:8080/nw/UserRoleServlet">
        <p style="color: red">${requestScope.errorMsg}</p>
        <div class="input-group">
            <span class="input-group-addon">输入旧密码：</span>
            <input type="password" class="form-control" placeholder="请输入旧密码" name="oldPassword" >
        </div>
        <br>
        <div class="input-group">
            <span class="input-group-addon">输入新的密码：</span>
            <input type="password" class="form-control" placeholder="请输入新的密码，不能为空！" name="newPassword">
        </div>
        <br>
        <input type="submit" name="action" value="确认更改密码" class="btn btn-success"/>
        <input type="hidden" name="username" value="${requestScope.username}">
        <input type="hidden" name="password" value="${requestScope.password}">
    </form>
</div>

</body>
</html>
