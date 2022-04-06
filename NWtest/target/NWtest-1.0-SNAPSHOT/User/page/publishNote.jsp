<%--
  Created by IntelliJ IDEA.
  User: TiNa
  Date: 2022/3/31
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="description" content="This is a testing demo page">
    <meta name="keywords" content="testing,html,demo">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>NW一轮考核</title>
    <link rel="stylesheet" type="text/css" href="http://localhost:8080/nw/User/css/home.css">
    <link rel="stylesheet" href="http://localhost:8080/nw/note/dist/css/bootstrap.min.css" type="text/css">
</head>
<body>
<div id="head">
    <div class="logo_title">
        <h1>小红薯的个人空间&nbsp;&nbsp;&nbsp;欢迎！ <h4 style="color: #4cae4c; ">${requestScope.username}</h4></h1>
        <h2>标记我的生活</h2>
        <p style="color: red">${sendMsg}</p>
    </div>
    <div class="navi">
        <ul>
            <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post">
                <input type="submit" value="首页" name="action" class="btn btn-success">
                <input type="hidden" value="${requestScope.username}" name="username">
                <input type="hidden" value="${requestScope.password}" name="password">
            </form>
            </li>
            <li><a href = "/nw/User/page/login.jsp" class="btn btn-success">退出登录</a></li>
        </ul>
    </div>
    <div class="clear"></div>
</div>
<form role="form" method="post" class="container" action="http://localhost:8080/nw/UserRoleServlet">
    <div class="form-group container" >
        <label for="head" >设置笔记标题</label>
        <input type="text" class="form-control" placeholder="标题不能为空哦~" name="title">
        <br>
    </div>
    <div class="form-group container" >
        <label for="head">填写笔记内容</label>
        <textarea class="form-control" rows="3" placeholder="不要什么都不写噢" name="main"></textarea>
    </div>
        <br>
    <div class="form-group container">
        <h4 STYLE="color: black" >选择发布的区域，一旦选择不能更改！不能不选哦！</h4>
        <div class="radio">
            <label>
                <input type="radio" name="zoomName"  value="游戏区" checked>游戏区
            </label>
        </div>
        <div class="radio">
            <label>
                <input type="radio" name="zoomName"  value="动漫区">动漫区
            </label>
        </div>
        <div class="radio">
            <label>
                <input type="radio" name="zoomName"  value="学习区" checked>学习区
            </label>
        </div>
        <div class="radio">
            <label>
                <input type="radio" name="zoomName"  value="科技区">科技区
            </label>
        </div>
        <div class="radio">
            <label>
                <input type="radio" name="zoomName"  value="美食区" checked>美食区
            </label>
        </div>
        <input type="hidden" name="username" value="${requestScope.username}">
        <input type="hidden" name="password" value="${requestScope.password}">
    </div>
        <input type="submit" value="确认发布" class="btn btn-success" name="action">
</form>


<script src="lib/jquery-2.1.1.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
