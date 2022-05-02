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
    <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/page/findPage" method="post">
        <input type="submit" value="个人主页" name="action" class="btn btn-success">
        <input type="hidden" value="${requestScope.username}" name="username">

    </form>
    </li>
    &nbsp;&nbsp;
    <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/user/loginpage" method="post">
        <input type="submit" value="首页" name="action" class="btn btn-success">
        <input type="hidden" value="${requestScope.username}" name="username">

    </form>
    </li>
    <li><a href="http://localhost:8080/nw/User/page/login.jsp" class="btn btn-warning">退出登录</a></li>
</ul>
<ul class="breadcrumb " style="color: cornsilk;">
    <c:if test="${(requestScope.root)!='N'&&(requestScope.root)!='super'}">
    <li><form  action="http://localhost:8080/nw/manager/chargeAllNote" method="post">
        <input type="submit" value="管理笔记" name="action" class="btn btn-success">
        <input type="hidden" value="${requestScope.username}" name="username">

        <input type="hidden" value="${requestScope.root}" name="root">
    </form>
    </li>
    <li>
        <form  action="http://localhost:8080/nw/manager/chargeUser" method="post">
            <input type="submit" value="管理用户" name="action" class="btn btn-success">
            <input type="hidden" value="${requestScope.username}" name="username">

            <input type="hidden" value="${requestScope.root}" name="root">
        </form>
    </li>
    </c:if>
    <c:if test="${requestScope.root.equals('super')}">
        <li><form  action="http://localhost:8080/nw/manager/chargeNote" method="post">
            <input type="submit" value="管理笔记" name="action" class="btn btn-success">
            <input type="hidden" value="${requestScope.username}" name="username">

            <input type="hidden" value="${requestScope.root}" name="root">
        </form>
        </li>
        <li>
            <form  action="http://localhost:8080/nw/manager/chargeManagerUser" method="post">
                <input type="submit" value="管理区域管理员" name="action" class="btn btn-success">
                <input type="hidden" value="${requestScope.username}" name="username">

                <input type="hidden" value="${requestScope.root}" name="root">
            </form>
        </li>
        <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/manager/notice" method="post">
            <input type="submit" value="发布公告" name="action" class="btn btn-warning">
            <input type="hidden" value="${requestScope.username}" name="username">

            <input type="hidden" value="${requestScope.root}" name="root">
        </form>
        </li>
    </c:if>
</ul>
<h4 style="color: red" class="container">${requestScope.historyMsg}</h4>
<br>
<form class="form-inline container" role="form" action="http://localhost:8080/nw/search/result" method="post">
    <div class="form-group">
        <label class="sr-only" for="name">搜索框</label>
        <input type="text" class="form-control" id="name" name="tag"
               placeholder="输入关键字" value="明日方舟">
    </div>
    <button type="submit" class="btn btn-default" name="action">搜索</button>
    <input type="hidden" value="${requestScope.username}" name="username">

</form>
<div class="container">
<table class="table">
    <caption>历史记录</caption>
    <thead>
    <tr>
        <th>缩略图</th>
        <th>标题</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.notes}" var="note">
        <tr>
            <td>
                <div class="col">
                <div class="col-sm-2 col-md-3">
                    <c:if test="${note.releaseStatus.equals('1')}">
                <a href="http://localhost:8080/nw/note/detail?noteId=${note.id}&username=${requestScope.username}" class="thumbnail"  >
                    <img src="${note.notePictureUrl}"
                         alt="通用的占位符缩略图">
                </a>
                    </c:if>
                    <c:if test="${!note.releaseStatus.equals('1')}">
                        <a href="#" class="thumbnail"  >
                            <img src="http://localhost:8080/nw/notebook/check.jpeg"
                                 alt="通用的占位符缩略图">
                        </a>
                        <p>该笔记主人修改了笔记，故笔记审核中~</p>
                    </c:if>
                </div>
                </div>
            </td>
            <td class="h4">${note.title}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>