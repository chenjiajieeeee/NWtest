<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/note/dist/css/bootstrap.min.css" type="text/css">
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
    <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post">
        <input type="submit" value="个人主页" name="action" class="btn btn-success">
        <input type="hidden" value="${requestScope.username}" name="username">
        <input type="hidden" value="${requestScope.password}" name="password">
    </form>
    </li>
    &nbsp;&nbsp;
    <li><a href="http://localhost:8080/nw/User/page/login.jsp" class="btn btn-warning">退出登录</a></li>
</ul>
<ul class="breadcrumb " style="color: cornsilk;">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="动漫区" name="action" >
        <input type="hidden" value="${requestScope.username}" name="username">
        <input type="hidden" value="${requestScope.password}" name="password">
    </form></li>
    <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="学习区" name="action" >
        <input type="hidden" value="${requestScope.username}" name="username">
        <input type="hidden" value="${requestScope.password}" name="password">
    </form></li>
    <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="科技区" name="action" >
        <input type="hidden" value="${requestScope.username}" name="username">
        <input type="hidden" value="${requestScope.password}" name="password">
    </form></li>
    <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="游戏区" name="action" >
        <input type="hidden" value="${requestScope.username}" name="username">
        <input type="hidden" value="${requestScope.password}" name="password">
    </form></li>
    <li style="color: #0f0f0f"><form  action="http://localhost:8080/nw/PageServlet" method="post"><input type="submit" value="美食区" name="action" >
        <input type="hidden" value="${requestScope.username}" name="username">
        <input type="hidden" value="${requestScope.password}" name="password">
    </form></li>
</ul>
<div class="row container" >
    <div class="col-sm-6 col-md-4 col-md-offset-6">
        <div class="thumbnail">
            <img src="${note.notePictureUrl}"
                 alt="通用的占位符缩略图">
            <div class="caption">
                <h3>${note.title}</h3>
                <h4>${note.zoomName}</h4>
                <h5>发布用户Uid：${note.userId}</h5>
                <p>${note.main}</p>
                <p>点赞数：${note.likeCount}</p>
                <p>
                <p style="color: red">${requestScope.likeMsg}</p>
                <p style="color: red">${requestScope.CollectMsg}</p>
                    <a href="http://localhost:8080/nw/LikeServlet?noteId=${note.id}&username=${requestScope.username}&password=${requestScope.password}
                    " class="btn btn-primary" role="button">
                        点赞
                    </a>
                    <a href="http://localhost:8080/nw/CollectServlet?noteId=${note.id}&username=${requestScope.username}&password=${requestScope.password}"
                       class="btn btn-default" role="button">
                        收藏
                    </a>
                </p>
                <c:forEach items="${requestScope.tags}" var="tag">
                    <p style="color: #5cb85c"># ${tag.tagMain}</p>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<div class="row container">
    <div class="col-sm-6 col-md-4 col-md-offset-6" >
        <h4 style="color: #2aabd2">来看看大家的看法：</h4>
        <c:forEach items="${requestScope.comments}" var="comment">
             <h4>来自Uid：${comment.userId}</h4>
             <p  style="color: #985f0d">${comment.main}</p>
            <hr style="color: black">
        </c:forEach>
    </div>
    <div style="padding: 100px 100px 10px;" class="row container">
        <div class="col-sm-6 col-md-4 col-md-offset-6">
            <h4 style="color: #5cb85c">留下你的看法</h4>
            <hr>
            <form class="bs-example bs-example-form" role="form" method="post" action="http://localhost:8080/nw/SubmitServlet">
                <div class="input-group input-group-sm">
                    <p style="color: red">${requestScope.publishMsg}</p>
                    <textarea class="form-control" rows="3" placeholder="不要什么都不写噢" name="commentMain"></textarea>
                    <input type="submit" name="action" value="评论" class="btn btn-success">
                    <input type="hidden" value="${requestScope.username}" name="username">
                    <input type="hidden" value="${requestScope.password}" name="password">
                    <input type="hidden" value="${note.id}" name="noteId">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>