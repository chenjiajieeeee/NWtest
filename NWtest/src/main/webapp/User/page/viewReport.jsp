<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>NW一轮考核</title>
    <link rel="stylesheet" href="http://localhost:8080/nw/notebook/dist/css/bootstrap.min.css" type="text/css">

</head>
<body class="container">

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
        <li><form  action="http://localhost:8080/nw/manager/chargeNote" method="post">
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
        <li><form  action="http://localhost:8080/nw/manager/chargeAllNote" method="post">
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
<p class="container" style="color: red">${requestScope.reportResult}</p>
<c:if test="${requestScope.notes.size()>0}">
    <table class="table">
        <caption style="color: red" class="h4">查看已举报笔记</caption>
        <thead>
        <tr>
            <th>缩略图</th>
            <th>内容</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.notes}" var="note">
            <tr>
                <td>
                    <a href="#" class="thumbnail"  >
                        <img src="${note.notePictureUrl}"
                             alt="通用的占位符缩略图" height="200px" width="200px">
                    </a>
                </td>
                <td>
                    <p>标题：${note.title}</p>
                    <p>内容：${note.main}</p>
                    <p>分区：${note.zoomName}</p>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<br>
<c:if test="${empty(requestScope.notes)}">
    <p style="color: red" class="h4">暂无已举报的笔记！</p>
</c:if>
</body>
</html>