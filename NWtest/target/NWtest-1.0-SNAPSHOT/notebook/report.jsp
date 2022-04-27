<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/notebook/dist/css/bootstrap.min.css" type="text/css">
</head>
<body >

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
        <input type="hidden" value="${requestScope.password}" name="password">
    </form>
    </li>
    <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/user/loginpage" method="post">
        <input type="submit" value="首页" name="action" class="btn btn-success">
        <input type="hidden" value="${requestScope.username}" name="username">
        <input type="hidden" value="${requestScope.password}" name="password">
    </form>
    </li>
    &nbsp;&nbsp;
    <li><a href="http://localhost:8080/nw/User/page/login.jsp" class="btn btn-warning">退出登录</a></li>
</ul>
<p style="color: red" class="container">${requestScope.reportMsg}</p>
<br>
<div class="row container" >
    <div class="col-sm-6 col-md-4 col-md-offset-6">
        <div class="thumbnail">
            <img src="${requestScope.note.notePictureUrl}"
                 alt="通用的占位符缩略图">
            <div class="caption">
                <h3>${requestScope.note.title}</h3>
                <h4>${requestScope.note.zoomName}</h4>
                <h5>发布用户Uid：${requestScope.note.userId}</h5>
                <p>${requestScope.note.main}</p>
                <p>点赞数：${requestScope.note.likeCount}</p>
            </div>
        </div>
    </div>
</div>
<div class="col">
    <div class="col-sm-2 col-md-4 col-md-offset-4">
<form role="form">
    <div class="form-group container">
        <label for="name">举报理由</label>
        <textarea class="form-control" rows="5" id="name" name="main" style="width: 500px"></textarea>
    </div>
    <input type="hidden" name="username" value="${requestScope.username}">
    <input type="hidden" name="password" value="${requestScope.password}">
    <input type="hidden" name="noteId" value="${requestScope.note.id}">
    <input type="hidden" name="root" value="${requestScope.root}">
    <input type="submit" name="action" value="提交" class="btn btn-danger">
</form>
    </div>
</div>
</body>
</html>
