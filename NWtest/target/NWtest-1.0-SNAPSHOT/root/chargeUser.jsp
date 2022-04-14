<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/notebook/dist/css/bootstrap.min.css" type="text/css">
</head>
<body>
<ul class="breadcrumb " style="color: cornsilk;">
    <li class="active "><h2 style="color: crimson;">小红书</h2></li>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <li class="active "><h4 style="color: black;">标记我的生活</h4></li>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <li class="active"><h3 style="color: #67b168">欢迎你！${requestScope.username}</h3></li>
    &nbsp;&nbsp;
    <li><a href="http://localhost:8080/nw/User/page/login.jsp" class="btn btn-warning">退出登录</a></li>
    <li><a href="http://localhost:8080/nw/user/login?username=${requestScope.username}&password=${requestScope.password}&root=${requestScope.root}" class="btn btn-warning">返回首页</a></li>
</ul>
<p style="color: red" class="container">${requestScope.Msg}</p>
<div class="container">
<table class="table table-striped ">
    <caption>用户状态</caption>
    <thead>
    <tr>
        <th>用户名</th>
        <th>用户id</th>
        <th>用户状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.userMessage}" var="user">
    <tr>
        <td>${user.username}</td>
        <td>${user.userId}</td>
        <td><c:if test="${user.userStatus.equals('0')}">
            用户被禁用
        </c:if>
            <c:if test="${user.userStatus.equals('1')}">
                用户状态正常
            </c:if>
        </td>
        <td><form action="http://localhost:8080/nw/manager/setUserStatus" method="post">
            <input type="submit" name="update" value="禁用/解封该用户" class="btn btn-warning">
            <input type="hidden" name="username" value="${requestScope.username}">
            <input type="hidden" name="password" value="${requestScope.password}">
            <input type="hidden" name="root" value="${requestScope.root}">
            <input type="hidden" name="userId" value="${user.userId}">
            <input type="hidden" name="userStatus" value="${user.userStatus}">
        </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>