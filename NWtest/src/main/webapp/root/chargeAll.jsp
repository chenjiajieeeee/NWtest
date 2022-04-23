<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/notebook/dist/css/bootstrap.min.css" type="text/css">
<<<<<<< HEAD
=======
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
>>>>>>> 983e94e (ninth)
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
<<<<<<< HEAD
=======
<p class="h4 container" style="color: red">${requestScope.changeMsg}</p>
>>>>>>> 983e94e (ninth)
<div class="container">
    <table class="table table-striped ">
        <caption>游戏区管理员状态</caption>
        <thead>
        <tr>
            <th>用户名</th>
            <th>管理员id</th>
            <th>被申述次数</th>
            <th>操作</th>
<<<<<<< HEAD
=======
            <th>转变身份</th>
>>>>>>> 983e94e (ninth)
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users4}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.id}</td>
                <td>${user.appealCount}次</td>
                <td><form action="http://localhost:8080/nw/manager/resetManager" method="post">
                    <input type="submit" name="update" value="解除该用户管理员身份" class="btn btn-warning">
                    <input type="hidden" name="username" value="${requestScope.username}">
                    <input type="hidden" name="password" value="${requestScope.password}">
                    <input type="hidden" name="root" value="${requestScope.root}">
                    <input type="hidden" name="userId" value="${user.id}">
                </form>
                </td>
<<<<<<< HEAD
=======
                <td>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">选择分区
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=游戏区">游戏区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=动漫区">动漫区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=学习区">学习区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=科技区">科技区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=美食区">美食区</a></li>
                        </ul>
                    </div>
                </td>
>>>>>>> 983e94e (ninth)
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <table class="table table-striped ">
        <caption>动漫区管理员状态</caption>
        <thead>
        <tr>
            <th>用户名</th>
            <th>管理员id</th>
            <th>被申述次数</th>
            <th>操作</th>
<<<<<<< HEAD
=======
            <th>转变身份</th>
>>>>>>> 983e94e (ninth)
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users3}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.id}</td>
                <td>${user.appealCount}次</td>
                <td><form action="http://localhost:8080/nw/manager/resetManager" method="post">
                    <input type="submit" name="update" value="解除该用户管理员身份" class="btn btn-warning">
                    <input type="hidden" name="username" value="${requestScope.username}">
                    <input type="hidden" name="password" value="${requestScope.password}">
                    <input type="hidden" name="root" value="${requestScope.root}">
                    <input type="hidden" name="userId" value="${user.id}">
                </form>
                </td>
<<<<<<< HEAD
=======
                <td>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">选择分区
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=游戏区">游戏区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=动漫区">动漫区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=学习区">学习区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=科技区">科技区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=美食区">美食区</a></li>
                        </ul>
                    </div>
                </td>
>>>>>>> 983e94e (ninth)
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table table-striped ">
        <caption>学习区管理员状态</caption>
        <thead>
        <tr>
            <th>用户名</th>
            <th>管理员id</th>
            <th>被申述次数</th>
            <th>操作</th>
<<<<<<< HEAD
=======
            <th>转变身份</th>
>>>>>>> 983e94e (ninth)
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users2}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.id}</td>
                <td>${user.appealCount}次</td>
                <td><form action="http://localhost:8080/nw/manager/resetManager" method="post">
                    <input type="submit" name="update" value="解除该用户管理员身份" class="btn btn-warning">
                    <input type="hidden" name="username" value="${requestScope.username}">
                    <input type="hidden" name="password" value="${requestScope.password}">
                    <input type="hidden" name="root" value="${requestScope.root}">
                    <input type="hidden" name="userId" value="${user.id}">
                </form>
                </td>
<<<<<<< HEAD
=======
                <td>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">选择分区
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=游戏区">游戏区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=动漫区">动漫区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=学习区">学习区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=科技区">科技区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=美食区">美食区</a></li>
                        </ul>
                    </div>
                </td>
>>>>>>> 983e94e (ninth)
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table table-striped ">
        <caption>美食区管理员状态</caption>
        <thead>
        <tr>
            <th>用户名</th>
            <th>管理员id</th>
            <th>被申述次数</th>
            <th>操作</th>
<<<<<<< HEAD
=======
            <th>转变身份</th>
>>>>>>> 983e94e (ninth)
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users1}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.id}</td>
                <td>${user.appealCount}次</td>
                <td><form action="http://localhost:8080/nw/manager/resetManager" method="post">
                    <input type="submit" name="update" value="解除该用户管理员身份" class="btn btn-warning">
                    <input type="hidden" name="username" value="${requestScope.username}">
                    <input type="hidden" name="password" value="${requestScope.password}">
                    <input type="hidden" name="root" value="${requestScope.root}">
                    <input type="hidden" name="userId" value="${user.id}">
                </form>
                </td>
<<<<<<< HEAD
=======
                <td>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">选择分区
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=游戏区">游戏区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=动漫区">动漫区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=学习区">学习区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=科技区">科技区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=美食区">美食区</a></li>
                        </ul>
                    </div>
                </td>
>>>>>>> 983e94e (ninth)
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table table-striped ">
        <caption>科技区管理员状态</caption>
        <thead>
        <tr>
            <th>用户名</th>
            <th>管理员id</th>
            <th>被申述次数</th>
            <th>操作</th>
<<<<<<< HEAD
=======
            <td>转变身份</td>
>>>>>>> 983e94e (ninth)
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.id}</td>
                <td>${user.appealCount}次</td>
                <td><form action="http://localhost:8080/nw/manager/resetManager" method="post">
                    <input type="submit" name="update" value="解除该用户管理员身份" class="btn btn-warning">
                    <input type="hidden" name="username" value="${requestScope.username}">
                    <input type="hidden" name="password" value="${requestScope.password}">
                    <input type="hidden" name="root" value="${requestScope.root}">
                    <input type="hidden" name="userId" value="${user.id}">
                </form>
                </td>
<<<<<<< HEAD
=======
                <td>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">选择分区
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=游戏区">游戏区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=动漫区">动漫区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=学习区">学习区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=科技区">科技区</a></li>
                            <li><a href="http://localhost:8080/nw/manager/changeUser?userId=${user.id}&username=${requestScope.username}&password${requestScope.password}&root=${requestScope.root}&zoom=美食区">美食区</a></li>
                        </ul>
                    </div>
                </td>
>>>>>>> 983e94e (ninth)
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>